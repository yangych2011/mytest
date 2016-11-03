package com.puck.framework.service.pagination.mybatis;

import com.puck.framework.service.pagination.PaginationSqlGenerator;
import com.puck.framework.service.pagination.PaginationSqlGeneratorFactory;
import com.puck.framework.service.pagination.Paginator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

/**
 * @author yangyongchao
 * @descript
 * @name PaginationInterceptor.java
 * @date 2015年12月24日
 */
@Intercepts({ @org.apache.ibatis.plugin.Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class,
		org.apache.ibatis.session.ResultHandler.class }) })
public class PaginationInterceptor implements Interceptor {

	// 默认分页sqlid后缀
	public static final String DEFAULT_PAGINATION_SQL_SUFFIX = "DoPagination";
	// 默认分页对象名称
	public static final String DEFAULT_PAGINATION_FIELD_NAME = "paginator";
	// 默认数据库方言
	public static final String DEFAULT_DIALECT = "MYSQL";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final ConcurrentMap<String, String> countSqlCache = new ConcurrentHashMap();// 查询总数sql缓存
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final ConcurrentMap<String, String[]> paginationSqlTemplateCache = new ConcurrentHashMap();// 分页sql缓存

	private PaginationSqlGenerator sqlGenerator;// 分页sql处理类

	private String dialect = DEFAULT_DIALECT;
	private String paginatorFieldName = DEFAULT_PAGINATION_FIELD_NAME;
	private String paginationSqlSuffix = DEFAULT_PAGINATION_SQL_SUFFIX;

	public PaginationInterceptor() {
		this.sqlGenerator = PaginationSqlGeneratorFactory.getGenerator(dialect);
	}

	/**
	 * 拦截器逻辑实现
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		Object[] args = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];
		String sqlIdWithNamespace = mappedStatement.getId();

		if (sqlIdWithNamespace.endsWith(this.paginationSqlSuffix)) {
			Object param = args[1];
			Paginator paginator = getPaginatorFromSqlParam(param);
			if (paginator == null) {
				throw new IllegalArgumentException("paginator cannot be null in " + sqlIdWithNamespace);
			}
			if (paginator.isDoPagination()) {

				BoundSql boundSql = mappedStatement.getBoundSql(param);
				String originalSql = boundSql.getSql();

				if (paginator.isQueryTotal()) {
					int total = queryTotal(invocation, mappedStatement, param, boundSql, originalSql);
					paginator.setTotalRecord(total);
				}

				if (paginator.getPageNo() > paginator.getTotalPage()) {
					paginator.setPageNo(paginator.getTotalPage());
				}

				String paginationSql = getPaginationSql(paginator, originalSql);

				setSqlForBoundSql(boundSql, paginationSql);

				args[0] = genMappedStatement(mappedStatement, boundSql);
			}
		}

		return invocation.proceed();
	}

	/**
	 * 获取分页sql
	 * 
	 * @param paginator
	 * @param originalSql
	 * @return
	 */
	private String getPaginationSql(Paginator paginator, String originalSql) {
		String paginationSql = null;
		String[] template = null;

		template = (String[]) paginationSqlTemplateCache.get(originalSql);
		if (null != template) {
			try {
				paginationSql = this.sqlGenerator.genPaginationSqlUsingTemplate(template, paginator);
			} catch (Exception e) {
			}
			if (null == paginationSql) {
				paginationSql = this.sqlGenerator.genPaginationSql(originalSql, paginator);
			}
		} else {
			try {
				template = this.sqlGenerator.genPaginationSqlTemplate(originalSql);
			} catch (Exception e) {
			}
			if (null == template) {
				paginationSql = this.sqlGenerator.genPaginationSql(originalSql, paginator);
			} else {
				try {
					paginationSql = this.sqlGenerator.genPaginationSqlUsingTemplate(template, paginator);
				} catch (Exception e) {
				}
				if (null == paginationSql) {
					paginationSql = this.sqlGenerator.genPaginationSql(originalSql, paginator);
				} else {
					paginationSqlTemplateCache.putIfAbsent(originalSql, template);
				}
			}
		}

		return paginationSql;
	}

	/**
	 * 查询总数
	 * 
	 * @param invocation
	 * @param mappedStatement
	 * @param param
	 * @param boundSql
	 * @param originalSql
	 * @return
	 * @throws SQLException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private int queryTotal(Invocation invocation, MappedStatement mappedStatement, Object param, BoundSql boundSql,
			String originalSql) throws SQLException, NoSuchFieldException, IllegalAccessException {
		int total = 0;

		String queryTotalSql = (String) countSqlCache.get(originalSql);
		if (null == queryTotalSql) {
			queryTotalSql = this.sqlGenerator.genQueryTotalSql(originalSql);
			countSqlCache.putIfAbsent(originalSql, queryTotalSql);
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = ((Executor) invocation.getTarget()).getTransaction().getConnection();
			ps = conn.prepareStatement(queryTotalSql);

			setSqlForBoundSql(boundSql, queryTotalSql);

			DefaultParameterHandler paramHandler = new DefaultParameterHandler(mappedStatement, param, boundSql);
			paramHandler.setParameters(ps);

			rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			cleanupResource(ps, rs);
		}
		return total;
	}

	/**
	 * 清理资源
	 * 
	 * @param ps
	 * @param rs
	 * @throws SQLException
	 */
	private void cleanupResource(PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw e;
			} finally {
				if (ps != null)
					ps.close();
			}

		} else if (ps != null)
			ps.close();
	}

	/**
	 * 从入参中获取分页对象
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Paginator getPaginatorFromSqlParam(Object param) {
		if (param == null) {
			return null;
		}
		Paginator paginator = null;

		if ((param instanceof Paginator)) {
			paginator = (Paginator) param;
		} else if ((param instanceof Map)) {
			Map map = (Map) param;
			paginator = (Paginator) map.get(this.paginatorFieldName);
		}
		return paginator;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
		System.out.println(properties.get("dialect"));

	}

	private void setSqlForBoundSql(BoundSql boundSql, String newSql) throws SecurityException,
			IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		setValueByFieldName(boundSql, "sql", newSql);
	}

	private MappedStatement genMappedStatement(MappedStatement ms, final BoundSql boundSql) {
		SqlSource sqlSource = new SqlSource() {
			public BoundSql getBoundSql(Object param) {
				return boundSql;
			}
		};
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), sqlSource,
				ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());

		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		builder.resultSetType(ms.getResultSetType());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		builder.databaseId(ms.getDatabaseId());
		return builder.build();
	}

	private void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		if (obj == null) {
			return;
		}
		Field field = getFieldByFieldName(obj, fieldName);
		if (field == null) {
			return;
		}
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	@SuppressWarnings("rawtypes")
	private Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		return null;
	}

	public void setPaginatorFieldName(String paginatorFieldName) {
		this.paginatorFieldName = paginatorFieldName;
	}

	public void setPaginationSqlSuffix(String paginationSqlSuffix) {
		this.paginationSqlSuffix = paginationSqlSuffix;
	}

	public void setSqlGenerator(PaginationSqlGenerator sqlGenerator) {
		this.sqlGenerator = sqlGenerator;
	}

	public PaginationSqlGenerator getSqlGenerator() {
		return this.sqlGenerator;
	}

	public String getPaginatorFieldName() {
		return this.paginatorFieldName;
	}

	public String getPaginationSqlSuffix() {
		return this.paginationSqlSuffix;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

}
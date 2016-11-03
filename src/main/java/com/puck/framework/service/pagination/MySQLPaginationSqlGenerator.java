/**
 * 
 */
package com.puck.framework.service.pagination;

/**
 * @author yangyongchao
 * @descript
 * @name MySQLPaginationSqlGenerator.java
 * @date 2015年12月24日
 */
public class MySQLPaginationSqlGenerator implements PaginationSqlGenerator {

	public static final String DIALECT_NAME = "MYSQL";

	@Override
	public String getDialectName() {
		return DIALECT_NAME;
	}

	@Override
	public String genQueryTotalSql(String originalSql) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(0) AS TOTAL_COUNT FROM (");
		builder.append(originalSql);
		builder.append(") AS TEMP_TAB");
		return builder.toString();
	}

	@Override
	public String genPaginationSql(String originalSql, Paginator paginator) {
		StringBuilder builder = new StringBuilder("SELECT * FROM(");
		builder.append(originalSql);
		builder.append(" ) AS TEMP_TAB LIMIT ");
		builder.append(paginator.getStartIndex());
		builder.append(",");
		builder.append(paginator.getPageSize());
		return builder.toString();
	}

	@Override
	public String genPaginationSqlUsingTemplate(String[] template, Paginator paginator) {
		StringBuilder builder = new StringBuilder(template[0]);
		builder.append(paginator.getStartIndex());
		builder.append(template[1]);
		builder.append(paginator.getPageSize());
		return builder.toString();
	}

	@Override
	public String[] genPaginationSqlTemplate(String originalSql) {
		String[] template = new String[1];
		StringBuilder builder = new StringBuilder("SELECT * FROM(");
		builder.append(originalSql);
		builder.append(" ) AS TEMP_TAB LIMIT ");
		template[0] = builder.toString();
		template[1] = ",";
		return template;
	}

}

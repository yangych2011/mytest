package com.puck.framework.service.pagination;

/**
 * @author yangyongchao
 * @descript
 * @name PaginationSqlGenerator.java
 * @date 2015年12月24日
 */
public abstract interface PaginationSqlGenerator {

	public abstract String getDialectName();

	public abstract String genQueryTotalSql(String paramString);

	public abstract String genPaginationSql(String paramString, Paginator paramPaginator);

	public abstract String genPaginationSqlUsingTemplate(String[] paramArrayOfString, Paginator paramPaginator);

	public abstract String[] genPaginationSqlTemplate(String paramString);

}

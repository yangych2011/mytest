/**
 * 
 */
package com.puck.framework.service.pagination;

/**
 * @author yangyongchao
 * @descript
 * @name DB2PaginationSqlGenerator.java
 * @date 2015年12月24日
 */
public class DB2PaginationSqlGenerator implements PaginationSqlGenerator {
	public static final String DIALECT_NAME = "DB2";

	public String getDialectName() {
		return DIALECT_NAME;
	}

	public String genQueryTotalSql(String originalSql) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(0) FROM (");
		builder.append(originalSql);
		builder.append(") AS TOTAL_COUNT");
		return builder.toString();
	}

	public String genPaginationSql(String originalSql, Paginator paginator) {
		StringBuilder builder = new StringBuilder("SELECT * FROM (SELECT TEMP_TAB.*,ROWNUMBER() OVER() AS IDX FROM (");
		builder.append(originalSql);
		builder.append(" ) AS TEMP_TAB ) AS TEMP_TAB_WITH_IDX WHERE TEMP_TAB_WITH_IDX.IDX > ");
		builder.append(paginator.getStartIndex());
		builder.append(" AND TEMP_TAB_WITH_IDX.IDX <= ");
		builder.append(paginator.getStartIndex() + paginator.getPageSize());
		return builder.toString();
	}

	public String genPaginationSqlUsingTemplate(String[] template, Paginator paginator) {
		StringBuilder builder = new StringBuilder(template[0]);
		builder.append(paginator.getStartIndex());
		builder.append(template[1]);
		builder.append(paginator.getStartIndex() + paginator.getPageSize());
		return builder.toString();
	}

	public String[] genPaginationSqlTemplate(String originalSql) {
		String[] template = new String[2];

		StringBuilder builder = new StringBuilder("SELECT * FROM (SELECT TEMP_TAB.*,ROWNUMBER() OVER() AS IDX FROM (");
		builder.append(originalSql);
		builder.append(" ) AS TEMP_TAB ) AS TEMP_TAB_WITH_IDX WHERE TEMP_TAB_WITH_IDX.IDX > ");

		template[0] = builder.toString();
		template[1] = " AND TEMP_TAB_WITH_IDX.IDX <= ";

		return template;
	}
}

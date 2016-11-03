/**
 * 
 */
package com.puck.framework.service.pagination;

/**
 * @author yangyongchao
 * @descript
 * @name OraclePaginationSqlGenerator.java
 * @date 2015年12月24日
 */
public class OraclePaginationSqlGenerator implements PaginationSqlGenerator {
	public static final String DIALECT_NAME = "ORACLE";

	public String getDialectName() {
		return DIALECT_NAME;
	}

	public String genQueryTotalSql(String originalSql) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(0) TOTAL_COUNT FROM (");
		builder.append(originalSql);
		builder.append(")");
		return builder.toString();
	}

	public String genPaginationSql(String originalSql, Paginator paginator) {
		StringBuilder builder = new StringBuilder(
				"SELECT * FROM(SELECT TEMP_TAB.*,ROW_NUMBER() OVER(ORDER BY NULL) AS IDX FROM (( ");

		builder.append(originalSql);
		builder.append(" )TEMP_TAB))TEMP_TAB_WITH_IDX WHERE TEMP_TAB_WITH_IDX.IDX > ");
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
		StringBuilder builder = new StringBuilder(
				"SELECT * FROM(SELECT TEMP_TAB.*,ROW_NUMBER() OVER(ORDER BY NULL) AS IDX FROM (( ");

		builder.append(originalSql);
		builder.append(" )TEMP_TAB))TEMP_TAB_WITH_IDX WHERE TEMP_TAB_WITH_IDX.IDX > ");
		template[0] = builder.toString();
		template[1] = " AND TEMP_TAB_WITH_IDX.IDX <= ";
		return template;
	}
}

/**
 * 
 */
package com.puck.framework.service.pagination;

import org.springframework.util.Assert;

/**
 * @author yangyongchao
 * @descript 工厂模式
 * @name PaginationSqlGeneratorFactory.java
 * @date 2015年12月24日
 */
public class PaginationSqlGeneratorFactory {

	private static final PaginationSqlGenerator db2SqlGenerator = new DB2PaginationSqlGenerator();
	private static final PaginationSqlGenerator oracleSqlGenerator = new OraclePaginationSqlGenerator();
	private static final PaginationSqlGenerator mysqlSqlGenerator = new MySQLPaginationSqlGenerator();

	public static PaginationSqlGenerator getGenerator(String dialectName) {
		Assert.hasText(dialectName);

		PaginationSqlGenerator generator = null;
		dialectName = dialectName.toUpperCase();
		if (dialectName.equals("DB2")) {
			generator = db2SqlGenerator;
		} else if (dialectName.equals("ORACLE")) {
			generator = oracleSqlGenerator;
		} else if (dialectName.equals("MYSQL")) {
			generator = mysqlSqlGenerator;
		}
		return generator;
	}
}

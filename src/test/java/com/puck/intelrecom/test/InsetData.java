/**
 * 
 */
package com.puck.intelrecom.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 * @title InsetData.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
public class InsetData {

	private static final List<String> types = Arrays.asList("1", "2", "3");

	static {
		try {
			// 动态加载mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&amp;characterEncoding=UTF-8&amp;";
		Connection conn = DriverManager.getConnection(url, "root", "");
		return conn;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		String sql;

		try {
			// 一个Connection代表一个数据库连接
			conn = getCon();
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			Statement stmt = conn.createStatement();
			int id = 3;
			String productId = "";
			String productName = "";
			String assetType = "";
			BigDecimal amount = new BigDecimal("50000");

			for (int i = 4; i < 1001; i++) {
				id = i;
				productId = changeNum(i);
				productName = "产品" + i;
				assetType = types.get(i % 3);
				amount = amount.add(new BigDecimal("5000"));

				sql = "INSERT INTO bs_product (id, product_id, product_name, guarantee_type, product_amount, period, publish_org, customer_belong, status, create_time, update_time) VALUES ("
						+ id
						+ ", '"
						+ productId
						+ "', '"
						+ productName
						+ "', '"
						+ assetType
						+ "', "
						+ amount.toString()
						+ ", null, null, null, null, '2016-10-25 17:31:42', '2016-10-25 17:31:42');";
				System.out.println(sql);
				stmt.execute(sql);
			}

		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public static String changeNum(int i) {
		return String.format("%03d", i);

	}

}

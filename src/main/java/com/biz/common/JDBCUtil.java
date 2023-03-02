package com.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
				
		try {

			DriverManager.registerDriver(new org.h2.Driver());

			String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa", "");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void close(PreparedStatement stmt, Connection conn) {
		
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		
		try {
//			rs.close();
			stmt.close();
			conn.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

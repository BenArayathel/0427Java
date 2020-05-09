package com.bankofben.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDbConnection {
	
	private static Connection connection = null;
	
	private OracleDbConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		if (connection==null) {
			String url = System.getenv("DB_URL");
			String username = System.getenv("DB_USERNAME");
			String password = System.getenv("DB_PASSWORD");
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

}

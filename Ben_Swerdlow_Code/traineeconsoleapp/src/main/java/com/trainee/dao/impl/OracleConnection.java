package com.trainee.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	
	private static Connection connection = null;
	
	private OracleConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		if (connection==null) {
			String url = "jdbc:oracle:thin:@myfirstorcl.cyw8bxzbivob.us-east-2.rds.amazonaws.com:1521:orcl";
			String username = "admin";
			String password = "e85c6^a*s%TuWRg&";
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

}

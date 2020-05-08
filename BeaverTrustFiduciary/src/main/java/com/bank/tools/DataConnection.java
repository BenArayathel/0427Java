package com.bank.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
	
	/*
	 * this class creates a reusable connection to my db
	 * and it employs a singleton design pattern
	 * for encapsulation purposes
	 */
	
	private static Connection conn = null;
	
	private DataConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		String url = 
				"jdbc:oracle:thin:@database-1.cb5vpvii5jvy.us-west-2.rds.amazonaws.com:1521:orcl";
		String username = "admin";
		String password = "gunderodd";
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	// instructor's example included this line
	// Class.forName("oracle.jdbc.OracleDriver");

}

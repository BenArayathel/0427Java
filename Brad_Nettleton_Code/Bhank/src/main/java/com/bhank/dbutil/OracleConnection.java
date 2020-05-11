package com.bhank.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	
//	private static final String URL = System.getenv("Bhank_db_url");
//	private static final String USERNAME = System.getenv("Bhank_db_username");
//	private static final String PASSWORD = System.getenv("Bhank_db_password");

	private static Connection connection=null;
	
	private OracleConnection() {
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		connection=DriverManager.getConnection("jdbc:oracle:thin:@bank.ck7jqrojz5xz.us-east-2.rds.amazonaws.com:1521:ORCL", "admin", "p$ssword");
		return connection;
	}
}

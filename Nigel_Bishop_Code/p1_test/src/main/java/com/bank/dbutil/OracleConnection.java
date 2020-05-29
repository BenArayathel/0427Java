package com.bank.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {

	private static Connection connection=null;
	
	private OracleConnection() 
	{
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		Class.forName("oracle.jdbc.OracleDriver");
		
		final String URL = System.getenv("P0_URL");
		final String USERNAME = System.getenv("P0_USERNAME");
		final String PASSWORD = System.getenv("P0_PASSWORD");

		connection=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return connection;
	}
}

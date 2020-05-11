package com.hackbank.persistence.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDBConnection {
	
	private static Connection conn;
	
	private static String URL = System.getenv("HACKBANK_URL");
	private static String USER = System.getenv("HACKBANK_USER");
	private static String PASSWORD = System.getenv("HACKBANK_PWD");
	
	private SingletonDBConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}

}

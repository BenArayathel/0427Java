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
	
	//using my system variables, hiding my info from github
	private static final String URL = System.getenv("REVATURE_P0_URL");
	private static final String USERNAME = System.getenv("REVATURE_P0_USERNAME");
	private static final String PASSWORD = System.getenv("REVATURE_P0_PASSWORD");
	
	public static Connection getConnection() throws SQLException {
		//original approach, will delete to simulate security if system variables work
//		String url = 
//				"jdbc:oracle:thin:@database-1.cb5vpvii5jvy.us-west-2.rds.amazonaws.com:1521:orcl";
//		String username = "admin";
//		String password = "gunderodd";
		
		// this fix allows me to use my connection in P1
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver Loaded ... ...");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not register driver!");
			e.printStackTrace();
		}

		
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}
	
	// instructor's example included this line
	// mostly likely no longer used, depricated
	// Class.forName("oracle.jdbc.OracleDriver");

}

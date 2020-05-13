package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private static Connection conn = null;
	
	private DBConnector() {
		
	}
	
	/**
	 * Singleton class to for database connection
	 * @return the connection to the database
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=root&password=bs95162z");
		//conn.setAutoCommit(false);	//Turns on autocommit
		return conn;
	}
	
	
	
	
}

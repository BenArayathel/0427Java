package com.ploutos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PloutosConnection {
	private static Connection c = null;
	
	private PloutosConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("JURL");
		String username = System.getenv("JUSERNAME");
		String password = System.getenv("JPASSWORD");
		

		
		/*
		 * I am not using
		 * 		if (c.isClosed())
		 * because this singleton is only called in the parens of a try/catch block and is always closed after it's been used.
		 * I understand that using this would be best practice, this is still risky especially in other contexts, but I like to live dangerously.
		 * 
		 * I am not using
		 * 		if (c == null)
		 * because this singleton is only null before it's been called at least once.
		 * 
		 * Simply calling DriverManager.getConnection() each time this is called is an elegant solution for this singleton in this particular program.
		 */
		
		c = DriverManager.getConnection(url, username, password);
		return c;
	}
	
	
}

package com.trainee.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {

	private static Connection connection=null;
	
	private OracleConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String username="java";
		String password="java";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}

package com.project1.dbutil;

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
		String url="jdbc:oracle:thin:@myfirstorcl.chpbw1uvnfvk.us-east-2.rds.amazonaws.com:1521:orcl";
		String username="benjaminknaak";
		String password="MoonIsGod0318";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}

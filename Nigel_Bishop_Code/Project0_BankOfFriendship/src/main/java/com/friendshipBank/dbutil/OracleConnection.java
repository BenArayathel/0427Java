package com.friendshipBank.dbutil;

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
		String url="jdbc:oracle:thin:@myfirstoracledata.c1retv5b8x6j.us-east-2.rds.amazonaws.com:1521:orcl";
		String username="puser";
		String password="p4ssw0rd";
		

		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}

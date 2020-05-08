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
		//  jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl
		String url="jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl";
		//String url="database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com";
		String username="mybasic";
		String password="34uy34uy";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}

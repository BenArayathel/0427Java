package com.example.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnection {


	private static Connection connection=null;
	
	private DConnection() {
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url=System.getenv("url");
		String username="admin";
		String password="password1";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}


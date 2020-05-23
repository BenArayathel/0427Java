package com.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.secrets.SecretStuff;

public class DatabaseConnection {
	
	private static Connection conn = null;
	
	private DatabaseConnection() {
		
	}
	private static final String AWSURL = SecretStuff.getAwsURL();
	private static final String USERNAME = SecretStuff.getAwsUserName();
	private static final String PASSWORD = SecretStuff.getAwsPassword();
	
	public static Connection getConn() throws SQLException {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//loggy.debug("Driver started");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		conn = DriverManager.getConnection(AWSURL, USERNAME, PASSWORD);
		return conn;
	}
	
	// instructor's example included this line
	// mostly likely no longer used, depricated
	// Class.forName("oracle.jdbc.OracleDriver");

}

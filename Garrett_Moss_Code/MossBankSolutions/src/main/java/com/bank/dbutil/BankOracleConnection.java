package com.bank.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BankOracleConnection {

	private static Connection connection = null;
	
	private BankOracleConnection(){
		
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thing:@moss-bank-solutions.cvzxwyvgfirt.us-east-2.rds.amazonaws.com:1521:orcl";
			String username="gadmin";
			String password="banksolutions101";
		connection=DriverManager.getConnection(url, username, password);
		
		return connection;
	}
}

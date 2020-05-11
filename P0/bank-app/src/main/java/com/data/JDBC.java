package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * This is the data layer that interfaces with the database
 * through local SQL queries. 
 * @author Dave Wroblewski
 *
 */
public class JDBC {
	
	private static String userName;
	private static String password;
	private static ResultSet resultSet;
	public static Connection conn2;
	private static Statement stmt;
	
	public JDBC() {
		
	}

	public JDBC(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	//First thing we need to do is make a database connection and verify for later use
	public static void databaseConnection(String inTable) {
		
		try {
		    conn2 = DBConnector.getConnection();//DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=" + this.userName + "&password=" + this.password);

		    stmt = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			resultSet = stmt.executeQuery("SELECT * FROM " + inTable);
		   
		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}


	///Called by other classes to create a new record for account creation
	public static void insert(String inTable, String inColumns, String inValues) {
//		System.out.println("INSERT INTO " + inTable + " (" + inColumns + ") "
//				+ "VALUES ("+ inValues + ")");
		   Statement stmt;
		try {
			stmt = conn2.createStatement();
		    stmt.executeUpdate("INSERT INTO " + inTable + " (" + inColumns + ") "
						+ "VALUES ("+ inValues + ")"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		databaseConnection(inTable);
	}

	//Overloaded method that takes a double value in
	public static void update(String inTable, String inColumn, double inValue, int inAccountNumber) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=root&password=bs95162z");
		PreparedStatement ps = conn.prepareStatement("UPDATE " + inTable + " SET " + inColumn + " = " + inValue + " WHERE accountNumber = " + inAccountNumber);
		ps.execute();
	}
	//Overloaded method that takes a boolean value in
	public static void update(String inTable, String inColumn, boolean inValue, int inAccountNumber) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=root&password=bs95162z");
		PreparedStatement ps = conn.prepareStatement("UPDATE " + inTable + " SET " + inColumn + " = " + inValue + " WHERE accountNumber = " + inAccountNumber);
		ps.execute();
	}
	
//	
//	public static ResultSet updateResultSet() throws SQLException {
//		Statement updateStmt = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_UPDATABLE);
//		resultSet = updateStmt.executeQuery("SELECT * FROM accountstable;");
//		
//		return resultSet;
//	}
	
	public static int getRowCount(String inTable) throws SQLException {
		int retVal = 0;
		int count = 0;
		if(count == 0) {
			Statement noCols = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet numberOfColumns = noCols.executeQuery("SELECT COUNT(*) FROM " + inTable);
			while(numberOfColumns.next()) {
				retVal = numberOfColumns.getInt(1);
			}
			count = 1;
		}else {
			retVal = 9999;
		}
			
		return retVal;
	}

	public static ResultSet getResultSet(String inTable) {
		databaseConnection(inTable);
		return resultSet;
	}

	public ResultSet setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
		return resultSet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

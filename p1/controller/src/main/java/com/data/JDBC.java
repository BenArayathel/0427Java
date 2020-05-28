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
	
	/**
	 * Creates the initial connection through DBConnector singleton class
	 * @param inTable the table to query
	 */
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
	
public static void databaseConnection(String inColumn, String inTable) {
		
		try {
		    conn2 = DBConnector.getConnection();//DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=" + this.userName + "&password=" + this.password);

		    stmt = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			resultSet = stmt.executeQuery("SELECT " + inColumn + " FROM " + inTable);
		   
		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}


	/**
	 * Called by other classes to create a new record for account creation
	 * @param inTable the table to query
	 * @param inColumns columns to insert data into
	 * @param inValues values to insert into columns
	 */
	public static void insert(String inTable, String inColumns, String inValues) {
		   Statement stmt;
		try {
			stmt = conn2.createStatement();
		    stmt.executeUpdate("INSERT INTO " + inTable + " (" + inColumns + ") "
						+ "VALUES ("+ inValues + ")"); 
		} catch (SQLException e) {
			System.out.println("There was an error, contact your system administrator");
		}
		databaseConnection(inTable);
	}

	/**
	 * Makes a call to update the database given in parameters
	 * Overloaded method that takes a double value in
	 * @param inTable the table to query
	 * @param inColumn the column to update
	 * @param inValue the value to be inserted into column
	 * @param inAccountNumber the number of the account currently being accessed
	 * @throws SQLException
	 */
	public static void update(String inTable, String inColumn, double inValue, int inAccountNumber) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=root&password=bs95162z");
		PreparedStatement ps = conn.prepareStatement("UPDATE " + inTable + " SET " + inColumn + " = " + inValue + " WHERE accountNumber = " + inAccountNumber);
		ps.execute();
	}
	
	/**
	 * Makes a call to update the database given in parameters
	 * Overloaded method that takes a boolean value in
	 * @param inTable the table to query
	 * @param inColumn the column to update
	 * @param inValue the boolean value to be inserted into column
	 * @param inAccountNumber the number of the account currently being accessed
	 * @throws SQLException
	 */
	public static void update(String inTable, String inColumn, boolean inValue, int inAccountNumber) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabse?user=root&password=bs95162z");
		PreparedStatement ps = conn.prepareStatement("UPDATE " + inTable + " SET " + inColumn + " = " + inValue + " WHERE accountNumber = " + inAccountNumber);
		ps.execute();
	}
	
	public static ResultSet getResultSet(String inTable) {
		databaseConnection(inTable);
		return resultSet;
	}
	
	public static ResultSet getResultSet(String inColumn, String inTable) {
		databaseConnection(inColumn, inTable);
		return resultSet;
	}
	
	/**
	 * For web access 
	 * Communicates with the servlet to determine whether the user is a customer
	 * @return value of whether they have access or not
	 * @throws SQLException 
	 */
	public static boolean logIn(int inUserID, int inPin) throws SQLException {
		boolean isUser = false;
		ResultSet rs = JDBC.getResultSet("accountstable");
		while(rs.next()) {
			if(rs.getInt(1) == inUserID && rs.getInt(2) == inPin) {
				isUser = true;
			}
		}
		
		return isUser;
	}
}

package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
	Applies Singleton Pattern
	Simply a class that provides a static getConn() method that open a connection using URL and credentials.
	DAO classes will utilize these static methods to open connection and send queries.

	Simply call OracleConnection.getConn() to open a connection.
 */
public class OracleConnection {

	private static Connection conn = null;

	private OracleConnection() {
	}

	public static Connection getConn() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.OracleDriver"); // Optional since JDBC v4.0.
		final String URL = "jdbc:oracle:thin:@bankdb.cgbk8ajkyybd.us-east-2.rds.amazonaws.com:1521:orcl";
		final String USERNAME = "admintk";
		final String PASSWORD = "p4ssw0rd";
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

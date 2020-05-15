package dbutil;

import org.apache.log4j.Logger;

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

	static final Logger log = Logger.getLogger(OracleConnection.class);

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
			log.error("Internal error, please contact SYSADMIN");
		}
		return conn;
	}
}

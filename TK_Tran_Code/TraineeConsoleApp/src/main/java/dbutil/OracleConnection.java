package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
	Applies Singleton Pattern
	Simply a class that provides a static getConnection() to open a connection using URL and credentials.
	DAO classes (TraineeDAOImpl) will utilize these static methods to open connections and send queries.
 */
public class OracleConnection {

	private static Connection conn = null;

	private OracleConnection() {
	}

	public static Connection getConnection() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.OracleDriver"); // Can be omitted since JDBC 4.0.
		String url = "jdbc:oracle:thin:@myfirstorcl.cgbk8ajkyybd.us-east-2.rds.amazonaws.com:1521:orcl";
		String username = "badmin";
		String password = "p4ssw0rd";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

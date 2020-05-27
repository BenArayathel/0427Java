package connection.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import bank.transaction.dao.BankDAO;
import bank.transaction.dao.BankDaoImpl;
import src.main.java.com.application.bank.secrets.SecretStuff;

public class DAOUtilites {

	private static SecretStuff ss = new SecretStuff();

	private static final String CONNECTION_USERNAME = SecretStuff.getAwsUserName();
	private static final String CONNECTION_PASSWORD = SecretStuff.getAwsPassword();
	private static final String URL = SecretStuff.getAwsURL();

	// private static final String CONNECTION_USERNAME = "mybasic";
	// private static final String CONNECTION_PASSWORD = "34uy34uy";
	// private static final String URL =
	// "jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl";

	private static Connection connection;

	public static synchronized Connection getConnection() throws SQLException {

		if (connection == null) {

			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("Driver Loaded ... ...");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}

			// System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}

		// If connection was closed then retrieve a new connection
		if (connection.isClosed()) {
			// System.out.println("Connection Found Closed!: Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

	public static BankDAO getBankDAO() {
		return new BankDaoImpl();
	}

}

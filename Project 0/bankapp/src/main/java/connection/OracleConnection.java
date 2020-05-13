package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection
{
	private static Connection connection=null;
	
	private OracleConnection() 
	{
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.OracleDriver");
		String url="jdbc:oracle:thin:@database-1.c7cy1w0cmkrg.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username="admin";//AWS username for database
		String password="password";//AWS password for database
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}
package src.main.java.com.application.bank.secrets;

public class SecretStuff {
	
	/**
	 * 	private static final String CONNECTION_USERNAME = "mybasic";
	private static final String CONNECTION_PASSWORD = "34uy34uy";
	private static final String URL = "jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl";
	 * @return
	 */
	
	
	public static String getAwsURL() {
		
		return "jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl";
	}

	public static String getAwsUserName() {
		
		return "mybasic";
	}
	
	public static String getAwsPassword() {
		
		return "34uy34uy";
	}
}

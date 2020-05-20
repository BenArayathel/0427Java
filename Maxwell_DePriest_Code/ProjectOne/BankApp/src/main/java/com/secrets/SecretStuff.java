package com.secrets;

import com.dao.impl.AccountDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.exceptions.BusinessException;

public class SecretStuff {
	
	private static final String awsURL = "jdbc:oracle:thin:@database-1.ccpojkviae8q.us-east-2.rds.amazonaws.com:1521:orcl";
	private static final String awsUserName = "madmax9242";
	private static final String awsPassword = "jasonbourne";
	private static final String originalString = "ZYXWVUTSRQPONMLKJIHGFEDCBAabcdefghijklmnopqrstuvwxyz0987654321";
	private static final String altString = "1234567890zyxwvutsrqponmlkjihgfedcbaABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static UserDaoImpl uDI = new UserDaoImpl();
	private static AccountDaoImpl aDI = new AccountDaoImpl();

	public SecretStuff() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getOriginalString() {
		return originalString;
	}
	
	public static String getAltString() {
		return altString;
	}
	
	public static String getAwsURL() {
		return awsURL;
	}
	
	public static String getAwsUserName() {
		return awsUserName;
	}
	
	public static String getAwsPassword() {
		return awsPassword;
	}
	
	public static void burnItAllDown() {
		try {
			uDI.deleteAllUsers();
			aDI.deleteAllAccounts();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
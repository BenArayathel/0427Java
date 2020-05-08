package com.maindriver.OLD;

import com.bank.presentation.UserHome;
import com.bank.tools.myScanner;
import com.bank.tools.quitOption;

public class UserLogin {
	//temporary login credentials: username == hc, password == pass
//	static Scanner loginScan = new Scanner(System.in);
	static String userLoginU = null;


	
	//first, check the users username via input
	public static void validateUsername() {
		
		System.out.println("Enter your username: ");
		userLoginU = myScanner.scan.nextLine().toString();
		
		//either found in db, quit to exit, or try again on failed attempt
		//and if it is found, move on to validate password
		if (userLoginU.equalsIgnoreCase("hc")) {
//			loginScan.nextLine();
			validatePassword();
		} else if (userLoginU.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			System.out.println("Username not recognized. Please try again.");
			validateUsername();
		}
		
	}

	
	public static void validatePassword() {
		String userLoginP = null;
		
		System.out.println("Enter your password: ");
		userLoginP = myScanner.scan.nextLine();
		
		//hardcoded default password, will be "is in database + connected to username
		if (userLoginP.equalsIgnoreCase("pass")) {
			System.out.println(userLoginU);
			UserHome.userWelcome(userLoginU);
		} else if (userLoginP.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			System.out.println("That username and password combination was not found. Please try again.");
			validateUsername();
		}

	}
	

}

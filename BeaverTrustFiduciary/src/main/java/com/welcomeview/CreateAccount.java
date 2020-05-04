package com.welcomeview;

import com.maindriver.myScanner;
import com.userview.UserHome;

public class CreateAccount {
	
	public static void userCreate() {
		String createUsername = null;
		String createPassword = null;
//		Scanner createScan = new Scanner(System.in);
		
		System.out.println("Enter your prefered username: ");
		createUsername = myScanner.scan.nextLine().toString();
		
//		for now, making sure credentials just don't equal current hardcoded version
//		if username doesn't exist, accept

		if (createUsername.equalsIgnoreCase("hc")) {
			System.out.println("Username is not available! Try again please");
			userCreate();
		} else if (createUsername.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			System.out.println("Username is available. Please enter your desired password.");
			createPassword = myScanner.scan.nextLine();
		}
		
		System.out.println("Your username is " + createUsername + " and your password is " + createPassword);
		System.out.println("You are now logged into your new account.");
		UserHome.userWelcome(createUsername);
	}

}

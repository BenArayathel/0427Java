package com.bank.presentation;

import com.bank.tools.myScanner;
import com.bank.tools.quitOption;
import com.maindriver.OLD.UserLogin;
import com.welcomeview.OLD.CreateAccount;



//first view for a user who opened the program
public class WelcomeView {
	
	public static void welcome() {
		//first, determine if this is a new user or return user
		String hasAccount = null;

		System.out.println("Welcome to Beaver Trust Fiduciary");
		System.out.println("Please type and enter 'Quit' at any time to exit.");
		System.out.println("Do you already have an account? Y or N: ");
		
		//takes user input to either log them in or create account
		hasAccount = myScanner.scan.nextLine();
		
		if (hasAccount.equals("Y") || hasAccount.equals("y")) {
			UserLogin.validateUsername();
		} else if (hasAccount.equals("N") || hasAccount.equals("n")) {
			CreateAccount.userCreate();
		} else if (hasAccount.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			welcome();
		}
	}

}

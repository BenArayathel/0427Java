package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class CreateUserAccountView {
	
	public static void userCreate() throws BankException {
		
		// create usi to access methods, and blank user object
		UserServiceInterface usi = new UserServiceImplementation();
		User myUser = new User();
		
		// interface for user
		Main.myLog.info("You are pre-approved to apply for a new account with Beaver Trust Fiduciary!");
		Main.myLog.info("Please enter your preferred username: ");
		myUser.setUsername(Main.scan.nextLine().toString());
		Main.myLog.info("Please enter your preferred password: ");
		myUser.setPassword(Main.scan.nextLine().toString());
		
		//check if they are already in the db
		if (usi.loginUser(myUser.getUsername(), myUser.getPassword())) {
			Main.myLog.error("Existing User");
			Main.myLog.info("User already exists. Log in as existing user from home screen");
			WelcomeView.welcome();
		} else {
			try {
				myUser = usi.createUser(myUser);
				//check if it is true with boolean?
				Main.myLog.info("\nThank you. Application pending. Please check back later to log in to your new account.");				
				Main.myLog.info("\nReturning to Beaver Trust Fiduciary Home Screen");
				WelcomeView.welcome();
			} catch (BankException e) {
				Main.myLog.error(e.getMessage());
				Main.myLog.info("Could not create account, please try again later.");
				CreateUserAccountView.userCreate();
			}			
		}	
	}
}

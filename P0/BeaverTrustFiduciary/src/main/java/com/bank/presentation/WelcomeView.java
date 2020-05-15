package com.bank.presentation;

import com.bank.main.Main;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;
import com.bank.presentation.UserLoginView;


//first view for a user who opened the program
public class WelcomeView {
	
	public static void welcome() throws BankException {
		//first, determine if this is a new user or return user
		String hasAccount = null;

		Main.myLog.info("Welcome to Beaver Trust Fiduciary");
		Main.myLog.info("\nPlease type and enter 'Quit' to return to the home screen.");
		Main.myLog.info("\nDo you already have an account? Y or N: ");
		
		//takes user input to either log them in or create account
		hasAccount = Main.scan.nextLine();
		
		if (hasAccount.equalsIgnoreCase("y")) {
			UserLoginView.validateLogin();
		} else if (hasAccount.equalsIgnoreCase("n")) {
			CreateUserAccountView.userCreate();
		} else if (hasAccount.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			Main.myLog.info("\nPlease enter 'Y' if you have an account, 'N' if you do not have an account yet, or 'Quit' to exit to the main menu.");
			Main.myLog.info(".............................................................");
			welcome();
		}
	}

}

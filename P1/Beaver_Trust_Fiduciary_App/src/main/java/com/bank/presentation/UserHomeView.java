package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class UserHomeView {
	
	public static void userWelcome(User user) throws BankException {
		if (user.getApproved() == 0) {
			Main.myLog.info("\nYour account is still pending. Please try again later.");
			Main.myLog.info("\n..................................................");
			WelcomeView.welcome();
		} else {
			
			// If their account is approved, move on
			String username = user.getUsername();
			String userOptions = null;
			
			Main.myLog.info("Welcome to your account, " + username);
			Main.myLog.info("What would you like to do?");
			Main.myLog.info(".........................................");
			Main.myLog.info("Enter '1' to access your existing accounts.");
			Main.myLog.info("Enter '2' to apply for a new bank account.");
			Main.myLog.info("Or enter 'Quit' to exit.");
			Main.myLog.info(".........................................");
			
			userOptions = Main.scan.nextLine().toString();
			
			// either view accounts, apply for a new one, or quit
			if (userOptions.equalsIgnoreCase("1")) {
				AccountsView.view(user);
			} else if (userOptions.equalsIgnoreCase("2")) {
				try {
					AccountApplication.apply(user);
				} catch (BankException e) {
					Main.myLog.error(e.getMessage() + e.getStackTrace());
					Main.myLog.info("Unable to access accounts.");
					UserHomeView.userWelcome(user);
				}
			} else if (userOptions.equalsIgnoreCase("quit")) {
				QuitOption.quit();
			} else {
				Main.myLog.info("Please try again.");
				Main.myLog.info("\n");
				userWelcome(user);
			}	
		}
	}
}

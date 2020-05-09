package com.bank.presentation;

import com.accounts.OLD.AccountApplication;
import com.bank.main.Main;
import com.bank.tools.QuitOption;

public class UserHomeView {
	
	public static void userWelcome(String username) {
		String userOptions = null;
		userOptions = Main.scan.nextLine().toString();
		
		System.out.println("Welcome to your account, " + username);
		System.out.println("What would you like to do?");
		System.out.println(".........................................");
		System.out.println("Enter '1' to access your existing accounts.");
		System.out.println("Enter '2' to apply for a new bank account.");
		System.out.println("Or, as always, enter 'Quit' to exit.");
		

		
		// either view accounts, apply for a new one, or quit
		if (userOptions.equalsIgnoreCase("1")) {
			AccountsView.view(username);
		} else if (userOptions.equalsIgnoreCase("2")) {
			AccountApplication.apply();
		} else if (userOptions.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			System.out.println("Please try again.");
			userWelcome(username);
		}

	}

}

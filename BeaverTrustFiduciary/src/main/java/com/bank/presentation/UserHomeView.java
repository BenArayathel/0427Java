package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class UserHomeView {
	
	public static void userWelcome(User user) {
		String username = user.getUsername();
		String userOptions = null;
		userOptions = Main.scan.nextLine().toString();
		
		System.out.println("Welcome to your account, " + username);
		System.out.println("What would you like to do?");
		System.out.println(".........................................");
		System.out.println("Enter '1' to access your existing accounts.");
		System.out.println("Enter '2' to apply for a new bank account.");
		System.out.println("Or, as always, enter 'Quit' to exit.");
		System.out.println(".........................................");

		
		// either view accounts, apply for a new one, or quit
		if (userOptions.equalsIgnoreCase("1")) {
			AccountsView.view(user);
		} else if (userOptions.equalsIgnoreCase("2")) {
			try {
				AccountApplication.apply(user);
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (userOptions.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			System.out.println("Please try again.");
			userWelcome(user);
		}

	}

}

package com.bank.presentation;

import com.accounts.OLD.AccountApplication;
import com.bank.tools.myScanner;
import com.bank.tools.quitOption;

public class UserHome {
	
	public static void userWelcome(String username) {
		String userOptions = null;
		
		System.out.println("Welcome, " + username + ".");
		System.out.println("What would you like to do?");
		System.out.println(".........................................");
		System.out.println("Enter '1' to access your accounts.");
		System.out.println("Enter '2' to apply for a new account.");
		System.out.println("Or, as always, enter 'Quit' to exit.");
		
//		Scanner optionsScan = new Scanner(System.in);
		userOptions = myScanner.scan.nextLine().toString();

		
		// either view accounts, apply for a new one, or quit
		if (userOptions.equalsIgnoreCase("1")) {
			AccountsView.view(username);
		} else if (userOptions.equalsIgnoreCase("2")) {
			AccountApplication.apply();
		} else if (userOptions.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			System.out.println("Please try again.");
			userWelcome(username);
		}

	}

}

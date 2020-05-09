package com.bank.presentation;

import java.util.ArrayList;
import java.util.List;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;
//import com.accounts.OLD.Deposit;

public class AccountsView {
	
	public static void view(String username) {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String accountAction = null;
		List<Account> userAccounts = new ArrayList<Account>();

		System.out.println("Here are your accounts, " + username + ".");
		
		// this will be connect to db, iterate and print their accounts
		System.out.println("The Accounts...with balances");
		
		// try to list all accounts related to user's name/id
		try {
			asi.listUserAccounts(username);
			System.out.println("did anything happen?");
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("What would you like to do?");
		System.out.println(".........................................");
		System.out.println("Enter '1' to make a deposit.");
		System.out.println("Enter '2' to make a withdrawal.");
		System.out.println("Enter '3' to transfer funds.");
		System.out.println("Enter '4' to go back.");
		System.out.println("Or, as always, enter 'Quit' to exit.");
		
		accountAction = Main.scan.nextLine().toString();
		
		if (accountAction.equalsIgnoreCase("1")) {
//			Deposit.deposit();			
//		} else if (accountAction.equalsIgnoreCase("2")) {
//			Withdrawal.withdraw();			
//		} else if (accountAction.equalsIgnoreCase("3")) {
//			Transfer.transfer();			
		} else if (accountAction.equalsIgnoreCase("4")) {
			UserHomeView.userWelcome(username);			
		} else if (accountAction.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			System.out.println("Please try again.");
			view(username);
		}
		

	}

}


/* options:
 * 1. withdraw (just subtract up to amount in account
 * 2. deposit (however much they want, add)
 * 3. transfer (up to amount in account, subtract and add)
 * 4. go back to user home (userWelcome(username)
 * 5. quit
 */


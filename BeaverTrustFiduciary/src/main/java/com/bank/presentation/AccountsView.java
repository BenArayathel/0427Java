package com.bank.presentation;

import java.text.DecimalFormat;
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
		System.out.println("\nYour Accounts: ");
		System.out.println("-----------------------------------------");
		
		// list all accounts related to user's name/id
		try {
			List<Account> userAccountsList = asi.listUserAccounts(username);
			for(Account i: userAccountsList) {
				System.out.println("Account: " + i.getAccount_name() + " Balance: $" + i.getBalance());
			}
			
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("\nWhat would you like to do?");
		System.out.println(".........................................");
		System.out.println("Enter '1' to make a deposit.");
		System.out.println("Enter '2' to make a withdrawal.");
		System.out.println("Enter '3' to transfer funds.");
		System.out.println("Enter '4' to go back.");
		System.out.println("Or, as always, enter 'Quit' to exit.");
		
		accountAction = Main.scan.nextLine().toString();
		
		if (accountAction.equalsIgnoreCase("1")) {
			AccountDeposit.deposit(username);			
		} else if (accountAction.equalsIgnoreCase("2")) {
			AccountWithdrawal.withdraw(username);			
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


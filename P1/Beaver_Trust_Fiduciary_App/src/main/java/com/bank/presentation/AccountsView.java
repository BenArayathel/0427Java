package com.bank.presentation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;
//import com.accounts.OLD.Deposit;

public class AccountsView {
	
	public static void view(User user) throws BankException {
		String username = user.getUsername();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String accountAction = null;
		List<Account> userAccounts = new ArrayList<Account>();

		Main.myLog.info("Here are your accounts, " + username + ".");
		
		// this will be connect to db, iterate and print their accounts
		Main.myLog.info("\nYour Accounts: ");
		Main.myLog.info("-----------------------------------------");
		
		// list all accounts related to user's name/id
		try {
			List<Account> userAccountsList = asi.listUserAccounts(username);
			for(Account i: userAccountsList) {
				Main.myLog.info("Account: " + i.getAccount_name() + " Balance: $" + i.getBalance());
			}
			
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			Main.myLog.info("Something went wrong with viewing your accounts.");
			AccountsView.view(user);
		}
				
		Main.myLog.info("\nWhat would you like to do?");
		Main.myLog.info(".........................................");
		Main.myLog.info("Enter '1' to make a deposit.");
		Main.myLog.info("Enter '2' to make a withdrawal.");
		Main.myLog.info("Enter '3' to transfer funds.");
		Main.myLog.info("Enter '4' to go back.");
		Main.myLog.info("Or, as always, enter 'Quit' to exit.");
		
		accountAction = Main.scan.nextLine().toString();
		
		if (accountAction.equalsIgnoreCase("1")) {
			AccountDeposit.deposit(user);			
		} else if (accountAction.equalsIgnoreCase("2")) {
			AccountWithdrawal.withdraw(user);			
		} else if (accountAction.equalsIgnoreCase("3")) {
//			Main.myLog.info("Transfer currently unavailable!");
//			AccountsView.view(user);
			AccountTransfer.transfer(user);			
		} else if (accountAction.equalsIgnoreCase("4")) {
			UserHomeView.userWelcome(user);			
		} else if (accountAction.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			Main.myLog.info("Please try again.");
			view(user);
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


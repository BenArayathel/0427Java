package com.bank.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;

public class AccountDeposit {

	public static void deposit(User user) throws BankException {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String username = user.getUsername();
		String account_name;
		String depositAmount;
		String user_id = user.getUser_id();
		
		Main.myLog.info("Enter the account name that you'd like to deposit into: ");
		account_name = Main.scan.nextLine().toString();
		Main.myLog.info("Enter the amount you are depositing: ");
		depositAmount = Main.scan.nextLine();
		
		
		
		// check that the amount is in money format...
		if (asi.validTransactionFormat(depositAmount)) {
			// ...then that it isn't negative
			if (Double.parseDouble(depositAmount) >= 0) {
				// instantiate the account
//				Account account = asi.listAccountByNameAndUserID(account_name, user_id);
//				// create list of all the user's accounts...
				List<Account> account_list = asi.listUserAccounts(username);
				// ...then a list of all the names from those accounts
				List<String> accountNames = new ArrayList<String>();
				for (Account i : account_list) {
					accountNames.add(i.getAccount_name());
				}
				// check if the name the user entered is in their accounts list
				if (accountNames.contains(account_name)) {
					asi.deposit(user, account_name, depositAmount);
					Main.myLog.info("\nDeposit of $" + depositAmount + " complete!");
					Main.myLog.info("-----------------------------------------------------");
					AccountsView.view(user);							
				} else {
					Main.myLog.info("You don't have an account with that name.");
					AccountDeposit.deposit(user);
				}
			} else {
				Main.myLog.info("Enter an amount greater than $0.");
				AccountDeposit.deposit(user);
			}			
		} else {
			Main.myLog.info("Please format your input as either dollars or dollars and cents");
			AccountDeposit.deposit(user);
		}
	}
}

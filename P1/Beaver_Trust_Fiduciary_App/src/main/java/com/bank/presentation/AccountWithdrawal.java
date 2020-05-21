package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;

public class AccountWithdrawal {
	
	public static void withdraw(User user) throws BankException {
		String username = user.getUsername();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String account_name;
		String withdrawalAmount;
		String user_id = user.getUser_id();
		
		
		Main.myLog.info("Enter the account name that you'd like to withdraw from: ");
		account_name = Main.scan.nextLine().toString();
		Main.myLog.info("Enter the amount you are withdrawing: ");
		withdrawalAmount = Main.scan.nextLine().toString();
		
		// first check that the amount entered is in a valid format
		if (asi.validTransactionFormat(withdrawalAmount)) {
			// then that they aren't withdrawing negative money
			if (Double.parseDouble(withdrawalAmount) >= 0) {
				// use this account object for overdraft check...
				Account account = asi.listAccountByNameAndUserID(account_name, user_id);
				// then that they aren't overdrafting
				if (account.getBalance() >= Double.parseDouble(withdrawalAmount)) {
					try {
						asi.withdraw(user, account_name, withdrawalAmount);
						Main.myLog.info("\nWithdrawal of $" + withdrawalAmount + " complete!");
						Main.myLog.info("-----------------------------------------------------");
						AccountsView.view(user);			
					} catch (BankException e) {
						Main.myLog.error(e.getMessage() + e.getStackTrace());
						Main.myLog.info("Could not complete that withdrawal, please check amount");
						AccountWithdrawal.withdraw(user);
					}
					
				} else {
					Main.myLog.info("Invalid transaction (account name incorrect or insufficient funds).");
					AccountWithdrawal.withdraw(user);
				}
			} else {
				Main.myLog.info("Enter an amount greater than $0.");
				AccountWithdrawal.withdraw(user);
			}			
		} else {
			Main.myLog.info("Please format your input as either dollars or dollars and cents");
			AccountWithdrawal.withdraw(user);
		}
	}
}

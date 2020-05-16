package com.bank.presentation;

import java.util.List;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;

public class AccountTransfer {
	
	public static void transfer(User user) throws BankException {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String username = user.getUsername();
		String accountFromName;
		String accountToName;
		String transferAmount;
				
		try {
			List<Account> userAccountsList = asi.listUserAccounts(username);
			for(Account i: userAccountsList) {
				Main.myLog.info("Account: " + i.getAccount_name() + " Balance: $" + i.getBalance());
			}	
				Main.myLog.info("\n");
				Main.myLog.info("Enter the account name that you'd like to transfer from: ");
				accountFromName = Main.scan.nextLine().toString();
				Main.myLog.info("Enter the account name that you'd like to transfer to: ");
				accountToName = Main.scan.nextLine().toString();
				Main.myLog.info("Enter the amount you are transfering: ");
				transferAmount = Main.scan.nextLine();
				
				if (asi.validTransactionFormat(transferAmount)) {
					if (Double.parseDouble(transferAmount) >= 0) {
						asi.withdraw(user, accountFromName, transferAmount);
						asi.deposit(user, accountToName, transferAmount);
						Main.myLog.info("\nTransfer of $" + transferAmount + " complete!");
						Main.myLog.info("-----------------------------------------------------");
						AccountsView.view(user);			
					} else {
						Main.myLog.info("Enter an amount greater than $0.");
						AccountTransfer.transfer(user);
					}					
				} else {
					Main.myLog.info("Please format your input as either dollars or dollars and cents");
					AccountTransfer.transfer(user);
				}
			
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			Main.myLog.info("Something went wrong with viewing your accounts.");
			UserHomeView.userWelcome(user);
		}
	}
}

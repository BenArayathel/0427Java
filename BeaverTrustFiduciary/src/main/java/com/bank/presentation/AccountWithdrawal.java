package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;

public class AccountWithdrawal {
	
	public static void withdraw(User user) {
		String username = user.getUsername();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String accountName;
		String withdrawalAmount;
		
		Main.myLog.info("Enter the account name that you'd like to withdraw from: ");
		accountName = Main.scan.nextLine().toString();
		Main.myLog.info("Enter the amount you are withdrawing: ");
		withdrawalAmount = Main.scan.nextLine().toString();
		
		asi.withdraw(user, accountName, withdrawalAmount);
		Main.myLog.info("\nWithdrawal of $" + withdrawalAmount + " complete!");
		Main.myLog.info("-----------------------------------------------------");
		AccountsView.view(user);
	}

}

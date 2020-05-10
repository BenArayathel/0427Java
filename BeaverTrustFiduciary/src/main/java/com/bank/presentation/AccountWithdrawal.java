package com.bank.presentation;

import com.bank.main.Main;
import com.bank.service_implementation.AccountServiceImplementation;

public class AccountWithdrawal {
	
	public static void withdraw(String username) {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String accountName;
		String withdrawalAmount;
		
		System.out.println("Enter the account name that you'd like to withdraw from: ");
		accountName = Main.scan.nextLine().toString();
		System.out.println("Enter the amount you are withdrawing: ");
		withdrawalAmount = Main.scan.nextLine().toString();
		
		asi.withdraw(username, accountName, withdrawalAmount);
		System.out.println("\nWithdrawal of $" + withdrawalAmount + " complete!");
		System.out.println("-----------------------------------------------------");
		AccountsView.view(username);
	}

}

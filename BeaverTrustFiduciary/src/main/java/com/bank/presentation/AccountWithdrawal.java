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
		
		System.out.println("Enter the account name that you'd like to withdraw from: ");
		accountName = Main.scan.nextLine().toString();
		System.out.println("Enter the amount you are withdrawing: ");
		withdrawalAmount = Main.scan.nextLine().toString();
		
		asi.withdraw(user, accountName, withdrawalAmount);
		System.out.println("\nWithdrawal of $" + withdrawalAmount + " complete!");
		System.out.println("-----------------------------------------------------");
		AccountsView.view(user);
	}

}

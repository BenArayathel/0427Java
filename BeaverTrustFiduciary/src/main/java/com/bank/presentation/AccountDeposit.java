package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;

public class AccountDeposit {

	public static void deposit(User user) {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String username = user.getUsername();
		String accountName;
		String depositAmount;
		
		System.out.println("Enter the account name that you'd like to deposit into: ");
		accountName = Main.scan.nextLine().toString();
		System.out.println("Enter the amount you are depositing: ");
		depositAmount = Main.scan.nextLine().toString();
		
		asi.deposit(user, accountName, depositAmount);
		System.out.println("\nDeposit of $" + depositAmount + " complete!");
		System.out.println("-----------------------------------------------------");
		AccountsView.view(user);
	}
	
}

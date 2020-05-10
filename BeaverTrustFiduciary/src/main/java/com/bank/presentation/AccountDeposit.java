package com.bank.presentation;

import com.bank.main.Main;
import com.bank.service_implementation.AccountServiceImplementation;

public class AccountDeposit {

	public static void deposit(String username) {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String accountName;
		String depositAmount;
		
		System.out.println("Enter the account name that you'd like to deposit into: ");
		accountName = Main.scan.nextLine().toString();
		System.out.println("Enter the amount you are depositing: ");
		depositAmount = Main.scan.nextLine().toString();
		
		asi.deposit(username, accountName, depositAmount);
		
	}
	
}

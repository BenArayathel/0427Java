package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;

public class AccountApplication {
	
	public static void apply(User user) {
		String username = user.getUsername();
		String accountName;
		String initialDeposit;
		
		System.out.println("What would you like to name your new account?");
		accountName = Main.scan.nextLine();
		System.out.println("How much will you be depositing as your starting balance?");
		initialDeposit = Main.scan.nextLine().toString();
		
		
	}
}

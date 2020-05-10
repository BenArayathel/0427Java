package com.bank.presentation;

import com.bank.main.Main;

public class AccountApplication {
	
	public static void apply(String username) {
		String accountName;
		String initialDeposit;
		
		System.out.println("What would you like to name your new account?");
		accountName = Main.scan.nextLine();
		System.out.println("How much will you be depositing as your starting balance?");
		initialDeposit = Main.scan.nextLine().toString();
		
		
	}
}

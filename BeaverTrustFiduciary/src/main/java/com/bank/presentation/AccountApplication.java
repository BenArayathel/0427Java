package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class AccountApplication {
	
	public static void apply(User user) throws BankException {
		AccountServiceImplementation aci = new AccountServiceImplementation();
		String accountName;
		String initialDeposit;
	
		// maybe use this?
//		if (accountName.equalsIgnoreCase("quit") || initialDeposit.toString().equalsIgnoreCase("quit")) {
//			QuitOption.quit();
//		} else {
		
		System.out.println("What would you like to name your new account?");
		accountName = Main.scan.nextLine();
		System.out.println("How much will you be depositing as your starting balance?");
		initialDeposit = Main.scan.nextLine().toString();
		
		try {
			aci.createAccount(user, accountName, initialDeposit);
			System.out.println("Your new account, " + accountName + " has been added.");
			UserHomeView.userWelcome(user);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BankException("something went wrong with creating your new bank account");
		}
		
	}
}

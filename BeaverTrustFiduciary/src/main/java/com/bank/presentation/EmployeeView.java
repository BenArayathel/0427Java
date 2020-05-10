package com.bank.presentation;

import java.util.List;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class EmployeeView {
	// in this view, stick the logic to 
	// 1. approve accounts (access them and update boolean)
	// 2. read all accounts by inputing user_id, or all accounts in general, both, whatever
	// 3. read in the log of all the transactions 
	// https://stackoverflow.com/questions/13934818/reading-log-file-in-java
	
	public static void Banking() throws BankException {
		UserServiceImplementation usi = new UserServiceImplementation();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String username;
		String showApproval;
		String selection;
		
		System.out.println("welcome to the employee view");
		
		// Listing Users
		try {
			List<User> users = usi.listUsers();
			for (User u: users) {
				if (u.getApproved() == 0) {
					showApproval = "(Account not yet approved)";
				} else {
					showApproval = "(Account Approved)";
				}
				System.out.println("Username: " + u.getUsername() + "............." + showApproval);
			}
		} catch (BankException e) {
			e.printStackTrace();
			throw new BankException("Something wrong with accessing user info.");
		}
		
		
		// Employee options
		System.out.println("........................................");
		System.out.println("Choose an option: ");
		System.out.println("Enter '1' to make see specific user's account details.");
		System.out.println("Enter '2' to approve a new account application.");
		System.out.println("Enter 'quit' to exit to Home screen.");

		
		selection = Main.scan.nextLine();
		
		if (selection.equals(1)) {
			// See customer's accounts
			System.out.println("Access customer's accounts with their Username: ");
			username = Main.scan.nextLine();
			
			// list them by the selected username
			try {
				List<Account> userAccountsList = asi.listUserAccounts(username);
				for(Account i: userAccountsList) {
					System.out.println("Account: " + i.getAccount_name() + "............." + " Balance: $" + i.getBalance());
				}
				
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (selection.equals(2)) {
			
		} else if (selection.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		}
		

		
		// Approve account
		
		
	}

}

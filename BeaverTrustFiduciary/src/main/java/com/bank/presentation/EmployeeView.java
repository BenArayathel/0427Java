package com.bank.presentation;

import java.util.List;

import com.bank.dao_implementation.AccountDAOImplementation;
import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.Transaction;
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
	
	public static void banking() throws BankException {
		UserServiceImplementation usi = new UserServiceImplementation();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		AccountDAOImplementation adi = new AccountDAOImplementation();
		
		String username;
		String user_id;
		String showApproval;
		String selection;
		
		Main.myLog.info("Welcome to the Employee Dashboard");
		// Employee options
		Main.myLog.info("........................................");
		Main.myLog.info("Choose an option: ");
		Main.myLog.info("Enter '1' to make see all customers.");
		Main.myLog.info("Enter '2' to make see specific user's account details.");
		Main.myLog.info("Enter '3' to approve a new account application.");
		Main.myLog.info("Enter '4' to see all transactions.");
		Main.myLog.info("Enter 'quit' to exit to Home screen.");

		
		selection = Main.scan.nextLine().toString();
		
		if (selection.equals("1")) {
			// Listing ALL Users
			try {
				List<User> users = usi.listUsers();
				for (User u: users) {
					if (u.getApproved() == 0) {
						showApproval = "(Account not yet approved)";
					} else {
						showApproval = "(Account Approved)";
					}
					Main.myLog.info("Username: " + u.getUsername() + "...." + "User_Id: " + u.getUser_id() + " " + showApproval);
					Main.myLog.info("...........................................");
				}
			} catch (BankException e) {
				e.printStackTrace();
				throw new BankException("Something wrong with accessing user info.");
			}
			banking();
		// See SPECIFIC customer accounts
		} else if (selection.equals("2")) {
			Main.myLog.info("Access customer's accounts with their Username: ");
			username = Main.scan.nextLine();
			
			// list them by the selected username
			try {
				List<Account> userAccountsList = asi.listUserAccounts(username);
				for(Account i: userAccountsList) {
					Main.myLog.info("Account: " + i.getAccount_name() + "......Account_ID: " + i.getAccount_id() + "......." + " Balance: $" + i.getBalance());
				}
				
			} catch (BankException e) {
				e.printStackTrace();
			}
			banking();
		// APPROVE account //PROBLEM CHILD
		} else if (selection.equals("3")) {
			Main.myLog.info("Approve customer's account by entering their User_ID: ");
			user_id = Main.scan.nextLine().toString();
			adi.approve(user_id);
			Main.myLog.info("Account approved.");
			banking();
		} else if (selection.equals("4")) {
			Main.myLog.info("Transaction Log: ");
			try {
				List<Transaction> allTransactionsList = asi.listAllTransactions();
				for(Transaction t: allTransactionsList) {
					Main.myLog.info(t);
				}
			} catch (BankException e) {
				e.printStackTrace();
			}
		} else if (selection.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			Main.myLog.info("somethign broke");
		}
		

		
		// Approve account
		
		
	}

}

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
		Main.myLog.info("Enter '1' to see all customers.");
		Main.myLog.info("Enter '2' to see specific user's account details.");
		Main.myLog.info("Enter '3' to approve a new account application.");
		Main.myLog.info("Enter '4' to see all transactions.");
		Main.myLog.info("Enter 'Quit' to exit to Home screen.");

		
		selection = Main.scan.nextLine().toString();
		
		// Listing ALL Users
		if (selection.equals("1")) {
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
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				Main.myLog.info("Something wrong with accessing user info.");
				EmployeeView.banking();
			}
			banking();
		// See SPECIFIC customer accounts
		} else if (selection.equals("2")) {
			Main.myLog.info("Access customer's accounts with their Username: ");
			username = Main.scan.nextLine();
			// LIST ACCOUNTS by the selected USERNAME
			try {
				List<Account> userAccountsList = asi.listUserAccounts(username);
				for(Account i: userAccountsList) {
					Main.myLog.info("Account: " + i.getAccount_name() + "......Account_ID: " + i.getAccount_id() + "......." + " Balance: $" + i.getBalance());
				}
				
			} catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				Main.myLog.info("Unable to list user accounts");
				EmployeeView.banking();
			}
			banking();
		// APPROVE ACCOUNT
		} else if (selection.equals("3")) {
			List<User> users = usi.listUsers();
			for (User u: users) {
				if (u.getApproved() == 0) {
					showApproval = "(Account not yet approved)";
				} else {
					showApproval = "(Account Approved)";
				}
				Main.myLog.info("Username: " + u.getUsername() + " " + "User_Id: " + u.getUser_id() + " " + showApproval);
				Main.myLog.info("...........................................");
			}
			Main.myLog.info("Approve customer's account by entering their User_ID: ");
			user_id = Main.scan.nextLine().toString();
			try {
				usi.approve(user_id);		
			} catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				Main.myLog.info("Unable to approve that account.");
				banking();
			}
			Main.myLog.info("\nAccount approved.");
			banking();
			// See a LOG of all TRANSACTIONS
		} else if (selection.equals("4")) {
			Main.myLog.info("Transaction Log: ");
			try {
				List<Transaction> allTransactionsList = asi.listAllTransactions();
				for(Transaction t: allTransactionsList) {
					Main.myLog.info(t);
				}
				banking();
			} catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				Main.myLog.info("Unable to print logs.");
				EmployeeView.banking();
			}
		} else if (selection.equalsIgnoreCase("quit")) {
			QuitOption.quit();
		} else {
			Main.myLog.info("\nSomething didn't work, try again please.");
			Main.myLog.info("\n..............................................");
			banking();
		}
	}
}

package com.bank.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.presentation.AccountsView;
import com.bank.presentation.WelcomeView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.service_interface.AccountServiceInterface;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;

public class Main {
	
	final static Logger myLog = Logger.getLogger(Main.class);
	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
     	// central hub for behavior
		// should have very few commands, just start the ball rolling
	
//		if (myLog.isInfoEnabled()) {
//			myLog.info("Hey, it workith!");			
//		}
//				
//		myLog.warn("HEY!");
		
	     
	    //my logger only works if my call to this object/method comes after the logging stuff!
//		WelcomeView.welcome();
		AccountsView.view("ethan1");


		UserServiceInterface usi = new UserServiceImplementation();
		User myUser = new User();
		
//		System.out.println("enter new username");
//		myUser.setUsername(scan.nextLine());
//		System.out.println("enter new password");
//		myUser.setPassword(scan.nextLine());
//		
//		try {
//			myUser = usi.createUser(myUser);
//			System.out.println("main level works");
//		} catch (BankException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		// Listing Users
//		UserServiceImplementation usi = new UserServiceImplementation();
//		
//		try {
//			List<User> users = usi.listUsers();
//		} catch (BankException e) {
//			e.printStackTrace();
//		}
		
		// TRYING SAME STUFF WITH ACCOUNT
		
		AccountServiceInterface asi = new AccountServiceImplementation();
		Account newAccount = new Account();
		
//		System.out.println("enter new account user_id");
//		newAccount.setUser_id(scan.nextLine().toString());
//		System.out.println("enter new account name");
//		newAccount.setAccount_name(scan.nextLine());
//		System.out.println("your new account starting balance will be $50.");
//		newAccount.setBalance(50.00);
//		
		// CREATE new account
//		try {
//			newAccount = asi.createAccount(newAccount);
//		} catch (BankException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		// READ all accounts
//		try {
//			asi.listAccounts();
//		} catch (BankException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	

	}

}

package com.application.bank;

import java.util.Scanner;
import java.io.Serializable;
import java.util.Random;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Customer extends User implements Serializable {
	private boolean accountActive;
	// private boolean awaitingApproval; // for pending transactions if we get that far
	//private Transaction transaction = new Transaction();
	final static Logger loggy = Logger.getLogger(Customer.class);
	
	private static final long serialVersionUID = 620321235045377344L;
	
	public Customer() {
		loggy.info("Starting new customer registration");
		newCustomerRegistration();
	}


	public boolean getAccountActive() {
		return accountActive;
	}



	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}



	public Customer(String name, String email, String phoneNumber, String password, Account account) {
		super(name, email, phoneNumber, password, account);
		this.accountActive = false;
		this.setStatus("customer");
		//this.awaitingApproval = true;
	}
	
	public static Customer newCustomerRegistration() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter your full name");
		String n = sc.nextLine();
		System.out.println("Please enter your email");
		String e = sc.nextLine();
		System.out.println("Please enter your phone number. Ex 3048675309");
		String pN = sc.nextLine();
		System.out.println("Create a password");
		String pW = sc.nextLine();
		System.out.println("How much would you like to put into your checking account?");
		String checkingMoney = sc.nextLine();
		
		int newSavingsNum = generateAccountNumber();
		int newCheckingNum = generateAccountNumber();
		
		Account act = new Account(newSavingsNum, newCheckingNum, 0.00, Integer.parseInt(checkingMoney), e);
		
		Customer c = new Customer(n, e, pN, pW, act);
		loggy.info("new customer and associated account created");
		System.out.println("Created new object- " + c );
		System.out.println();
		System.out.println("Before addNewCustomer() - " + Lobby.readCustomers("./customerRecords"));
		Lobby.addNewCustomer("./customerRecords.txt", c);
		System.out.println();
		System.out.println("After addNewCustomer() - " + Lobby.readCustomers("./customerRecords"));
		sc.close();
		return c;
	}
	
	public static int generateAccountNumber() {
		Random rand = new Random();
		int randomAccountNumber = rand.nextInt(100000);
		
		return randomAccountNumber;
	}



	@Override
	public String toString() {
		return "Customer [accountActive=" + accountActive + ", getName()=" + getName() + ", getEmail()=" + getEmail()
				+ ", getPhoneNumber()=" + getPhoneNumber() + ", getStatus()=" + getStatus() + ",  getPassword()=" + getPassword() + "]";
	}
	
	

}


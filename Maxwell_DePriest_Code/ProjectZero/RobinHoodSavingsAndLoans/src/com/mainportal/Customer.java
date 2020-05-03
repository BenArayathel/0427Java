package com.mainportal;

import java.util.Scanner;
import java.util.Random;

public class Customer extends User {
	private boolean accountActive;
	private boolean awaitingApproval;
	private Transaction transaction = new Transaction();
	private int startingBalance;
	
	public Customer() {
		
	}

	

	public Customer(String name, String email, String phoneNumber, String password, Account account) {
		super(name, email, phoneNumber, password, account);
		this.accountActive = accountActive;
		this.awaitingApproval = awaitingApproval;
//		this.startingBalance = startingBalance;
		this.accountActive = false;
		this.awaitingApproval = true;
	}
	
	public Customer newUserRegistration() {
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
		
		return c;
	}
	
	public int generateAccountNumber() {
		Random rand = new Random();
		int randomAccountNumber = rand.nextInt(100000);
		
		return randomAccountNumber;
	}

}

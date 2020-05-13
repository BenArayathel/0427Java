package com.ploutos.model;

public class Account implements Comparable<Account>{ // A BANK account, not a user login.
	private long accountNumber;
	private String userID;
	private int balance;
	
	public Account(long accountNumber, String userID, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.userID = userID;
		this.balance = balance;
	}
	
	public Account(String userID, int balance) {
		super();
		this.userID = userID;
		this.balance = balance;
	}
	
	public Account() {
		super();
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", userID=" + userID + ", balance=" + balance + "]";
	}

	@Override
	public int compareTo(Account o) {
		return ((Long) this.accountNumber).compareTo(o.getAccountNumber());
	}
}

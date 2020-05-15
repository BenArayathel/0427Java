package com.bank.models;

public class Account {
	
	//FIELDS
	private String account_id;
	private String user_id;
	private String account_name;
	private double balance;
	
	//CONSTRUCTORS	
	public Account() {
		super();
	}
	
	public Account(String account_name, double balance) {
		this.account_name = account_name;
		this.balance = balance;
	}

	public Account(String account_id, String user_id, String account_name, double balance) {
		super();
		this.account_id = account_id;
		this.user_id = user_id;
		this.account_name = account_name;
		this.balance = balance;
	}

	
	//GETTERS and SETTERS
	public String getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getAccount_name() {
		return account_name;
	}
	
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	//TOSTRING
//	@Override
//	public String toString() {
//		return "Account [account_id=" + account_id + ", user_id=" + user_id + ", account_name=" + account_name
//				+ ", balance=" + balance + "]";
//	}


	
	
}

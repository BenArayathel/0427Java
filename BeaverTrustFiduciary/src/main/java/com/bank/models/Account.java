package com.bank.models;

public class Account {
	
	//FIELDS
	private int account_id;
	private int user_id;
	private String account_name;
	private double balance;
	private boolean approved;
	
	//CONSTRUCTORS	
	public Account() {
		super();
	}

	public Account(int account_id, int user_id, String account_name, double balance, boolean approved) {
		super();
		this.account_id = account_id;
		this.user_id = user_id;
		this.account_name = account_name;
		this.balance = balance;
		this.approved = approved;
	}

	
	//GETTERS and SETTERS
	public int getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
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
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", user_id=" + user_id + ", account_name=" + account_name
				+ ", balance=" + balance + ", approved=" + approved + "]";
	}
	
	
}

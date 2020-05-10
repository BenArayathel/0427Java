package com.bank.models;

public class Transaction {
	
	//FIELDS
	private String transaction_id;
	private double amount;
	private String account_name;
	private String user_id;
	private String type;
	
	
	// CONSTRUCTORS
	public Transaction() {
		super();
	}
	
	public Transaction(String transaction_id, double amount, String account_name, String user_id, String type) {
		super();
		this.transaction_id = transaction_id;
		this.amount = amount;
		this.account_name = account_name;
		this.user_id = user_id;
		this.type = type;
	}
	
	
	// GETTERS and SETTERS
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	// TO STRING OVERRIDE
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", amount=" + amount + ", account_name=" + account_name
				+ ", user_id=" + user_id + ", type=" + type + "]";
	}
	
	

}

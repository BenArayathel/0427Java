package com.bank.model;


public class Customer {
	
	private String username;
	private String password;
	private String accountBalance;
	private String accountNumber;
	private String approved;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public Customer(String username, String password, String accountBalance, String accountNumber, String approved) {
		super();
		this.username = username;
		this.password = password;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
		this.approved = approved;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", accountBalance=" + accountBalance
				+ ", accountNumber=" + accountNumber + ", approved=" + approved + "]";
	}

	
	

}

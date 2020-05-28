package com.models;

public class Account {
	private String accountNumber;
	private String savingsAmount;
	private String checkingAmount;
	private String accountType;
	
	
	public Account() {
		
	}


	public Account(String accountNumber, String savingsAmount, String checkingAmount, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.savingsAmount = savingsAmount;
		this.checkingAmount = checkingAmount;
		this.accountType = accountType;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getSavingsAmount() {
		return savingsAmount;
	}


	public void setSavingsAmount(String savingsAmount) {
		this.savingsAmount = savingsAmount;
	}


	public String getCheckingAmount() {
		return checkingAmount;
	}


	public void setCheckingAmount(String checkingAmount) {
		this.checkingAmount = checkingAmount;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	
}

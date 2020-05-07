package com.application.bank.models;

public class Account {
	private int id;
	private int savingsAccountNumber;
	private int checkingAccountNumber;
	private double checkingBalance;
	private double savingsBalance;
	private boolean active;
	private String userEmail;

	public Account() {
		
	}

	public Account(int id, int savingsAccountNumber, int checkingAccountNumber, double checkingBalance, double savingsBalance,
			boolean active, String userEmail) {
		super();
		this.id = id;
		this.savingsAccountNumber = savingsAccountNumber;
		this.checkingAccountNumber = checkingAccountNumber;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		this.active = active;
		this.userEmail = userEmail;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSavingsAccountNumber() {
		return savingsAccountNumber;
	}

	public void setSavingsAccountNumber(int savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}

	public int getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(int checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", savingsAccountNumber=" + savingsAccountNumber + ", checkingAccountNumber="
				+ checkingAccountNumber + ", checkingBalance=" + checkingBalance + ", savingsBalance=" + savingsBalance
				+ ", active=" + active + ", userEmail=" + userEmail + "]";
	}
	
	

}

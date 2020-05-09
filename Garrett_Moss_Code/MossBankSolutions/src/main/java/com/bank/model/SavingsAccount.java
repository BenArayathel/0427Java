package com.bank.model;

public class SavingsAccount {

	private int savingsAccountNumber;
	private String name;
	private int accountBalance;
	
	public SavingsAccount() {
		
	}

	public int getSavingsAccountNumber() {
		return savingsAccountNumber;
	}

	public void setSavingsAccountNumber(int savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "SavingsAccount [savingsAccountNumber=" + savingsAccountNumber + ", name=" + name + ", accountBalance="
				+ accountBalance + "]";
	}
	
	
}

package com.bank.model;

public class CheckingAccount {

	private int checkingAccountNumber;
	private String name;
	private int accountBalance;
	
	public CheckingAccount() {
		
	}

	public int getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(int checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
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
		return "CheckingAccount [checkingAccountNumber=" + checkingAccountNumber + ", name=" + name
				+ ", accountBalance=" + accountBalance + "]";
	}
	
}

package com.mainportal;

public class Account implements Bank {
	
	private int savingsAccountNumber;
	private int checkingAccountNumber;
	private double checkingBalance;
	private double savingsBalance;
	private boolean active;
	private String userEmail;

	public Account() {
		
	}

	public Account(int savingsAccountNumber, int checkingAccountNumber, double savingsBalance, double checkingBalance, String userEmail) {
		this.savingsAccountNumber = savingsAccountNumber;
		this.checkingAccountNumber = checkingAccountNumber;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		this.userEmail = userEmail;
		this.active = false;
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
	
	public double getSavingsBalance() {
		return savingsBalance;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	// 1 = checking account, 2 = savings account 
	public void setBalance(double amt, int acct) {
		if (acct == 1) {
			this.checkingBalance = amt;
		}
		else if (acct == 2) {
			this.savingsBalance = amt;
		}
	}

	@Override
	public double checkBalance(int c) {
		if (c == 1) {
			return this.getCheckingBalance();
		}
		else if (c == 2) {
			return this.getSavingsBalance();
		}
		
		else {
			System.out.println("Invalid entry. Please try again.");
			return 0.0;
		}
	}
	
	public double withdrawMoney(int amt, int acct) {
		double temp = this.checkBalance(acct) - amt;
		this.setBalance(temp, acct);
		System.out.printf("Your account now has $%.2f", this.checkBalance(acct));
		return amt;
	}
	
	public void depositMoney(int amt, int acct) {
		double temp = this.checkBalance(acct) + amt;
		this.setBalance(temp, acct);
		System.out.printf("Your account now has $%.2f", this.checkBalance(acct));
		
	}

	/*
	 * Sends funds to another account number with sender/receiver's names. 
	 * fromWhere variable - Checking = 1, Savings = 2
	 */
	@Override
	public void transferFunds(int destination, int fromWhere, double amount) {
		double newBalance = 0.0;
		if (fromWhere == 1) {
			newBalance = this.getCheckingBalance() - amount;
			this.setBalance(newBalance, 1);
			//generate a transaction object with users names, account nums (transient), and amount
			// receiving user adds to balance
		}
		else if (fromWhere == 2) {
			newBalance = this.getSavingsBalance() - amount;
			this.setBalance(newBalance, 2);
		}
		else {
			System.out.println("This number doesn't match our records. Please try again.");
		}
		
	}

	@Override
	public void receiveFunds() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

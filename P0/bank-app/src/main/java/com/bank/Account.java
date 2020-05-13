package com.bank;

public class Account {

	private int accountNumber;
	private transient int pinNumber;
	private double savingsAmount;
	private double checkingAmount;
	
	public Account() {
		
	}

	/**
	 * 
	 * @param accountNumber The number of the account currently being accessed
	 * @param pinNumber The corresponding pin associated with current account
	 * @param savingsAmount The amount in the savings account
	 * @param checkingAmount The amount in the checking account
	 */
	public Account(int accountNumber, int pinNumber, double savingsAmount, double checkingAmount) {
		super();
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.savingsAmount = savingsAmount;
		this.checkingAmount = checkingAmount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public double getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(double savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	public double getCheckingAmount() {
		return checkingAmount;
	}

	public void setCheckingAmount(double checkingAmount) {
		this.checkingAmount = checkingAmount;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", savingsAmount=" + savingsAmount + ", checkingAmount="
				+ checkingAmount + "]";
	}
	
}

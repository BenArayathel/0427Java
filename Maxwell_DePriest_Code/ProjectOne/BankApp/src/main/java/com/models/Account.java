package com.models;

import java.text.DecimalFormat;
import org.apache.log4j.Logger;

public class Account {
	private String id;
	private String savingsAccountNumber;
	private String checkingAccountNumber;
	private String checkingBalance;
	private String savingsBalance;
	private String active;
	private String email;
	
	DecimalFormat df = new DecimalFormat("####.##");

	public Account() {
		
	}

	public Account(String id, String savingsAccountNumber, String checkingAccountNumber, String checkingBalance, String savingsBalance,
			String active, String email) {
		super();
		this.id = id;
		this.savingsAccountNumber = savingsAccountNumber;
		this.checkingAccountNumber = checkingAccountNumber;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		this.active = active;
		this.email = email;
	}

	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSavingsAccountNumber() {
		return savingsAccountNumber;
	}

	public void setSavingsAccountNumber(String savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}

	public String getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(String checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}

	public String getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(String checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public String getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(String savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account id = " + id + ", Savings account number = " + savingsAccountNumber + ", Checking account number = "
				+ checkingAccountNumber + ", Checking account balance = $" + df.format(checkingBalance) + ", Savings account balance = $" + df.format(savingsBalance)
				+ ", Account active = " + active + ", User email = " + email + "]";
	}
	
	

}

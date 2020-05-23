package com.models;

import java.text.DecimalFormat;

public class CurrentUser {

	private String email;
	private String name;
	private String checkingBalance;
	private String savingsBalance;
	DecimalFormat df = new DecimalFormat("####.##");

	public CurrentUser() {
		
	}
	
	public CurrentUser(String email, String name, String checkingBalance, String savingsBalance) {
		this.email = email;
		this.name = name;
		double cB = Double.parseDouble(checkingBalance);
		double sB = Double.parseDouble(savingsBalance);
		this.checkingBalance = df.format(cB);
		this.savingsBalance = df.format(sB);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	

}

package com.models;

import java.text.DecimalFormat;

public class CurrentUser {

	private String email;
	private String name;
	private String checkingBalance;
	private String savingsBalance;
	private String status;
	DecimalFormat df = new DecimalFormat("###0.00");

	public CurrentUser() {
		
	}
	
	public CurrentUser(String email, String name, String checkBalance, String saveBalance, String status) {
		this.email = email;
		this.name = name;
		double cB = Double.parseDouble(checkBalance);
		double sB = Double.parseDouble(saveBalance);
		this.checkingBalance = df.format(cB);
		this.savingsBalance = df.format(sB);
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}

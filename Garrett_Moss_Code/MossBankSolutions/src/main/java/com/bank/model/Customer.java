package com.bank.model;

import java.util.Date;


public class Customer {
	
	private String username;
	private String password;
	private String name;
	private Date birthdate;
	private String address;
	private int checkingAccountNumber;
	private int savingsAccountNumber;
	
	
	public Customer() {
		
	}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCheckingAccountNumber() {
		return checkingAccountNumber;
	}
	public void setCheckingAccountNumber(int checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}
	public int getSavingsAccountNumber() {
		return savingsAccountNumber;
	}
	public void setSavingsAccountNumber(int savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", name=" + name + ", birthdate="
				+ birthdate + ", address=" + address + ", checkingAccountNumber=" + checkingAccountNumber
				+ ", savingsAccountNumber=" + savingsAccountNumber + "]";
	}
	
	
	

}

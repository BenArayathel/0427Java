package com.mainportal;

import java.lang.StringBuilder;

public class User implements Bank{
	// Stretch goal - implement some basic hashing for password
	
	private String name;
	private String email;
	private String phoneNumber;
	private String status; // Employee or Customer
	private String password;
	private Account account;
	//private int pinNumber;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email, String phoneNumber, String status, String password, Account account) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.password = password;
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public double checkBalance(int c) {
		return this.getAccount().checkBalance(c);
	}

	@Override
	public void transferFunds(int destination, int fromWhere, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveFunds() {
		// TODO Auto-generated method stub
		
	}
	
	public String passwordEncryption(String pw) {
		StringBuilder newPassword = new StringBuilder();
		String original = "abcdefghijklmnopqrstuvwxyz";
		String alternate = "zyxwvutsrqponmlkjihgfedcba";
		String[] arr = alternate.split("");
		String[] wordArray = pw.split("");
		int tempIndex;
		pw = "abcdefg";
		for(int k = 0; k < (pw.length()); k++) {
			String tempLetter = wordArray[k];
			tempIndex = original.indexOf(tempLetter); 
			newPassword.append(arr[tempIndex]);
		}
		return newPassword.toString();
		
	}

	
}

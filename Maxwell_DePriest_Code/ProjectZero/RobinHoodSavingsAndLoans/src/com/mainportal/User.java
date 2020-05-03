package com.mainportal;

import java.lang.StringBuilder;

public class User implements Bank{

	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private Account account;

	public User() {

	}

	public User(String name, String email, String phoneNumber, String password, Account account) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = passwordEncryption(password);
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
		String[] wordArray = pw.toLowerCase().split("");
		int tempIndex;
		for(int k = 0; k < (wordArray.length); k++) {
			String tempLetter = wordArray[k];
			tempIndex = original.indexOf(tempLetter); 
			newPassword.append(arr[tempIndex]);
		}
		return newPassword.toString();
		
	}

	
}

package com.application.bank;

import java.io.Serializable;
import java.lang.StringBuilder;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class User implements Bank, Serializable{

	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private Account account;
	private String status;
	
	private static final long serialVersionUID = 620321243045379944L;
	final static Logger loggy = Logger.getLogger(User.class);
	

	public User() {

	}

	public User(String name, String email, String phoneNumber, String password, Account account) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;  //passwordEncryption(password); // Can bring on encryption if we get regular working
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public double checkBalance(int c) {
		return this.getAccount().checkBalance(c);
	}

	@Override
	public void transferFunds(int destination, int fromWhere, double amount) {
		
	}

	@Override
	public void receiveFunds() {
		// TODO Auto-generated method stub
		
	}
	
//	public void addRecord()
//	
//	public void updateRecord()
	
	public static String passwordEncryption(String pw) {
		loggy.info("Encrypting password");
		StringBuilder newPassword = new StringBuilder();
		String original = "abcdefghijklmnopqrstuvwxyz0987654321";
		String alternate = "1234567890zyxwvutsrqponmlkjihgfedcba";
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
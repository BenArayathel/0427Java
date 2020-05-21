package com.example.models;

public class User {
	
	//FIELDS
	private String username;
	private String password;
	
	// Constructors
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	// Getters and Settrs
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

	// TOSTRING
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
		
}
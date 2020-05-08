package com.bank.models;

public class User {
	
	//FIELDS
	private int user_id;
	private String username;
	private String password;
	
	//CONSTRUCTORS
	public User() {
		super();
	}

	public User(int user_id, String username, String password) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
	}

	
	//GETTERS and SETTERS
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	//TOSTRING
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + "]";
	}
	
	

}

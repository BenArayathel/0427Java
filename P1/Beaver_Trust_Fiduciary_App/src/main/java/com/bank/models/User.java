package com.bank.models;

public class User {
	
	//FIELDS
	private String user_id;
	private String username;
	private String password;
	private int approved;
	
	//CONSTRUCTORS
	public User() {
		super();
	}

	public User(String user_id, String username, String password, int approved) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.approved = approved;
	}

	
	//GETTERS and SETTERS
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String string) {
		this.user_id = string;
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
	
	public int getApproved() {
		return approved;
	}
	
	public void setApproved(int approved) {
		this.approved = approved;
	}


	//TOSTRING
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", approved="
				+ approved + "]";
	}

	
	

}

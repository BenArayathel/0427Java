package com.ploutos.model;

public class Login implements Comparable<Login> {
	private String username;
	private String password;
	private int isActive;
	
	public Login() {
		
	}
	
	public Login(String username, String password, int isActive) {
		super();
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}
	
	public Login(String username, String password) {
		this(username,password,0);
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
	
	public int isActive() {
		return isActive;
	}
	
	public void setActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", isActive=" + isActive + "]";
	}
	
	@Override
	public int compareTo(Login other) {
		return this.username.compareTo(other.getUsername());
	}
}

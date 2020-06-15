package com.friendshipBank.model;

public class users {
	String userName;
	String userPassword;
	
	public users() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "users [userName=" + userName + ", userPassword=" + userPassword + "]";
	}
	
	

}

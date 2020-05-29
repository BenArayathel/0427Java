package com.friendshipBank.model;

public class newUserLogin {
	String userName;
	String userPassword;
	String userPassCon;
	
	public newUserLogin() {
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

	public String getUserPassCon() {
		return userPassCon;
	}

	public void setUserPassCon(String userPassCon) {
		this.userPassCon = userPassCon;
	}

	@Override
	public String toString() {
		return "newUserLogin [userName=" + userName + ", userPassword=" + userPassword + ", userPassCon=" + userPassCon
				+ "]";
	}
	
	

}

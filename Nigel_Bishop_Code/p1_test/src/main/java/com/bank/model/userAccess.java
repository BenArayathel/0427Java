package com.bank.model;

public class userAccess 
{
	
	private String userID;
    private String customerID;
    private String userName;
    private String userPassword;
    private String accountStatus;
	
	public userAccess() {
		// TODO Auto-generated constructor stub
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "userAccess [userID = " + userID + ", customerID = " + customerID + ", userName = " + userName
				+ ", userPassword = " + userPassword + ", accountStatus = " + accountStatus + "]";
	}
	
	
	


}

package com.friendshipBank.model;

public class bankAccount 
{
	private String accountID;
	private String customerID;
	private String accountType;
	private Double balance;
	private String accountStatus;
	
	public bankAccount() {
		// TODO Auto-generated constructor stub
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "bankAccount [accountID=" + accountID + ", customerID=" + customerID + ", accountType=" + accountType
				+ ", balance=" + balance + ", accountStatus=" + accountStatus + "]";
	}
	
	
	


}

package com.friendshipBank.model;

import java.sql.Date;

public class transaction 
{
	private String transactionID;
	private String accountID;
	private String customerID;
	private String accountType;
	private Double balance;
	private Double transAmount;
	private String transType;
	private Date transDate;
	

	
	public transaction() {
	}



	public String getTransactionID() {
		return transactionID;
	}



	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
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



	public Double getTransAmount() {
		return transAmount;
	}



	public void setTransAmount(Double transAmount) {
		this.transAmount = transAmount;
	}



	public String getTransType() {
		return transType;
	}



	public void setTransType(String transType) {
		this.transType = transType;
	}



	public Date getTransDate() {
		return transDate;
	}



	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}



	@Override
	public String toString() {
		return "transaction [transactionID=" + transactionID + ", accountID=" + accountID + ", customerID=" + customerID
				+ ", accountType=" + accountType + ", balance=" + balance + ", transAmount=" + transAmount
				+ ", transType=" + transType + ", transDate=" + transDate + "]";
	}


	
	
	
}

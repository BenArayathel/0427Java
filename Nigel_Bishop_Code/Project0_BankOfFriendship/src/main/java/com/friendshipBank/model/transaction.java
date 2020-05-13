package com.friendshipBank.model;

public class transaction 
{
	private String transactionID;
	private String accountID;
	private String accountType;
	private Double balance;
	private Double withdrawl;
	private Double deposit;
	private Double transfer;
	
	public transaction() {
		// TODO Auto-generated constructor stub
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

	public Double getWithdrawl() {
		return withdrawl;
	}

	public void setWithdrawl(Double withdrawl) {
		this.withdrawl = withdrawl;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getTransfer() {
		return transfer;
	}

	public void setTransfer(Double transfer) {
		this.transfer = transfer;
	}
	
	
	
	
}

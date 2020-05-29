package com.bank.model;

public class Transactions {

	private String accountNumber;
	private String transactionType;
	private String TransactionAmount;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionAmount() {
		return TransactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		TransactionAmount = transactionAmount;
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Transactions [accountNumber=" + accountNumber + ", transactionType=" + transactionType
				+ ", TransactionAmount=" + TransactionAmount + "]";
	}
	public Transactions(String accountNumber, String transactionType, String transactionAmount) {
		super();
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		TransactionAmount = transactionAmount;
	}
	
	
}

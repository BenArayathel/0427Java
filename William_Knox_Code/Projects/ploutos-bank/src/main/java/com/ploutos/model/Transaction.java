package com.ploutos.model;

public class Transaction {
	private int transactionID;
	private long toAccountNum;
	private long fromAccountNum;
	private int amount;
	
	
	
	public Transaction(int transactionID, long toAccountNum, long fromAccountNum, int amount) {
		super();
		this.transactionID = transactionID;
		this.toAccountNum = toAccountNum;
		this.fromAccountNum = fromAccountNum;
		this.amount = amount;
	}

	public Transaction(long toAccountNum, long fromAccountNum, int amount) {
		super();
		this.toAccountNum = toAccountNum;
		this.fromAccountNum = fromAccountNum;
		this.amount = amount;
	}

	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public long getToAccountNum() {
		return toAccountNum;
	}
	
	public void setToAccountNum(int toAccountNum) {
		this.toAccountNum = toAccountNum;
	}
	
	public long getFromAccountNum() {
		return fromAccountNum;
	}
	
	public void setFromAccountNum(int fromAccountNum) {
		this.fromAccountNum = fromAccountNum;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", toAccountNum=" + toAccountNum + ", fromAccountNum="
				+ fromAccountNum + ", amount=" + amount + "]";
	}
}

package com.bankofben.models;

import java.sql.Timestamp;

public class Transaction implements Comparable<Transaction> {
	
	private final String transactionId;
	private final Timestamp timestamp;
	private final Long accountNumber;
	private final double initialBalance;
	private final double amount;
	private final double finalBalance;
	private final long otherAccountNumber;
	
	
	public Transaction(String transactionId, Timestamp timestamp, Long accountNumber, double initialBalance,
			double amount, long otherAccountNumber) {
		super();
		this.transactionId = transactionId;
		this.timestamp = timestamp;
		this.accountNumber = accountNumber;
		this.initialBalance = initialBalance;
		this.amount = amount;
		this.finalBalance = initialBalance + amount;
		this.otherAccountNumber = otherAccountNumber;
	}
	
	
	public Transaction(Long accountNumber, double initialBalance, double amount, long otherAccountNumber) {
		super();
		this.transactionId = null;
		this.timestamp = null;
		this.accountNumber = accountNumber;
		this.initialBalance = initialBalance;
		this.amount = amount;
		this.finalBalance = initialBalance+amount;
		this.otherAccountNumber = otherAccountNumber;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public Long getAccountNumber() {
		return accountNumber;
	}


	public double getInitialBalance() {
		return initialBalance;
	}


	public double getAmount() {
		return amount;
	}


	public double getFinalBalance() {
		return finalBalance;
	}


	public long getOtherAccountNumber() {
		return otherAccountNumber;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", timestamp=" + timestamp + ", accountNumber="
				+ accountNumber + ", initialBalance=" + initialBalance + ", amount=" + amount + ", finalBalance="
				+ finalBalance + ", otherAccountNumber=" + otherAccountNumber + "]";
	}


	@Override
	public int compareTo(Transaction t) {
		Timestamp s1 = this.timestamp;
		Timestamp s2 = t.getTimestamp();
		// will compare the id of the product that calls compareTo and the passed in product
		return s2.compareTo(s1);
	}
	
	
}

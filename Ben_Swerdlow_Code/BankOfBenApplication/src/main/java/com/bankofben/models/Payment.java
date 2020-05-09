package com.bankofben.models;

public class Payment extends Transfer {
	
	private final long payingAccountNumber;
	private final long receivingAccountNumber;
	private final double amount;
	
	public Payment(String transferId, String initUserId, boolean pending, long payingAccountNumber,
			long receivingAccountNumber, double amount) {
		super(transferId, initUserId, pending);
		this.payingAccountNumber = payingAccountNumber;
		this.receivingAccountNumber = receivingAccountNumber;
		this.amount = amount;
	}

	public long getPayingAccountNumber() {
		return payingAccountNumber;
	}

	public long getReceivingAccountNumber() {
		return receivingAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

}

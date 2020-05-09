package com.bankofben.models;

public class Request extends Transfer {
	
	private final long requestorAccountNumber;
	private final long soughtAccountNumber;
	private final double amount;
	
	public Request(String transferId, String initUserId, boolean pending, long requestorAccountNumber,
			long soughtAccountNumber, double amount) {
		super(transferId, initUserId, pending);
		this.requestorAccountNumber = requestorAccountNumber;
		this.soughtAccountNumber = soughtAccountNumber;
		this.amount = amount;
	}

	public long getRequestorAccountNumber() {
		return requestorAccountNumber;
	}

	public long getSoughtAccountNumber() {
		return soughtAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

}

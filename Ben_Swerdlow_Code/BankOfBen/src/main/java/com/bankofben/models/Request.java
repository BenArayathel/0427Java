package com.bankofben.models;

public class Request extends Transfer {
	
	private final long requestorAccountNumber;
	private final long soughtAccountNumber;
	private final double amount;
	
	public Request(String id, String initUserId, boolean pending, long requestorAccountNumber,
			long soughtAccountNumber, double amount) {
		super(id, initUserId, pending);
		this.requestorAccountNumber = requestorAccountNumber;
		this.soughtAccountNumber = soughtAccountNumber;
		this.amount = amount;
	}
	
	public Request(String initUserId, boolean pending, long requestorAccountNumber,
			long soughtAccountNumber, double amount) {
		super(initUserId, pending);
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

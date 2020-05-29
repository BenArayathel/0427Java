package com.friendshipBank.model;

public class Transfer {
	
	private String source;
	private String destination;
	private Double transferAmount;

	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

	@Override
	public String toString() {
		return "Transfer [source=" + source + ", destination=" + destination + ", transferAmount=" + transferAmount
				+ "]";
	}
	
	
	
}

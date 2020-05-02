package com.mainportal;

public class Transaction {
	
	private double amount;
	private int destination;
	private int senderLocation;
	//private User sendingUser;
	//private User receivingUser;

	public Transaction() {
		
	}
	
	public Transaction (int destination, int senderLocation, double amount) {
		this.destination = destination;
		this.senderLocation = senderLocation;
		this.amount = amount;
	}
	
	

}

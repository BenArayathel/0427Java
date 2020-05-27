package user.cust.account.models;

import java.util.ArrayList;
import java.util.List;

//import not.used.TransactionDeposit;
//import not.used.TransactionWithdraw;
//import not.used.TransferReceived;
//import not.used.TransferSent;

public class Transaction {

	private String t_id;
	private String user_id; // foreign key user_id
	private String date;
	private String description;
	private double transactionValue; // $
	private String destination_id;

	public Transaction() {
		//super();
	}

	// before database
	public Transaction(String user_id, String date, String description, double transactionValue,
			String destination_id) {
		super();
		this.user_id = user_id;
		this.date = date;
		this.description = description;
		this.transactionValue = transactionValue;
		this.destination_id = destination_id;
	}

	// after database
	public Transaction(String t_id, String user_id, String date, String description, double transactionValue,
			String destination_id) {
		super();
		this.t_id = t_id;
		this.user_id = user_id;
		this.date = date;
		this.description = description;
		this.transactionValue = transactionValue;
		this.destination_id = destination_id;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTransactionValue() {
		return transactionValue;
	}

	public void setTransactionValue(double transactionValue) {
		this.transactionValue = transactionValue;
	}

	public String getDestination_id() {
		return destination_id;
	}

	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}

	@Override
	public String toString() {
		return ""+ date 
				+ " descr " + description
				+ " transValue " + transactionValue
				+ " destinationId " + destination_id
				+ " creatorUserId " + user_id;
		
		// I use to have transaction id on the end of this.
	}

}



















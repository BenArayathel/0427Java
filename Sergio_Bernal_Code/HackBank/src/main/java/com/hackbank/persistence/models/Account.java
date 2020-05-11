package com.hackbank.persistence.models;

public class Account {
	
	private long Id;
	private short accountTypeId;
	private long routingNumber;
	private double balance;
	private Person person;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(long id, short accountTypeId, long routingNumber, double balance, Person person) {
		super();
		Id = id;
		this.accountTypeId = accountTypeId;
		this.routingNumber = routingNumber;
		this.balance = balance;
		this.person = person;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public short getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(short accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public long getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(long routingNumber) {
		this.routingNumber = routingNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account Number: " + Id + 
				"\nRouting Number: "+ routingNumber +			 
				"\nBalance: " + balance;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}

package com.hackbank.persistence.models;

public class Account {
	
	private String Id;
	private short accountTypeId;
	private String routingNumber;
	private double balance;
	
	private Person person;
	private AccountType accountType;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String id, short accountTypeId, String routingNumber, double balance, Person person) {
		super();
		Id = id;
		this.accountTypeId = accountTypeId;
		this.routingNumber = routingNumber;
		this.balance = balance;
		this.person = person;
	}
	
	public Account(String id, AccountType accountType, String routingNumber, double balance) {
		super();
		Id = id;
		this.accountType = accountType;
		this.routingNumber = routingNumber;
		this.balance = balance;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public short getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(short accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
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
	
	public String preview() {
		return "Account Type: " + accountType.getName() + 
				"\nAccount Number: " + Id + 
				"\nRouting Number: "+ routingNumber;
	}
	
	public String printBalance() {
		return "Account Type: " + accountType.getName() + 
				"\nAccount Number: " + Id + 
				"\nRouting Number: "+ routingNumber +
				"\nBalance: "+ balance;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

}

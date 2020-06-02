package com.pzero.v1.persistence.models;

import java.util.Date;

public class BankAccountEnrollment {
	
	private int id;
	private String toName;
	private String toLastName;
	private String accountNumber;
	private String routingNumber;
	private Date createdAt;
	private String personId;

	public BankAccountEnrollment() {
		// TODO Auto-generated constructor stub
	}

	public BankAccountEnrollment(int id, String toName, String toLastName, String accountNumber, String routingNumber,
			Date createdAt, String personId) {
		super();
		this.id = id;
		this.toName = toName;
		this.toLastName = toLastName;
		this.accountNumber = accountNumber;
		this.routingNumber = routingNumber;
		this.createdAt = createdAt;
		this.personId = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToLastName() {
		return toLastName;
	}

	public void setToLastName(String toLastName) {
		this.toLastName = toLastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}

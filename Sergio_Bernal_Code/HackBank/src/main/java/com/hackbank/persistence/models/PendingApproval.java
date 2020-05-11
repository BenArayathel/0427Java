package com.hackbank.persistence.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PendingApproval {
	
	private long Id;
	private String personId;
	private byte accountTypeId;
	private double startBalance;
	private String status;
	private Date createdAt;
	
	private Person person;
	private AccountType accountType;
	
	public PendingApproval() {
		// TODO Auto-generated constructor stub
	}
	
	public PendingApproval(long id, double startBalance, String status,Date createdAt, Person person, AccountType accountType) {
		super();
		Id = id;
		this.startBalance = startBalance;
		this.status = status;
		this.createdAt = createdAt;
		this.person = person;
		this.accountType = accountType;
	}

	public PendingApproval(long id, String personId, byte accountTypeId, double startBalance, String status,
			Date createdAt) {
		super();
		Id = id;
		this.personId = personId;
		this.accountTypeId = accountTypeId;
		this.startBalance = startBalance;
		this.status = status;
		this.createdAt = createdAt;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public byte getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(byte accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public double getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "PendingApproval [Id=" + Id + ", personId=" + personId + ", accountTypeId=" + accountTypeId
				+ ", startBalance=" + startBalance + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
	
	public String preview() {
		return "Client Id: " + person.getId() + 
				"\nAccount Type: " + accountType.getName() +
				"\nStart Balance: " + startBalance +
				"\nStatus: " + status +
				"\nCreated At: " + new SimpleDateFormat("MMM-dd-yyyy").format(createdAt);
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

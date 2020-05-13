package com.hackbank.persistence.models;

import java.util.Date;

public class Transfer {
	
	private int Id;
	private String iniAcccountNumber;
	private String endAccountNumber;
	private Date createdAt;
	private String status;
	private String iniRoutingNumber;
	private String endRoutingNumber;
	private double balance;
	
	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public Transfer(int id, String iniAcccountNumber, String endAccountNumber, Date createdAt, String status,
			String iniRoutingNumber, String endRoutingNumber, double balance) {
		super();
		Id = id;
		this.iniAcccountNumber = iniAcccountNumber;
		this.endAccountNumber = endAccountNumber;
		this.createdAt = createdAt;
		this.status = status;
		this.iniRoutingNumber = iniRoutingNumber;
		this.endRoutingNumber = endRoutingNumber;
		this.balance = balance;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIniAcccountNumber() {
		return iniAcccountNumber;
	}

	public void setIniAcccountNumber(String iniAcccountNumber) {
		this.iniAcccountNumber = iniAcccountNumber;
	}

	public String getEndAccountNumber() {
		return endAccountNumber;
	}

	public void setEndAccountNumber(String endAccountNumber) {
		this.endAccountNumber = endAccountNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIniRoutingNumber() {
		return iniRoutingNumber;
	}

	public void setIniRoutingNumber(String iniRoutingNumber) {
		this.iniRoutingNumber = iniRoutingNumber;
	}

	public String getEndRoutingNumber() {
		return endRoutingNumber;
	}

	public void setEndRoutingNumber(String endRoutingNumber) {
		this.endRoutingNumber = endRoutingNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transfer [Id=" + Id + ", iniAcccountNumber=" + iniAcccountNumber + ", endAccountNumber="
				+ endAccountNumber + ", createdAt=" + createdAt + ", status=" + status + ", iniRoutingNumber="
				+ iniRoutingNumber + ", endRoutingNumber=" + endRoutingNumber + ", balance=" + balance + "]";
	}

	
}

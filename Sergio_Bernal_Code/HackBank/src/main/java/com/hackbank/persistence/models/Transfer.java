package com.hackbank.persistence.models;

import java.util.Date;

public class Transfer {
	
	private long Id;
	private long iniAcccountNumber;
	private long endAccountNumber;
	private Date createdAt;
	private String status;
	
	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public Transfer(long id, long iniAcccountNumber, long endAccountNumber, Date createdAt, String status) {
		super();
		Id = id;
		this.iniAcccountNumber = iniAcccountNumber;
		this.endAccountNumber = endAccountNumber;
		this.createdAt = createdAt;
		this.status = status;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getIniAcccountNumber() {
		return iniAcccountNumber;
	}

	public void setIniAcccountNumber(long iniAcccountNumber) {
		this.iniAcccountNumber = iniAcccountNumber;
	}

	public long getEndAccountNumber() {
		return endAccountNumber;
	}

	public void setEndAccountNumber(long endAccountNumber) {
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

	@Override
	public String toString() {
		return "Transfer [Id=" + Id + ", iniAcccountNumber=" + iniAcccountNumber + ", endAccountNumber="
				+ endAccountNumber + ", createdAt=" + createdAt + ", status=" + status + "]";
	}

}

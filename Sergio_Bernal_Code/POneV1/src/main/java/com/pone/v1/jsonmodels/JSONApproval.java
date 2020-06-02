package com.pone.v1.jsonmodels;

import java.util.Date;

public class JSONApproval {
	
	private int id;
	private String customerFullName;
	private String customerId;
	private String accountType;
	private int accountTypeId;
	private double balance;
	private String status;
	private Date createdAt;
	
	public JSONApproval() {
		// TODO Auto-generated constructor stub
	}

	public JSONApproval(int id, String customerFullName, String customerId, String accountType, int accountTypeId,
			double balance, String status, Date createdAt) {
		super();
		this.id = id;
		this.customerFullName = customerFullName;
		this.customerId = customerId;
		this.accountType = accountType;
		this.accountTypeId = accountTypeId;
		this.balance = balance;
		this.status = status;
		this.createdAt = createdAt;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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
	
	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	@Override
	public String toString() {
		return "JSONApproval [id=" + id + ", customerFullName=" + customerFullName + ", accountType=" + accountType
				+ ", balance=" + balance + ", status=" + status + ", createdAt=" + createdAt + "]";
	}

}

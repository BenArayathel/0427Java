package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SimpleAccount")
public class SimpleAccount {
	
	@Id
	@Column(name = "account_number")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long accountNumber;
	
	@Column(name="balance", unique=true, nullable=false)
	private double balance;
	
	public SimpleAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SimpleAccount(long accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleAccount other = (SimpleAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;
		return true;
	}

}

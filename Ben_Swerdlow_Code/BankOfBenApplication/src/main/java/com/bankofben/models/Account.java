package com.bankofben.models;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.presentation.ValidationTools;

public class Account {
	
	private long accountNumber;
	// This is a small bank, so everyone has the same routing number
	private static final long routingNumber = 123456789;
	private double balance;
	private String customerId;
	
	public Account() {
		super();
	}
	
	public Account(long accountNumber, double balance, String customerId) throws BusinessException {
		super();
		setAccountNumber(accountNumber);
		this.balance = balance;
		setCustomerId(customerId);
	}
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) throws BusinessException {
		if (Long.parseLong(accountNumber) <= 0 || accountNumber.length() != 10) {
			throw new BusinessException("Invalid account number. Account number must be a positive 10-digit number");
		}
		
		if (this.accountNumber==0) {
			try {
				this.accountNumber = Long.parseLong(accountNumber);
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid account number. Account number must be a positive 10-digit number");
			}
		} else {
			throw new BusinessException("Attempt to make account failed. Account alreaedy exists and account numbers "
					+ "cannot change after creation.");
		}
	}
	
	public void setAccountNumber(long accountNumber) throws BusinessException {
		if (accountNumber <= 0 || Long.toString(accountNumber).length() != 10) {
			throw new BusinessException("Invalid account number. Account number must be a positive 10-digit number");
		}
		
		if (this.accountNumber==0) {
			this.accountNumber = accountNumber;
		} else {
			throw new BusinessException("Attempt to make account failed. Account alreaedy exists and account numbers "
					+ "cannot change after creation.");
		}
	}
	
	public static long getRoutingNumber() {
		return routingNumber;
	}

	// The routing number is set by the bank, you cannot change it.
	// At this time, BoB has only 1 routing number and it is assigned at account creation.
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance, Customer owner) throws BusinessException {
		if (owner.getId().equals(getCustomerId())) {
			if (ValidationTools.isValidMonetaryAmount(balance)) {
				if (Double.valueOf(balance)==Double.POSITIVE_INFINITY) {
					throw new BusinessException("Balances in excess of "+Double.MAX_VALUE+" are handled "
							+ "via another system. Contact a Bank of Ben employee for more details.");
				} else if (balance < 0) {
					throw new BusinessException("The balance of an account cannot be a negative number.");
				} else {
					this.balance = balance;
				}
			} else {
				throw new BusinessException("Balance amount must be a positive number that has only "
						+ "two digits after the decimal point");
			}
		} else {
			throw new BusinessException("Invalid credentials to change account "+this.getAccountNumber()+". "
					+ "You are not the owner of this account number. Please check your information and try again.");
		}
	}
	
	public void setBalance(double balance, Employee employee) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(balance)) {
			if (Double.valueOf(balance)==Double.POSITIVE_INFINITY) {
				throw new BusinessException("Balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else if (balance < 0) {
				throw new BusinessException("The balance of an account cannot be a negative number.");
			} else {
				this.balance = balance;
			}
		} else {
			throw new BusinessException("Balance amount must be a positive number that has only "
					+ "two digits after the decimal point");
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}

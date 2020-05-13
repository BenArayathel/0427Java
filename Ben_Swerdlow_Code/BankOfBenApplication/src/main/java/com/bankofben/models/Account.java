package com.bankofben.models;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.presentation.ValidationTools;
import com.bankofben.services.BankOfBenServices;

public class Account {
	
	private long accountNumber;
	// This is a small bank, so everyone has the same routing number
	private static final long routingNumber = 123456789;
	private double balance;
	private String customerId;
	private boolean pending;
	
	private static BusinessException b = null;
	final static Logger loggy = Logger.getLogger(BankOfBenServices.class);
	
	public Account() {
		super();
	}
	
	public Account(long accountNumber, double balance, String customerId, boolean pending) throws BusinessException {
		super();
		setAccountNumber(accountNumber);
		this.balance = balance;
		setCustomerId(customerId);
		setPending(pending);
	}
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) throws BusinessException {
		if (Long.parseLong(accountNumber) <= 0 || accountNumber.length() != 10) {
			b = new BusinessException("Invalid account number. Account number must be a positive 10-digit number");
			loggy.error(b);
			throw b;
		}
		
		if (this.accountNumber==0) {
			try {
				this.accountNumber = Long.parseLong(accountNumber);
			} catch (NumberFormatException e) {
				b = new BusinessException("Invalid account number. Account number must be a positive 10-digit number");
				loggy.error(b);
				throw b;
			}
		} else {
			b = new BusinessException("Attempt to make account failed. Account alreaedy exists and account numbers "
					+ "cannot change after creation.");
			loggy.error(b);
			throw b;
		}
	}
	
	public void setAccountNumber(long accountNumber) throws BusinessException {
		if (accountNumber <= 0 || Long.toString(accountNumber).length() != 10) {
			b = new BusinessException("Invalid account number. Account number must be a positive 10-digit number");
			loggy.error(b);
			throw b;
		}
		
		if (this.accountNumber==0) {
			this.accountNumber = accountNumber;
		} else {
			b = new BusinessException("Attempt to make account failed. Account alreaedy exists and account numbers "
					+ "cannot change after creation.");
			loggy.error(b);
			throw b;
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
					b = new BusinessException("Balances in excess of "+Double.MAX_VALUE+" are handled "
							+ "via another system. Contact a Bank of Ben employee for more details.");
					loggy.error(b);
					throw b;
				} else if (balance < 0) {
					b = new BusinessException("The balance of an account cannot be a negative number.");
					loggy.error(b);
					throw b;
				} else {
					this.balance = balance;
				}
			} else {
				b = new BusinessException("Balance amount must be a positive number that has only "
						+ "two digits after the decimal point");
				loggy.error(b);
				throw b;
			}
		} else {
			b = new BusinessException("Invalid credentials to change account "+this.getAccountNumber()+". "
					+ "You are not the owner of this account number. Please check your information and try again.");
			loggy.error(b);
			throw b;
		}
	}
	
	public void setBalance(double balance, Payment payment) throws BusinessException {
		if (!payment.isPending()) {
			b = new BusinessException("Invalid payment. Payment has already been resolved.");
			loggy.error(b);
			throw b;
		} else {
			if (payment.getPayingAccountNumber()==this.accountNumber || payment.getReceivingAccountNumber()==this.accountNumber) {
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
					b = new BusinessException("Balance amount must be a positive number that has only "
							+ "two digits after the decimal point");
					loggy.error(b);
					throw b;
				}
			} else {
				b = new BusinessException("Invalid change in balance. Payment does not involve account "+this.accountNumber);
				loggy.error(b);
				throw b;
			}
		}
	}
	
	public void setBalance(double balance, Request request) throws BusinessException {
		if (!request.isPending()) {
			b = new BusinessException("Invalid request. Request has already been resolved");
			loggy.error(b);
			throw b;
		} else {
			if (request.getRequestorAccountNumber()==this.accountNumber || request.getSoughtAccountNumber()==this.accountNumber) {
				if (ValidationTools.isValidMonetaryAmount(balance)) {
					if (Double.valueOf(balance)==Double.POSITIVE_INFINITY) {
						b = new BusinessException("Balances in excess of "+Double.MAX_VALUE+" are handled "
								+ "via another system. Contact a Bank of Ben employee for more details.");
						loggy.error(b);
						throw b;
					} else if (balance < 0) {
						b = new BusinessException("The balance of an account cannot be a negative number.");
						loggy.error(b);
						throw b;
					} else {
						this.balance = balance;
					}
				} else {
					b = new BusinessException("Balance amount must be a positive number that has only "
							+ "two digits after the decimal point");
					loggy.error(b);
					throw b;
				}
			} else {
				b = new BusinessException("Invalid change in balance. Request does not involve account "+this.accountNumber);
				loggy.error(b);
				throw b;
			}
		}
	}
	
	public void setBalance(double balance, Employee employee) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(balance)) {
			if (Double.valueOf(balance)==Double.POSITIVE_INFINITY) {
				b = new BusinessException("Balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
				loggy.error(b);
				throw b;
			} else if (balance < 0) {
				b = new BusinessException("The balance of an account cannot be a negative number.");
				loggy.error(b);
				throw b;
			} else {
				this.balance = balance;
			}
		} else {
			b = new BusinessException("Balance amount must be a positive number that has only "
					+ "two digits after the decimal point");
			loggy.error(b);
			throw b;
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", customerId=" + customerId
				+ ", pending=" + pending + "]";
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
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		return true;
	}

	
	
}

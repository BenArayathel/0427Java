package com.bankofben.bankapplication;

public class Account {
	
	private int accountNumber;
	// This is a small bank, so everyone has the same routing number
	private static final int routingNumber = 123456789;
	private double balance;
	private User ownerUser;
	
	public Account() {
		super();
	}
	
	public Account(int accountNumber, double balance) {
		super();
		setAccountNumber(accountNumber);
		setBalance(balance);
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		if (accountNumber <= 0 || Integer.valueOf(accountNumber).toString().length() != 10) {
			throw new AccountNumberException("Account number must be a positive 10-digit number");
		}
		
		if (this.accountNumber==0) {
			this.accountNumber = accountNumber;
		} else {
			throw new AccountEditException("Account numbers cannot change after creation.");
		}
	}
	
	public int getRoutingNumber() {
		return routingNumber;
	}
	// Routing number is set by the bank, you cannot reset it
//	public void setRoutingNumber(int routingNumber) {
//		if (routingNumber <= 0 || Integer.valueOf(routingNumber).toString().length() != 9) {
//			throw new RoutingNumberException("Routing number must be a positive 9-digit number");
//		}
//		
//		if (this.routingNumber==0) {
//			this.routingNumber = routingNumber;
//		} else {
//			throw new AccountEditException("Account numbers cannot change after creation.");
//		}
//		this.routingNumber = routingNumber;
//	}
	
	public double getBalance() {
		return balance;
	}
	
	private void setBalance(double balance) {
		if (isValidMonetaryAmount(balance)) {
			if (Double.valueOf(balance)==Double.POSITIVE_INFINITY) {
				throw InvalidBalanceException("Deposits that would result in balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else {
				this.balance = balance;
			}
		} else {
			throw new InvalidBalanceException("Balance amount must be a positive number that has only "
					+ "two digits after the decimal point");
		}
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}
	
	public void makeDeposit(double deposit, int accountNumber, int routingNumber) {
		if (isValidMonetaryAmount(deposit)) {
			if (routingNumber != Account.routingNumber) {
				// Check routing number
				throw RoutingNumberException("Given routing number does not match Bank of Ben's routing number. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (accountNumber != this.accountNumber) {
				// Check account number
				throw AccountNumberException("Recipient account and intended recipient account do not match. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (Double.valueOf(getBalance() + deposit)==Double.POSITIVE_INFINITY) {
				// Check that the maximum balance is not exceeded
				throw InvalidDepositException("Deposits that would result in balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else {
				setBalance(getBalance() + deposit);
			}
		} else {
			throw InvalidDepositException("Deposit amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}
	
	public void makeWithdrawal(double withdrawal, int accountNumber, int routingNumber) {
		if (isValidMonetaryAmount(withdrawal)) {
			if (routingNumber != Account.routingNumber) {
				// Check routing number
				throw RoutingNumberException("Given routing number does not match Bank of Ben's routing number. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (accountNumber != this.accountNumber) {
				// Check account number
				throw AccountNumberException("Recipient account and intended recipient account do not match. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (Double.valueOf(getBalance() - withdrawal) < 0) {
				// Check that the maximum balance is not exceeded
				throw InvalidWithdrawalException("Withdrawal amount "+withdrawal+" exceeds the amount available in account"
						+accountNumber+". Please check that your information is correct. If it is, contact a Bank of Ben employee "
								+ "to remedy the issue.");
			} else {
				setBalance(getBalance() - withdrawal);
			}
		} else {
			throw InvalidDepositException("Withdrawal amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}
	
	public boolean isValidMonetaryAmount(double ammount) {
		if (ammount <= 0) {
			return false;
		}
		String stringAmmount = Double.valueOf(ammount).toString();
		String[] ammountIntMantissa = stringAmmount.split(".");
		if (ammountIntMantissa.length == 2) {
			if (ammountIntMantissa[1].length() > 2) {
				return false;
			}
		}
		return true;
	}

}

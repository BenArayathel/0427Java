package com.bankofben.bankapplication;

public class Account {
	
	private String accountNumber;
	// This is a small bank, so everyone has the same routing number
	private static final String routingNumber = "123456789";
	private double balance;
	private User owner;
	
	public Account() {
		super();
	}
	
	public Account(String accountNumber, double balance, User owner) throws NumberFormatException, InvalidAccountNumberException,
			AccountNumberPersistenceException, InvalidBalanceException {
		super();
		setAccountNumber(accountNumber);
		setBalance(balance);
		setOwner(owner);
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) throws NumberFormatException, InvalidAccountNumberException,
			AccountNumberPersistenceException {
		if (Integer.parseInt(accountNumber) <= 0 || accountNumber.length() != 10) {
			throw new InvalidAccountNumberException("Account number must be a positive 10-digit number");
		}
		
		if (this.accountNumber.equals(null)) {
			this.accountNumber = accountNumber;
		} else {
			throw new AccountNumberPersistenceException("Account numbers cannot change after creation.");
		}
	}
	
	public String getRoutingNumber() {
		return routingNumber;
	}

	// The routing number is set by the bank, you cannot change it.
	// At this time, BoB has only 1 routing number and it is assigned at account creation.
	
	public double getBalance() {
		return balance;
	}
	
	private void setBalance(double balance) throws InvalidBalanceException {
		if (isValidMonetaryAmount(balance)) {
			if (Double.valueOf(balance)==Double.POSITIVE_INFINITY) {
				throw new InvalidBalanceException("Balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else {
				this.balance = balance;
			}
		} else {
			throw new InvalidBalanceException("Balance amount must be a positive number that has only "
					+ "two digits after the decimal point");
		}
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public void makeDeposit(double deposit, String accountNumber, String routingNumber) throws RoutingNumberException,
			InvalidAccountNumberException, InvalidBalanceException, InvalidAmountException {
		if (isValidMonetaryAmount(deposit)) {
			if (!(routingNumber.equals(Account.routingNumber))) {
				// Check routing number
				throw new RoutingNumberException("Given routing number does not match Bank of Ben's routing number. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (!(accountNumber.equals(this.accountNumber))) {
				// Check account number
				throw new InvalidAccountNumberException("Recipient account and intended recipient account do not match. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (Double.valueOf(getBalance() + deposit)==Double.POSITIVE_INFINITY) {
				// Check that the maximum balance is not exceeded
				throw new InvalidBalanceException("Deposits that would result in balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else {
				setBalance(getBalance() + deposit);
			}
		} else {
			throw new InvalidAmountException("Deposit amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}
	
	public void makeWithdrawal(double withdrawal, String accountNumber, String routingNumber) throws RoutingNumberException,
			InvalidAccountNumberException, InvalidBalanceException, InvalidAmountException {
		if (isValidMonetaryAmount(withdrawal)) {
			if (!(routingNumber.equals(Account.routingNumber))) {
				// Check routing number
				throw new RoutingNumberException("Given routing number does not match Bank of Ben's routing number. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (!(accountNumber.equals(this.accountNumber))) {
				// Check account number
				throw new InvalidAccountNumberException("Recipient account and intended recipient account do not match. Please check "
						+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			} else if (Double.valueOf(getBalance() - withdrawal) < 0) {
				// Check that the maximum balance is not exceeded
				throw new InvalidBalanceException("Withdrawal amount "+withdrawal+" exceeds the amount available in account"
						+accountNumber+". Please check that your information is correct. If it is, contact a Bank of Ben employee "
						+ "to remedy the issue.");
			} else {
				setBalance(getBalance() - withdrawal);
			}
		} else {
			throw new InvalidAmountException("Withdrawal amount must be a positive number that has only "
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

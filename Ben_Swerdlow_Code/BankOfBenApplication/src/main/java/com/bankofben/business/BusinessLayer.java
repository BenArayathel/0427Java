package com.bankofben.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.User;
import com.bankofben.presentation.UserInterface;
import com.bankofben.presentation.ValidationTools;
import com.bankofben.services.DatabaseServices;

public class BusinessLayer {
	
	public User loginUser(String username, Scanner sc) throws BusinessException {
		String password = null;
		int loginAttempts = 0;
		User user = null;
		BusinessLayer bl = new BusinessLayer();
		while (loginAttempts < 4) {
			password = UserInterface.requestPassword(sc);
			bl.loginUser(username, password);
			loginAttempts++;
			// TODO: Added lag to discourage brute force attempts; not critical, attempt later
		}
		if (loginAttempts >= 4) {
			throw new BusinessException("Limit of password attempts exceeded. Please try again later.");
		}
		return user;
	}

	public User loginUser(String username, String password) throws BusinessException {
		DatabaseServices dba = new DatabaseServices();
		User user = null;
		try {
			user = dba.validateUser(username, password);
		} catch (BusinessException e) {
			throw e;
		}
		return user;
	}

	private String generateAccountNumber () {
		boolean uniqueAccountNumber = false;
		Long randomTenDigitNumber;
		DatabaseServices dba = new DatabaseServices();
		do {
			randomTenDigitNumber = ThreadLocalRandom.current().nextLong((long)1e9, (long)1e10);
			uniqueAccountNumber = dba.isUniqueAccountNumber(randomTenDigitNumber);
			/*
			 *  TODO Fix dbl.isUniqueAccountNumber(long randomTenDigitNumber) implementation to actually
			 *  		check if account number already exists in the database.
			 */
		} while (!(uniqueAccountNumber));
		return randomTenDigitNumber.toString();
	}

	public boolean userExists(String username) {
		// BusinessLayer passes database call to DatabaseLayer
		return new DatabaseServices().userExists(username);
//		boolean userExists;
//		try {
//			userExists = this.usernameCustomerMap.containsKey(username);
//		} catch (NullPointerException e) {
//			userExists = false;
//		}
//		return userExists;
	}
	
	public boolean emailExists(String email) {
		// BusinessLayer passes database call to DatabaseLayer
		return new DatabaseServices().emailExists(email);
//		boolean emailExists;
//		try {
//			emailExists = this.usernameEmailMap.containsValue(email);
//		} catch (NullPointerException e) {
//			emailExists = false;
//		}
//		return emailExists;
	}

	public Customer applyForAccount(User user) throws BusinessException {
		DatabaseServices dbs = new DatabaseServices();
		Customer customer = dbs.applyForAccount(user);
		return customer;
	}

	public String viewBalances(Customer customer) throws BusinessException {
		DatabaseServices dbs = new DatabaseServices();
		return dbs.getBalances(customer);
	}
	
	public Account getAccount(long accountNumber, String routingNumber)
			throws BusinessException {
		if (!(routingNumber.equals(Account.getRoutingNumber()))) {
			// Check routing number
			throw new BusinessException("Given routing number does not match Bank of Ben's routing number. Please check "
					+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
		} else {
			DatabaseServices dbs = new DatabaseServices();
			Account account = dbs.getAccount(accountNumber, routingNumber);
			return account;
		}
	}

	public void makeDeposit(double deposit, Account account, User customerOrEmployee) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(deposit)) {
			if (Double.valueOf(account.getBalance() + deposit)==Double.POSITIVE_INFINITY) {
				// Check that the maximum balance is not exceeded
				throw new BusinessException("Deposits that would result in balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else {
				account.setBalance(account.getBalance() + deposit, customerOrEmployee);
			}
		} else {
			throw new BusinessException("Deposit amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}
	
	public void makeWithdrawal(double withdrawal, Account account, User customerOrEmployee) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(withdrawal)) {
			if (Double.valueOf(account.getBalance() - withdrawal) < 0) {
				// Check that the balance would not become negative
				throw new BusinessException("Withdrawal amount "+withdrawal+" exceeds the amount available in account"
						+account.getAccountNumber()+". Please check that your information is correct. If it is, contact "
						+ "a Bank of Ben employee to remedy the issue.");
			} else {
				account.setBalance(account.getBalance() - withdrawal, customerOrEmployee);
			}
		} else {
			throw new BusinessException("Withdrawal amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}

	public boolean employeeExists(int employeeId) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<Long> getAccountNumbersForCustomer(Customer customer) throws BusinessException {
		DatabaseServices dbs = new DatabaseServices();
		List<Account> accounts = dbs.getAccountsForCustomer(customer);
		List<Long> accountNumbers = new ArrayList<>();
		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}
		return accountNumbers;
	}
	
	
}

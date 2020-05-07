package com.bankofben.business;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.bankofben.database.DatabaseServices;
import com.bankofben.models.Customer;
import com.bankofben.models.User;
import com.bankofben.presentation.UserInterface;

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

	public Customer applyForAccount(User user) {
		DatabaseServices dbs = new DatabaseServices();
		Customer customer = dbs.applyForAccount(user);
		return customer;
	}

	public String viewBalances(int customerID) {
		DatabaseServices dbs = new DatabaseServices();
		return dbs.getBalances(customerID);
	}
	
	
	
}

package com.bankofben.services;

import java.sql.SQLException;
import java.util.List;

import com.bankofben.dao.BankOfBenDAO;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;

public class DatabaseServices {
	
	private BankOfBenDAO dao = new BankOfBenDAO();

	public User validateUser(String username, String password) throws BusinessException {
		User user = null;
		try {
			user = callDatabase(username, password);
		} catch (SQLException e) {
			throw new BusinessException("There appears to be a problem with the database. Please try again later.");
		}
		return user;
	}

	private User callDatabase(String username, String password) throws SQLException {
		/*
		 *  TODO Check database to see if a customer or employee with username and password exists
		 *  probably will be replaced with proper database call tomorrow
		 */
		return null;
	}

	public boolean isUniqueAccountNumber(Long randomTenDigitNumber) {
		/*
		 *  TODO: Check database to see if randomTenDigitNumber is already an account number 
		 */
		boolean unique = true;
		return unique;
	}
	
	public boolean userExists(User user) {
		boolean exists = false;
		if (user instanceof Customer) {
			exists = customerExists((Customer) user);
		} else if (user instanceof Employee) {
			exists = employeeExists((Employee) user);
		} else
			exists = false;
		return exists;
	}
	
	public boolean userExists(String username) {
		boolean exists = false;
		/*
		 *  TODO Check database to see if customer or employee exists with username
		 */
		return exists;
	}
	
	public boolean emailExists(String email) {
		boolean exists = false;
		/*
		 * TODO Check database to see if customer or employee exists with email
		 */
		return exists;
	}

	private boolean customerExists(Customer customer) {
		boolean exists = false;
		String employeeID = customer.getId();
		/*
		 *  TODO Check database to see if employee already exists using the employeeID
		 */
		return exists;
	}
	
	public boolean customerExists(String customerUsername) {
		boolean exists = false;
		/*
		 *  TODO Check database to see if customer exists with customerUsername
		 */
		return exists;
	}

	private boolean employeeExists(Employee employee) {
		boolean exists = false;
		String employeeID = employee.getId();
		/*
		 *  TODO Check database to see if employee already exists using the employeeID
		 */
		return exists;
	}
	
	public boolean employeeExists(String employeeUsername) {
		boolean exists = false;
		/*
		 *  TODO Check database to see if employee exists with employeeUsername
		 */
		return exists;
	}

	public Customer applyForAccount(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBalances(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getAccount(long accountNumber, String routingNumber) throws BusinessException {
		// TODO Auto-generated method stub
//		throw new BusinessException("Recipient account and intended recipient account do not match. Please check "
//				+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
		return dao.getAccountByAccountNumber(accountNumber);
	}
	
	public List<Account> getAccountsForCustomer(Customer customer) throws BusinessException {
		return dao.getAccountsForCustomerId(customer.getId());
	}

}

package com.bankofben.database;

import java.sql.SQLException;

import com.bankofben.business.BusinessException;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;

public class DatabaseServices {

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
		int employeeID = customer.getId();
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
		int employeeID = employee.getId();
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

	public String getBalances(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

}

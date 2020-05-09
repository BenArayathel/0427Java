package com.bankofben.services;

import java.util.ArrayList;
import java.util.List;

import com.bankofben.dao.BankOfBenDAO;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;

public class BankOfBenServices {
	
	private BankOfBenDAO dao = new BankOfBenDAO();

	public User loginUser(String username, String password) throws BusinessException {
		User user = null;
		List<BusinessException> bizExceptions = new ArrayList<>();
		try {
			user = dao.loginCustomer(username, password);
		} catch (BusinessException e) {
			bizExceptions.add(e);
		}
		
		try {
			user = dao.loginEmployee(username, password);
		} catch (BusinessException e) {
			bizExceptions.add(e);
		}
		
		if (user==null) {
			if (bizExceptions.size()==1) {
				throw bizExceptions.get(0);
			} else {
				throw new BusinessException("User does not exist with these credentials. "
						+ "Please register these credentials as a new user.");
			}
		}
		
		return user;
	}
	
	public Customer loginCustomer(String username, String password) throws BusinessException {
		return dao.loginCustomer(username, password);
	}
	
	public Employee loginEmployee(String username, String password) throws BusinessException {
		return dao.loginEmployee(username, password);
	}

//	public boolean isUniqueAccountNumber(Long randomTenDigitNumber) {
//		/*
//		 *  TODO: Check database to see if randomTenDigitNumber is already an account number 
//		 */
//		boolean unique = true;
//		return unique;
//	}
	
	public boolean userExists(User user) throws BusinessException {
		boolean exists = false;
		if (user instanceof Customer) {
			exists = customerExists((Customer) user);
		} else if (user instanceof Employee) {
			exists = employeeExists((Employee) user);
		} else
			exists = false;
		return exists;
	}
	
	public boolean usernameExists(String username) throws BusinessException {
		return (dao.customerUsernameExists(username) || dao.customerUsernameExists(username));
	}
	
	public boolean emailExists(String email) throws BusinessException {
		return (dao.customerEmailExists(email) || dao.employeeEmailExists(email));
	}

	public boolean ssnExists(long ssn) throws BusinessException {
		return (dao.customerSsnExists(ssn) || dao.employeeSsnExists(ssn));
	}

	private boolean customerExists(Customer customer) throws BusinessException {
		return customerExists(customer.getUsername());
	}

	public boolean customerExists(String customerUsername) throws BusinessException {
		return dao.customerUsernameExists(customerUsername);
	}

	private boolean employeeExists(Employee employee) throws BusinessException {
		return employeeExists(employee.getUsername());
	}
	
	public boolean employeeExists(String employeeUsername) throws BusinessException {
		return dao.employeeUsernameExists(employeeUsername);
	}

	public Customer applyForAccount(User user) throws BusinessException {
		return dao.createCustomer(user);
	}
	
	public void approveCustomerAccount(String customerId, Employee employee) {
		
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

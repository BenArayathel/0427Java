package com.company.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.company.controller.BankServiceController;
import com.company.dao.AccountDaoJdbcImpl;
import com.company.dao.CustomerDaoJdbcImpl;
import com.company.dao.RegistrationDaoJdbcImpl;
import com.company.model.Account;
import com.company.model.Customer;
import com.company.view.BankApp;

public class ServiceLayer {
	
	private final RegistrationDaoJdbcImpl registrationDao = new RegistrationDaoJdbcImpl();
	private final CustomerDaoJdbcImpl customerDao = new CustomerDaoJdbcImpl();
	private final AccountDaoJdbcImpl accountDao = new AccountDaoJdbcImpl();

	
	public Customer validateLogin(String loginName, String password) {
				
        // Validate login Name.
        if (loginName.trim().length() == 0 || password.trim().length() == 0) {
            BankApp.loggy.info("Login Name and Password must not be blank.");
            return null;
        };
        
        // Get customerId from registration table
        int customerId = registrationDao.getCustomerIdByLoginName(loginName, password);
        
        // if no customerId found, customerID returned will be 0 and return false;      
        if (customerId == 0) {
        	BankApp.loggy.info("Customer not found. Please try again or enroll.");
        	return null;
        };
        
        // find customer detail from customer table.
        BankApp.loggy.info("Customer ID found... proceeding to application screen.");
        
        Customer customer = customerDao.getCustomer(customerId);
        
		return customer;
	}
	
	
	public void createCustomerAccount(String firstName, String lastName, String birthday, String usState, String accountType, BigDecimal bigDecimalBalance) {
	
		// Create Customer
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		
		// convert java.util.date to sql.Date.
        SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
        java.util.Date javaDate;
        java.sql.Date sqlDate;
        try {
			javaDate = format.parse( birthday );
	        // Date type below is java.sql.Date
	        sqlDate = new java.sql.Date(javaDate.getTime());
	        customer.setBirthday(sqlDate);
		} catch (ParseException e) {
			BankApp.loggy.error("Problem encountered in parsing date.");
			e.printStackTrace();
		}  // Notice the ".util." of package name.

		customer.setState(usState);
		
		customer = customerDao.addCustomer(customer);
		
		//Create Account 
		Account account = new Account();
		account.setCustomerId(customer.getCustomerId());
		account.setAccountType(accountType);
		account.setBalance(bigDecimalBalance);
		account.setApproved(false);
		
		account = accountDao.addAccount(account);
		
		// insert transactions in transaction table.
		
		
		
	}

}

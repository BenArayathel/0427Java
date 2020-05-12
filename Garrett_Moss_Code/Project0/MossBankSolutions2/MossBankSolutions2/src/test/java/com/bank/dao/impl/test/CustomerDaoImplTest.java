package com.bank.dao.impl.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bank.dao.CustomerDAO;
import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.dbutil.BankOracleConnection;
import com.bank.exception.BankException;
import com.bank.model.Customer;

public class CustomerDaoImplTest {

	private static CustomerDAO customerDao;
	@Test
	public void viewBalance() {
		Customer customer = new Customer();
		customer.setAccountBalance("40.00");
		System.out.println(customer.getAccountBalance());
	}
	@Test
	public void createCustomerAccount() throws BankException {
		Customer customer = new Customer();
		customer.setUsername("JRain");
		customer.setPassword("Password");
		customer.setAccountBalance("900.00");
		customer.setAccountNumber("1000000000");
		customer.setApproved("Approved");
		customer = customerDao.createCustomerAccount(customer);
		
	}

}

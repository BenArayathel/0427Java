package com.friendshipBank.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.friendshipBank.dao.customerDAO;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;

public class customerDAOImplTest {


	@Test
	public final void testGetCustomerById() throws BusinessException {
//		Customer ID: FBJS196710040
		String expectedLastname = "Smith";
		String expectedFirstname = "John";
		
		customer customer = new customer();
		customerDAO resultCustomer = new customerDAOImpl();
		customer = resultCustomer.getCustomerById("FBJS196710040");
		
		assertEquals(expectedLastname, customer.getLastName());
		assertEquals(expectedFirstname, customer.getFirstName());
		
	}

	@Test
	public final void testGetCustomerByState() throws BusinessException {
		List<customer> resultList = new ArrayList<>();
		customerDAO resultCustomer = new customerDAOImpl();
		resultList = resultCustomer.getCustomerByState("NY");
		
		assertNotNull(resultList);
	}

	@Test
	public final void testGetCustomerByCity() throws BusinessException {
		List<customer> resultList = new ArrayList<>();
		customerDAO resultCustomer = new customerDAOImpl();
		resultList = resultCustomer.getCustomerByCity("Harlem");
		
		assertNotNull(resultList);
	}

	@Test
	public final void testGetAllCustomers() throws BusinessException {
		
		List<customer> resultList = new ArrayList<>();
		customerDAO resultCustomer = new customerDAOImpl();
		resultList = resultCustomer.getAllCustomers();
		
		assertNotNull(resultList);
	}

}

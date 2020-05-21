package com.bhank.service.impl.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bhank.exception.BusinessException;
import com.bhank.model.Customer;
import com.bhank.service.impl.CustomerServiceImpl;

import junit.framework.Assert;

public class CustomerServiceImplTest {
	
	private static CustomerServiceImpl service;
	
	@BeforeClass
	public static void createCustomerServiceImplObject() {
		service=new CustomerServiceImpl();
	}
	
	@Test
	public void createCustomerTest() throws BusinessException {
		Customer customer = new Customer();
		customer.setName("namely nameson");
		customer.setPassword("passwo");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(false);
		Date d = null;
		try {
			d = sdf.parse("10.10.1999");
			customer.setDob(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer = service.createCustomer(customer);
		
		assertTrue("customer id", customer.getId().matches("CU[a-zA-Z]{2}[0-9]{9}"));
		assertEquals("customer name", "namely nameson", customer.getName());
		assertEquals("customer password", "passwo", customer.getPassword());
		assertEquals("customer dob", d, customer.getDob());
	}
	
	@Test
	public void selectCustomerByNameAndPassword() {
		String wrongName = "namely wrongnameson";
		String wrongPassword = "wrongpasswo";
		
		String correctName = "namely nameson";
		String correctPassword = "passwo";
		
		Customer customer = null;
		
		try {
			customer = service.selectCustomerByNameAndPassword(wrongName, wrongPassword);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
//		assertNull(customer);
		
		try {
			customer = service.selectCustomerByNameAndPassword(correctName, correctPassword);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		assertNotNull(customer);
		
		assertEquals("Customer id", "CUNA199910060", customer.getId());
		assertEquals("Customer name", "namely nameson", customer.getName());
		assertEquals("Customer password", "passwo", customer.getPassword());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(false);
		Date d = null;
		try {
			d = sdf.parse("10.10.1999");
			customer.setDob(d);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals("Customer dob", d, customer.getDob());
		
	}
	
	@Test
	public void isValidPasswordTest() {
		assertEquals("Test password of length 1", false, service.isValidPassword("1"));
		assertEquals("test password of length 21", false, service.isValidPassword("abcdefghijklmnopqrstu"));
		assertEquals("test password of length 8", true, service.isValidPassword("a1b2c3d4"));
	}
	
	
	@AfterClass
	public static void destroyCustomerServiceImplObject() {
		service=null;
	}

}

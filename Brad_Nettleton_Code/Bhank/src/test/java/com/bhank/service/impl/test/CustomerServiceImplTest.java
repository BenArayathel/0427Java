package com.bhank.service.impl.test;
import static org.junit.Assert.assertEquals;
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
	
	@Test (expected = BusinessException.class)
	public void createCustomerWithInvalidNameTest() {
		Customer customer = new Customer();
		customer.setName("234543");
		try {
			assertEquals("Test invalid name", BusinessException.class, service.createCustomer(customer));
		} catch (BusinessException e) {
			
		}
	
	}
	
	
	@AfterClass
	public static void destroyCustomerServiceImplObject() {
		service=null;
	}

}

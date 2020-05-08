package com.company.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.company.model.Customer;

public class CustomerDaoJdbcImplTest {

//	@Before
//	public void setUp() throws Exception {
//		
//	}
	
	@Test
	public void testAddGetDeleteCustomer() {
        Customer customer = new Customer();
          
        customer.setFirstName("Rich");
        customer.setLastName("Fen");
        
        SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
        java.util.Date myDate;
        java.sql.Date sqlDate;
        try {
			myDate = format.parse( "03/18/1988" );
	        // Date type below is java.sql.Date
	        sqlDate = new Date(myDate.getTime());
	        customer.setBirthday(sqlDate);
		} catch (ParseException e) {
			System.out.println("Problem encountered in parsing date.");
			e.printStackTrace();
		}  // Notice the ".util." of package name.
        

        customer.setState("GA");
        
        CustomerDaoJdbcImpl customerDaoJdbcImpl = new CustomerDaoJdbcImpl();
        
        customer = customerDaoJdbcImpl.addCustomer(customer);
        
        System.out.println("customer inserted");

        Customer customer1 = customerDaoJdbcImpl.getCustomer(customer.getCustomerId());

        System.out.println("Input by user through the app... " + customer);
        System.out.println("Retrieved from DB... " + customer1);
        
        assertEquals(customer1, customer);

        customerDaoJdbcImpl.deleteCustomer(customer.getCustomerId());

        customer1 = customerDaoJdbcImpl.getCustomer(customer.getCustomerId());

        assertNull(customer1);

	}

	@Test
	public void testGetCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllCustomers() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

}

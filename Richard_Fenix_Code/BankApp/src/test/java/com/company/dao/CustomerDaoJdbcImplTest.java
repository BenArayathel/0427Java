package com.company.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.company.model.Customer;

public class CustomerDaoJdbcImplTest {


    SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
    java.util.Date myDate;
    java.sql.Date sqlDate;
	
    CustomerDaoJdbcImpl customerDaoJdbcImpl = new CustomerDaoJdbcImpl();


  	@Before
	public void setUp() throws Exception {
		List<Customer> cList = customerDaoJdbcImpl.getAllCustomers();
		for (Customer c: cList) {
			customerDaoJdbcImpl.deleteCustomer(c.getCustomerId());
		}
		
	}

    
	@Test
	public void testAddGetDeleteCustomer() {
        Customer customer = new Customer();
          
        customer.setFirstName("Rich");
        customer.setLastName("Fen");
        
        // convert string date into sql date format.
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
        
        //CustomerDaoJdbcImpl customerDaoJdbcImpl = new CustomerDaoJdbcImpl();
        
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
	public void testGetAllCustomers() {
				
		Customer customer = new Customer();
		
        customer.setFirstName("Rich");
        customer.setLastName("Fen");
        
        // convert string date into sql date format.
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
        
        //CustomerDaoJdbcImpl customerDaoJdbcImpl = new CustomerDaoJdbcImpl(); 
        customer = customerDaoJdbcImpl.addCustomer(customer);

        System.out.println("First customer inserted... " + customer);
        
        customer = new Customer();
        customer.setFirstName("Luke");
        customer.setLastName("Skywalker");
        
        // convert string date into sql date format.
        try {
			myDate = format.parse( "05/04/1968" );
	        // Date type below is java.sql.Date
	        sqlDate = new Date(myDate.getTime());
	        customer.setBirthday(sqlDate);
		} catch (ParseException e) {
			System.out.println("Problem encountered in parsing date.");
			e.printStackTrace();
		}  // Notice the ".util." of package name.
        

        customer.setState("NY");
        
        //CustomerDaoJdbcImpl customerDaoJdbcImpl = new CustomerDaoJdbcImpl(); 
        customer = customerDaoJdbcImpl.addCustomer(customer);
        
        System.out.println("Second customer inserted... " + customer);
        
        List<Customer> xList = customerDaoJdbcImpl.getAllCustomers();

        System.out.println("Total records in the array as extracted from db is... " + xList.size());
        
        assertEquals(2, xList.size());

	}

	@Test
	public void testUpdateCustomer() {
        Customer customer = new Customer();
        
        customer.setFirstName("Rich");
        customer.setLastName("Fen");

        // convert string date into sql date format.
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
        
        customer = customerDaoJdbcImpl.addCustomer(customer);
        
        System.out.println("Customer for update inserted..." + customer);
        
        customer.setFirstName("Richard");
        
        try {
			myDate = format.parse( "03/27/2000" );
	        // Date type below is java.sql.Date
	        sqlDate = new Date(myDate.getTime());
	        customer.setBirthday(sqlDate);
		} catch (ParseException e) {
			System.out.println("Problem encountered in parsing date.");
			e.printStackTrace();
		}  // Notice the ".util." of package name.

        
        customerDaoJdbcImpl.updateCustomer(customer);
        
        Customer customer1 = customerDaoJdbcImpl.getCustomer(customer.getCustomerId());
  
        System.out.println("Updated customer from DB... " + customer1);

        assertEquals("Richard", customer1.getFirstName());
        
        // get updated date from DB. Convert downloaded sql date format to java date format.
        java.util.Date utilDate = new Date(customer1.getBirthday().getTime());
        
        assertEquals(utilDate, customer1.getBirthday());

	}


}

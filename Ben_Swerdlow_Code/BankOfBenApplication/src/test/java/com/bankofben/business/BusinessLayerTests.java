package com.bankofben.business;

import static org.junit.Assert.assertArrayEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;

public class BusinessLayerTests {
	/*
	 * These are unit tests for the Bank of Ben Business Layer
	 */
	
	// Will be executed before testing begins
	
	private static BusinessLayer bl = new BusinessLayer();
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Getting ready for all the Business Layer tests\n");
	}
	
	// Will be executed after each test
	@Before
	public void before() {
		System.out.println("\nAttempting the next Business Layer test");
	}
	
	@Test
	public void loginUser_properCall_employeeReturned() throws BusinessException{
		User u = bl.loginUser("michaelscarn", "Ih34rtH0lly!");
		if (u instanceof Employee) {
			Employee e = (Employee) u;
			assertArrayEquals(
					new String[] {"Michael", "Gary", "Scott", "Kevis", "michael.scott@dundermifflin.com", "michaelscarn", "Ih34rtH0lly!",
							"Branch Manager", "EMSWBE1992100000"},
					new String[] {e.getFirstName(), e.getMiddleName(), e.getLastName(), e.getMomsMaidenName(), e.getEmail(), e.getUsername(), e.getPassword(),
							e.getDesignation(), e.getSupervisorEmployeeId()});
			assertArrayEquals(
					new long[] {987654321L, 9287437243L},
					new long[] {e.getSsn(), e.getPhoneNumber()});
			assertArrayEquals(
					new Date[] {new GregorianCalendar(1964, 02, 15).getTime()}, 
					new Date[] {e.getDob()});
		} else if (u instanceof Customer) {
			throw new BusinessException("Test failed. Customer returned instead of employee.");
		} else {
			throw new BusinessException("Test failed. User returned instead of employee.");
		}
	}
	
	// Will be executed after each test
	@After
	public void testEquals() {
		System.out.println("Completed test");
	}
	
	// Will be executed after testing begins
	@AfterClass
	public static void after() {
		System.out.println("Completed all Business Layer tests!");
	}

}

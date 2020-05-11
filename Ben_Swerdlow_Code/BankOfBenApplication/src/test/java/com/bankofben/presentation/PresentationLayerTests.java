package com.bankofben.presentation;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Employee;
import com.bankofben.models.User;

public class PresentationLayerTests {
	/*
	 * These are unit tests for the Bank of Ben Application (i.e. the user interface)
	 */
	
	// Will be executed before testing begins
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Getting ready for all the Application tests\n");
	}
	
	// Will be executed after each test
	@Before
	public void before() {
		System.out.println("\nAttempting the next Application test");
	}
	
	@Test
	public void requestUserInfo_properCall_userReturned() throws BusinessException {
		
		PresentationLayer pl = new PresentationLayer();
		String inputs = "test@gmail.com\n"
				+ "testUsername\n"
				+ "111-11-1112\n"
				+ "testFirst\n"
				+ "testMiddle\n"
				+ "testLast\n"
				+ "testMom\n"
				+ "10-10-2000\n"
				+ "(123) 456-7890\n"
				+ "P4ssw0rd!\n"
				+ "P4ssw0rd!\n";
		User u = pl.requestUserInfo(new Scanner(new ByteArrayInputStream(inputs.getBytes())));
		assertArrayEquals(
			new String[] {"TestFirst", "TestMiddle", "TestLast", "TestMom", "test@gmail.com", "testUsername", "P4ssw0rd!"}, 
			new String[] {u.getFirstName(), u.getMiddleName(), u.getLastName(), u.getMomsMaidenName(), u.getEmail(), u.getUsername(), u.getPassword()});
		assertArrayEquals(
				new long[] {111111112L, 1234567890L},
				new long[] {u.getSsn(), u.getPhoneNumber()});
		assertArrayEquals(
				new Date[] {new GregorianCalendar(2000, 9, 10).getTime()},
				new Date[] {u.getDob()});
	}
	
	@Test
	public void requestLoginUserInfo_properCall_employeeReturned() throws BusinessException{
		PresentationLayer pl = new PresentationLayer();
		String inputs = 
				"michaelscarn\n"
				+"iheartholly!\n"
				+"Iheartholly!\n"
				+"IheartHolly!\n"
				+"Ih34rtH0lly!";
		User u = pl.requestLoginUserInfo(new Scanner(new ByteArrayInputStream(inputs.getBytes())));
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
		} else {
			throw new BusinessException("Did not log in employee. Wrong information requested for login employee test.");
		}
	}
	
//	@Test
//	public void approve() {
//		// Test the 
//	}
	
	// Will be executed after each test
	@After
	public void testEquals() {
		System.out.println("Completed test");
	}
	
	// Will be executed after testing begins
	@AfterClass
	public static void after() {
		System.out.println("Completed all Application tests!");
	}

}

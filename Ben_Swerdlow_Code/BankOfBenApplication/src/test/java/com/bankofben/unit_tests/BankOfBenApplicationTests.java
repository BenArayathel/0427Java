package com.bankofben.unit_tests;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bankofben.business.BusinessException;
import com.bankofben.models.User;
import com.bankofben.presentation.PresentationLayer;
import com.bankofben.presentation.ValidationTools;

public class BankOfBenApplicationTests {
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
		User expecteds[] = {new User(
				"testFirst",
				"testMiddle",
				"testLast",
				"testMom",
				LocalDate.parse("10-10-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 
				"111-11-1111",
				"test@gmail.com",
				"1234567890",
				"testUsername",
				"P4ssw0rd!")};
		String inputs = "test@gmail.com\n"
				+ "testUsername\n"
				+ "testFirst\n"
				+ "testMiddle\n"
				+ "testLast\n"
				+ "testMom\n"
				+ "10-10-2000\n"
				+ "111-11-1111\n"
				+ "(123) 456-7890\n"
				+ "P4ssw0rd!\n"
				+ "P4ssw0rd!\n";
		User actuals[] = {
				pl.requestUserInfo(
						new Scanner(
								new ByteArrayInputStream(
										inputs.getBytes())))};
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void requestLoginUserInfo_properCall_userReturned() throws BusinessException{PresentationLayer pl = new PresentationLayer();
		User expecteds[] = {new User(
			"testFirst",
			"testMiddle",
			"testLast",
			"testMom",
			LocalDate.parse("10-10-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 
			"111-11-1111",
			"test@gmail.com",
			"1234567890",
			"testUsername",
			"P4ssw0rd!")};
		
	}
	
	@Test
	public void approve() {
		// Test the 
	}
	
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

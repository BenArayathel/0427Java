package com.bankofben.unit_tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public static void before() {
		System.out.println("\nAttempting the next Application test");
	}
	
	@Test
	public static void registerUser() {
		// Test the user registration process
//		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public static void approve() {
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
		
	}

}

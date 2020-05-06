package com.bankofben.unit_tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeTests {
	/*
	 * These are tests for actions an employee can do (e.g. view accounts, and approve/deny applications)
	 */

	// Will be executed before testing begins
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Getting ready for all User Tests\n");
	}
	
	// Will be executed after each test
	@Before
	public static void before() {
		System.out.println("\nAttempting the next User Test");
	}
	
	@Test
	public static void applyForAccount() {
		
	}
	
	@After
	public static void after() {
		
	}
	
	@AfterClass
	public static void afterClass() {
		
	}
	
}

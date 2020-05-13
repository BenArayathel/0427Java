package com.bankofben.business;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import com.bankofben.services.BankOfBenServices;

public class DatabaseServicesTests {
	/*
	 * These are unit tests for the Bank of Ben Business Layer
	 */
	
	// Will be executed before testing begins
	
	BankOfBenServices dbs = new BankOfBenServices();
	
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
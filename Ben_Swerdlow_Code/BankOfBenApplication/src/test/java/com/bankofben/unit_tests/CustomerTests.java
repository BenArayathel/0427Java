package com.bankofben.unit_tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTests {
	/*
	 * These are tests that a customer can do (e.g. manipulate their accounts)
	 */

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
	public static void makeWithdrawal() {
		// Test the process for withdrawing money from an account
	}
	
	@Test
	public static void makeDeposit() {
		// Test the process for depositing money into an account
	}
	
	@After
	public static void after() {
		
	}
	
	@AfterClass
	public static void afterClass() {
		
	}
}

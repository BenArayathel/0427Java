package com.bankofben.presentation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	/*
	 * These are unit tests for things that users can do (e.g. apply for an account)
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

package com.unit_tests;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;// static import means we can directly access the method

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.calculator.Calculator;

public class CalculatorTest {
	
	@BeforeClass
	//this needs to be done before the class is instantiated if not static
	// so we'll make it static
	public static void beforeClass() {
		System.out.println("Getting ready for all the tests\n");
	}
	
	@Before
	public void beforeTest() {
		System.out.println("Getting ready for a test");
	}
	
	@Test//This tells the compiler this is a test
	public void addTest() {
		assertEquals(5, Calculator.add(2, 3));
		// assertEquals(expectedResult, actualTestResult)
		System.out.println("Testing add");
	}
	
	@Test
	public void returnStringTest() {
		assertEquals("Hello", Calculator.returnString("Hello"));
		System.out.println("Testing returnString");
	}
	
//	@Test
//	public void multiplyTest() {
//		fail("Method has not been implemented yet");
//	}
	
	@After
	public void afterTest() {
		System.out.println("Relaxing after a test\n");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("\nRelaxing after all the tests");
	}

}

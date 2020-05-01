package com.test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.calculator.Calculator;

public class CalculatorTest {
	
	@BeforeClass
	public static void beforeClass() { // Have to have static because it can't actually run before the test class has been instantiated
		System.out.println("Getting ready for the tests");
	}
	
	@Before // Will run this before every single test
	public void beforeTest() {
		System.out.println("Getting ready for a test\n");
	}
	
	@After
	public void afterTest() {
		System.out.println("Hopefully all went well \n");
	}
	
	@AfterClass //After the class is done
	public static void AfterTest() {
		System.out.println("Relaxing after all of the tests");
	}

	@Test
	public void addTest() {
		assertEquals(5, Calculator.add(2, 3));
		//(expected, actual) results
		System.out.println("Testing Add");
	} 
	
	@Test
	public void multiplyTest() {
		assertEquals(6, Calculator.multiply(2,3));
		
	}
	

}









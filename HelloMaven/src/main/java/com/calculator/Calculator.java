package com.calculator;

public class Calculator {
	
	/*
	 * Unit testing, is testing the smallest unit of your code. In OOP that is typically 
	 * 	the methods within your objects.
	 *They are automated test to check the functionality of your methods.
	 *
	 *Unit Testing -> Integration testing - > System testing
	 *
	 *Using JUnit to complete Unit testing
	 *
	 *Benefits?
	 *	Creates reliable code as well as the practice of creating modular tests means that code 
	 *	can be tested every time changes occur.
	 *
	 *TDD: Test Driven Development
	 *	First create the test methods, then implement the code to pass the test. 
	 *	It ensures that your code is designed to serve a purpose
	 */
	
	public static int add(int a, int b) {
		return a + b;
	}
	
	public static String returnString(String a) {
		return a ;
	}

}

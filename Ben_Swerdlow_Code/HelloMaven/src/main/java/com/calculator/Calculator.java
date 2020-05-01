package com.calculator;

public class Calculator {
	
	/*
	 * Unit testing: Testing the smallest unit of your code. 
	 * 				In OOP that is typically the methods within your objects.
	 * 
	 * They are automated tests to check the functionality of your methods.
	 * 
	 * Unit testing is supposed to be the FIRST thing you do in the cycle
	 * 		It'll test the smallest units, and then we build up
	 * 
	 * Unit Testing -> Integration Testing -> System Testing
	 * 
	 * Using JUnit to complete Unit Testing
	 * 
	 * Benefits of Unit Testing:
	 * 		Creates reliable, modular code
	 * 		The practice of creating modular tests means code can be tested every time changes occur
	 * 		YAYAYAYAYAYAY!
	 * 
	 * TDD: Test Driven Development!!!!
	 * 		First create the test methods, then implement the code to pass the test
	 * 		Begin with the end in mind
	 * 		Code designed to serve an explicit purpose
	 */
	
	public static int add(int a, int b) {
		return a + b;
	}
	
	public static String returnString(String a) {
		return a;
	}

}

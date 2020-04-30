package com.example.Strings;

public class MoblieNumberValidation {
	
	public static void main(String[] args) {
		String s = "+91-1234567890";
		if (s.matches("\\+91-[0-9]{10}")) {
			System.out.println("Valid number.");
		} else {
			System.out.println("Invaild number.");
		}
		
		/*
		 * Homework Examples:
		 * Validate SSN (validate with dashes to understand how to add dashes in the regular expressions)
		 * Validate DL
		 * write a regular expression to validate these common patterns
		 * do further research on regular expressions to understand the meaning of all the other characters
		 */
		
		String ssn = "947-54-6450";
		System.out.println("\nValidating Social Security Number: " + ssn);
		if (ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			System.out.println("Valid number.");
		} else {
			System.out.println("Invalid number.");
		}
		
		String driversLicense = "P4568217";
		System.out.println("\nValidating CA Driver's License: " + driversLicense);
		if (driversLicense.matches("[A-Z]{1}[0-9]{7}")) {
			System.out.println("Valid number.");
		} else {
			System.out.println("Invaild number.");
		}
	}

}
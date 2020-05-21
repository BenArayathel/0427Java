package com.april29;

public class Regex {

	public static void main(String[] args) {
		String ssn = "123-45-6789";

		if (ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			System.out.println("Valid SSN");
		} else {
			System.out.println("Invalid SSN");
		}
	}

}

package com.revature.string.demo;

public class RegExpDemo {

	public static void main(String[] args) {
		
		/*
		 * [] : Expression
		 * {} : Length
		 * ^ : Not
		 * 
		 * [a-z]
		 * [A-Z]
		 * [0-9]
		 * */
		
//		String num = "+1 (300)-123-4455";
//		RegExp = \\+[0-9]{1} ([0-9]{3})-[0-9]{3}-[0-9]{4}
//		System.out.println(num.matches("\\+[0-9]{1} \\([0-9]{3}\\)-[0-9]{3}-[0-9]{4}"));
		
		//Validation SSN
		String ssn = "125-45-6789";
		// RegExp to validate SSN: [0-9]{3}-[0-9]{2}-[0-9]{4}
		if (ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			System.out.println("Your SSN is valid!!!!");
		}else {
			System.out.println("Your SSN is not valid :(");
		}
		
		//Validation DL (Florida)
		String dl = "A123-546-78-901-0";
		// RegExp to validate DL: [A]{1}[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{1}
		if (dl.matches("[A-Z]{1}[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{1}")) {
			System.out.println("Your DL is valid!!!!");
		}else {
			System.out.println("Your DL is not valid :(");
		}

	}

}

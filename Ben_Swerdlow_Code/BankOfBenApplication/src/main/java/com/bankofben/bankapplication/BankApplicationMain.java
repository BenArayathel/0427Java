package com.bankofben.bankapplication;

import java.util.Scanner;

public class BankApplicationMain {

	public static void main(String[] args) {
		BankOfBen bob = BankOfBen.getBank();
		User user = null;
		int loginAttempts = 0;
		Scanner sc = new Scanner(System.in);

		greeting();
		String response=null;
		while (response.equalsIgnoreCase(null)) {
			response = requestResponse(sc);
			if (response==null) {
				invalidResponse();
			} else if (response.equalsIgnoreCase("quit")) {
				response = quit(sc);
			}
		}

	}

	private static void invalidResponse() {
		System.out.println("The response given is not a valid option.\nPlease select another option.");
		
	}

	private static String quit(Scanner sc) {
		System.out.println("Are you sure you would like to quit the Bank of Ben application?");
		String response = sc.nextLine();
		if (response.equalsIgnoreCase("quit")) {
			System.out.println("Thank you for using the Bank of Ben Application.");
			System.exit(0);
		}
		return response;
	}

	private static String requestResponse(Scanner sc) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void greeting() {
		// TODO Auto-generated method stub
		
	}

	private static User getUser(String input) {
		User user = null;
		switch (input.toLowerCase()) {
			case "apply": 
				user = bob.applyForAccount();
				break;
			case "login":
				user = bob.signIn();
				break;
			default:
				System.out.println();
		}
		return user;
	}

}

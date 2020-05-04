package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BankApplicationMain {

	public static void main(String[] args) {
		BankOfBen bob = BankOfBen.getBank();
		User user = null;
//		int loginAttempts = 0;
		Scanner sc = new Scanner(System.in);

		greeting();
		String response=null;
		while (user.equals(null)) {
			response = sc.nextLine();
			if (response.equalsIgnoreCase("register")) {
				user = registerUser(sc);
			} else if (response.equalsIgnoreCase("login")) {
				user = bob.loginUser(sc);
			} else if (response.equalsIgnoreCase("quit")) {
				quit(sc);
			} else {
				invalidResponse(response);
				greeting();
			}
		}
	}

	private static void invalidResponse(String response) {
		System.out.println("Your request \""+response+"\" is not a valid option.");
		System.out.println("Please select another option.");
		
	}

	private static void quit(Scanner sc) {
		System.out.println("Are you sure you would like to quit the Bank of Ben application?");
		String response = sc.nextLine();
		if (response.equalsIgnoreCase("quit")) {
			System.out.println("Thank you for using the Bank of Ben Application.");
			System.exit(0);
		}
	}

	private static void greeting() {
		System.out.println("Welcome to the Bank of Ben! Please select from the following options:");
		System.out.println("Type \"register\" to register a new user");
		System.out.println("Type \"login\" to log in.");	
	}
	
	private static User registerUser(Scanner sc) {
		BankOfBen bob = BankOfBen.getBank();
		User user = null;
		String email = requestEmail(sc);
		String username = requestUsername(sc);
		boolean loginRequested = false;
		while (emailExists(email)) {
			System.out.println("The email "+email+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = loginUser(sc);
				username = user.getUsername();
				break;
			} else {
				email = requestEmail(sc);
			}
		}
		while (userExists(username)) {
			System.out.println("The username "+username+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = loginUser(username, sc);
				break;
			} else {
				username = requestUsername(sc);
			}
		}
		if (!(loginRequested)) {
			/* 
			 * Try to register user. These exceptions should have been caught earlier and the user should have
			 * been given a chance to correct the provided information. The exceptions are a safety measure to
			 * ensure erroneous information cannot be registered with BoB. 
			 */
			try {
				user = registerUser(username, email, sc);
			} catch (ExistingUserException e) {
				System.out.println("User registration unsuccessful. User already exists. Please login instead.");
			} catch (UsernameInvalidException e) {
				System.out.println("User registration unsuccessful. Username must be between 4 and 20 characters");
			} catch (ExistingEmailException e) {
				System.out.println("User registration unsuccessful. Email provided already exists. Please login instead.");
			} catch (EmailInvalidException e) {
				System.out.println("User registration unsuccessful. Must supply a valid email address.");
			}
		}
		return user;
	}
	
	private static User registerUser(String username, String email, Scanner sc)
			throws UsernameInvalidException, EmailInvalidException, ExistingUserException, ExistingEmailException {
		BankOfBen bob = BankOfBen.getBank();
		User user = null;
		String firstName = UserUtils.requestFirstName(sc);
		String middleName = UserUtils.requestMiddleName(sc);
		String lastName = UserUtils.requestLastName(sc);
		String momsMaidenName = UserUtils.requestMomsMaidenName(sc);
		LocalDate dob = UserUtils.requestDob(sc);
		String ssn = UserUtils.requestSsn(sc);
		String phoneNumber = UserUtils.requestPhoneNumber(sc);
		
		String password = UserUtils.requestNewPassword(sc);
//		User user = new User(username, email, password);
		if (usernameEmailMap.containsKey(username)) {
			throw new ExistingUserException();
		}
		if (usernameEmailMap.containsValue(email)) {
			throw new ExistingEmailException();
		}
		

		bob.registerUser(user);
		System.out.println("User "+user.getUsername()+" registered with the Bank of Ben.");
	}
	
//	private static User getUser(String input) {
//		User user = null;
//		switch (input.toLowerCase()) {
//			case "apply": 
//				user = bob.applyForAccount();
//				break;
//			case "login":
//				user = bob.signIn();
//				break;
//			default:
//				System.out.println();
//		}
//		return user;
//	}

}

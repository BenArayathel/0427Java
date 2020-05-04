package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.util.Scanner;

public class BankApplicationMain {

	public static void main(String[] args) {
//		BankOfBen bob = BankOfBen.getBank();
		User user = null;
//		int loginAttempts = 0;
		Scanner sc = new Scanner(System.in);

		greeting();
		String response=null;
		
		do {
			greeting();
			response = sc.nextLine();
			if (response.equalsIgnoreCase("register")) {
				user = registerUser(sc);
				if (user.equals(null)) {
					invalidRegistrationMessage();
				}
			} else if (response.equalsIgnoreCase("login")) {
				user = loginUser(sc);
				if (user.equals(null)) {
					invalidLoginMessage();
				}
			} else if (response.equalsIgnoreCase("quit")) {
				quit(sc);
			} else {
				invalidResponseMessage(response);
			}
		} while (user.equals(null));
		
	}

	private static void invalidResponseMessage(String response) {
		System.out.println("Your request \""+response+"\" is not a valid option.");
		System.out.println("Please select another option.");
	}
	
	private static void invalidRegistrationMessage() {
		System.out.println("Invalid registration. Please try again.");
	}
	
	private static void invalidLoginMessage() {
		System.out.println("Invalid login. Please try again.");
	}

	private static void quit(Scanner sc) {
		System.out.println("Are you sure you would like to quit the Bank of Ben Application?");
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
		System.out.println("type \"quit\" to quit the application");
	}
	
	private static User registerUser(Scanner sc) {
		BankOfBen bob = BankOfBen.getBank();
		User user = null;
		String email = UserUtils.requestEmail(sc);
		String username = UserUtils.requestUsername(sc);
		boolean loginRequested = false;
		while (bob.emailExists(email)) {
			System.out.println("The email "+email+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = loginUser(sc);
				username = user.getUsername();
				break;
			} else {
				email = UserUtils.requestEmail(sc);
			}
		}
		while (bob.userExists(username) && !(loginRequested)) {
			System.out.println("The username "+username+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = loginUser(username, sc);
				break;
			} else {
				username = UserUtils.requestUsername(sc);
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
		String firstName = UserUtils.requestFirstName(sc);
		String middleName = UserUtils.requestMiddleName(sc);
		String lastName = UserUtils.requestLastName(sc);
		String momsMaidenName = UserUtils.requestMomsMaidenName(sc);
		LocalDate dob = UserUtils.requestDob(sc);
		String ssn = UserUtils.requestSsn(sc);
		String phoneNumber = UserUtils.requestPhoneNumber(sc);
		
		String password = UserUtils.requestNewPassword(sc);
		
		User user = bob.registerUser(new User());
//		if (bob.getUsernameEmailMap().containsKey(username)) {
//			throw new ExistingUserException();
//		}
//		if (bob.getUsernameEmailMap().containsValue(email)) {
//			throw new ExistingEmailException();
//		}
		if (!(user.equals(null))) {
			System.out.println("User "+user.getUsername()+" registered with the Bank of Ben.");
		}
		return user;
	}
	
	public static User loginUser(String username, Scanner sc) {
		String password = null;
		int loginAttempts = 0;
		User user = null;
		BankOfBen bob = BankOfBen.getBank();
		while (loginAttempts < 4) {
			password = UserUtils.requestPassword(sc);
			try {
				user = bob.loginUser(username, password);
			} catch (InvalidLoginException e) {
				System.out.println("Invalid password for user "+username+". Please try again.");
			} catch (NullPointerException | UserNotFoundException e) {
				System.out.println("Bank of Ben has no record of user with those credentials. Please try again.");
			}
			loginAttempts++;
			// TODO: Added lag to discourage brute force attempts; not critical, attempt later
		}
		if (loginAttempts >= 4) {
			System.out.println("Limit of password attempts exceeded. Please try again later.");
		}
		return user;
	}
	
	public static User loginUser(Scanner sc) {
		String username = UserUtils.requestUsername(sc);
		return loginUser(username, sc);
	}

}

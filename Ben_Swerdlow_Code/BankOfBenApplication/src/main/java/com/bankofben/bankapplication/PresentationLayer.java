package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.util.Scanner;

public class PresentationLayer {

	public static void main(String[] args) {
		BusinessLayer bob = BusinessLayer.getBank();
		User user = null;
//		int loginAttempts = 0;
		Scanner sc = new Scanner(System.in);
		String response=null;

		printUserGreeting();
		boolean userResponseValidated = false;
		do {
			printUserOptions();
			response = sc.nextLine();
			if (response.equalsIgnoreCase("register")) {
				try {
					user = requestUserInfo(sc);
					userResponseValidated = true;
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					printInvalidRegistrationMessage();
				}
			} else if (response.equalsIgnoreCase("login")) {
				try {
					user = requestLoginUserInfo(sc);
					userResponseValidated = true;
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					printInvalidLoginMessage();
				}
			} else if (response.equalsIgnoreCase("quit")) {
				quit(sc);
//				userResponseValidated = true;
			} else {
				printInvalidResponseMessage(response);
			}
		} while (!(userResponseValidated));
		
		if (user instanceof Customer) {
			Customer customer = (Customer) user;
			printCustomerGreeting(customer);
			do {
				printCustomerOptions();
				response = sc.nextLine();
				if (response.equalsIgnoreCase("view")) {
					// TODO: view balance(s) logic
				} else if (response.equalsIgnoreCase("withdraw")) {
					// TODO: withdraw logic
				} else if (response.equalsIgnoreCase("deposit")) {
					// TODO: deposit logic
				} else if (response.equalsIgnoreCase("apply")) {
					// TODO: apply logic
				} else if (response.equalsIgnoreCase("quit")) {
					quit(sc);
				} else {
					printInvalidResponseMessage(response);
				}
			} while(true);
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			printEmployeeGreeting(employee);
			while (true) {
				printEmployeeOptions();
				response = sc.nextLine();
				if (response.equalsIgnoreCase("view")) {
					// TODO: view customer accounts logic
				} else if (response.equalsIgnoreCase("applications")) {
					// TODO: applications logic
				} else if (response.equalsIgnoreCase("log")) {
					// TODO: log logic
				} else if (response.equalsIgnoreCase("quit")) {
					quit(sc);
				} else {
					printInvalidResponseMessage(response);
				}
			}
		} else {
			bob.applyForAccount(user);
		}
		
	}

	private static void printInvalidResponseMessage(String response) {
		System.out.println("I am sorry. Your request \""+response+"\" is not a valid option.");
		System.out.println("Please try again.\n");
	}
	
	private static void printInvalidRegistrationMessage() {
		System.out.println("Invalid registration. Please try again.");
	}
	
	private static void printInvalidLoginMessage() {
		System.out.println("Invalid login. Please try again.");
	}

	private static void quit(Scanner sc) {
		System.out.println("Are you sure you would like to quit the Bank of Ben Application?");
		System.out.println("Type \"quit\" again to confirm. Enter anything else to return to your options.");
		String response = sc.nextLine();
		if (response.equalsIgnoreCase("quit")) {
			System.out.println("Thank you for using the Bank of Ben Application.");
			System.exit(0);
		}
	}

	private static void printUserGreeting() {
		System.out.println("Welcome to the Bank of Ben!\n");
	}
	
	private static void printUserOptions() {
		System.out.println("Please select from the following options:");
		System.out.println("Type \"register\" to register a new user");
		System.out.println("Type \"login\" to log in.");
		System.out.println("Type \"quit\" to quit the application");
	}

	private static void printCustomerGreeting(Customer customer) {
		System.out.println("Welcome "+customer.getFirstName()+" "+customer.getLastName()+"!");
	}
	
	private static void printCustomerOptions() {
		System.out.println("Please select from the following options:");
		System.out.println("Type \"view\" to view your balance(s)");
		System.out.println("Type \"withdraw\" to make a withdrawal from an account");
		System.out.println("Type \"deposit\" to make a deposit into an account");
		System.out.println("Type \"apply\" to apply for a new account");
		System.out.println("Type \"quit\" to quit the application");
	}

	private static void printEmployeeGreeting(Employee employee) {
		System.out.println("Welcome "+employee.getFirstName()+" "+employee.getLastName()+"!");
		System.out.println("Please select from the following options:");
	}
	
	private static void printEmployeeOptions() {
		System.out.println("Please select from the following options:");
		System.out.println("Type \"view\" to view user balances");
		System.out.println("Type \"applications\" to view, approve, or deny account applications.");
		System.out.println("Type \"log\" to view a log of all transactions");
		System.out.println("Type \"quit\" to quit the application");
	}
	
	private static void registrationDisclaimer() {
		System.out.println("Thank you for your interest in registering with the Bank of Ben.\n");
		System.out.println("The following prompts will ask you for personal information necessary to create your "
				+ "application. If you do not intend to apply for an account after all your personal infromation "
				+ "is entered, your information will not be saved.\n");
		System.out.println("Please complete the account application process after these personal information prompts "
				+ "to complete the process. An employee will either approve or deny your account in a timely manner "
				+ "and get back to you via email. If you are denied, your personal information will not be saved.\n");
	}
	
	private static User requestUserInfo(Scanner sc) throws BusinessException {
		BusinessLayer bob = BusinessLayer.getBank();
		User user = null;
		registrationDisclaimer();
		String email = UserInterface.requestEmail(sc);
		String username = UserInterface.requestUsername(sc);
		boolean loginRequested = false;
		while (bob.emailExists(email)) {
			System.out.println("The email "+email+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = requestLoginUserInfo(sc);
				username = user.getUsername();
				break;
			} else {
				email = UserInterface.requestEmail(sc);
			}
		}
		while (bob.userExists(username) && !(loginRequested)) {
			System.out.println("The username "+username+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				try {
					user = bob.loginUser(username, sc);
				} catch (BusinessException e) {
					e.getMessage();
				}
				break;
			} else {
				username = UserInterface.requestUsername(sc);
			}
		}
		if (!(loginRequested)) {
			/* 
			 * Try to register user. These exceptions should have been caught earlier and the user should have
			 * been given a chance to correct the provided information. The exceptions are a safety measure to
			 * ensure erroneous information cannot be registered with BoB. 
			 */
			user = requestUserInfo(username, email, sc);
		}
		return user;
	}
	
	private static User requestUserInfo(String username, String email, Scanner sc) throws BusinessException {
		String firstName = UserInterface.requestFirstName(sc);
		String middleName = UserInterface.requestMiddleName(sc);
		String lastName = UserInterface.requestLastName(sc);
		String momsMaidenName = UserInterface.requestMomsMaidenName(sc);
		LocalDate dob = UserInterface.requestDob(sc);
		String ssn = UserInterface.requestSsn(sc);
		String phoneNumber = UserInterface.requestPhoneNumber(sc);
		String password = UserInterface.requestNewPassword(sc);
		
		User user = new User(firstName, middleName, lastName, momsMaidenName, dob, ssn, email,
				phoneNumber, username, password);
		
		System.out.println("Thank you for your information.");
		
		return user;
	}
	
	public static User requestLoginUserInfo(Scanner sc) throws BusinessException {
		String username = UserInterface.requestUsername(sc);
		BusinessLayer bob = BusinessLayer.getBank();
		return bob.loginUser(username, sc);
	}
//	
//	public static User loginUser(String username, Scanner sc) throws BusinessException {
//		String password = null;
//		int loginAttempts = 0;
//		User user = null;
//		BusinessLayer bob = BusinessLayer.getBank();
//		while (loginAttempts < 4) {
//			password = UserInterface.requestPassword(sc);
//			bob.loginUser(username, password);
//			loginAttempts++;
//			// TODO: Added lag to discourage brute force attempts; not critical, attempt later
//		}
//		if (loginAttempts >= 4) {
//			throw new BusinessException("Limit of password attempts exceeded. Please try again later.");
//		}
//		return user;
//	}

}

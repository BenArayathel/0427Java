package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.util.Scanner;

public class BankApplicationMain {

	public static void main(String[] args) {
//		BankOfBen bob = BankOfBen.getBank();
		User user = null;
//		int loginAttempts = 0;
		Scanner sc = new Scanner(System.in);
		String response=null;

		printUserGreeting();
		do {
			printUserOptions();
			response = sc.nextLine();
			if (response.equalsIgnoreCase("register")) {
				user = registerUser(sc);
				if (user.equals(null)) {
					printInvalidRegistrationMessage();
				}
			} else if (response.equalsIgnoreCase("login")) {
				user = loginUser(sc);
				if (user.equals(null)) {
					printInvalidLoginMessage();
				}
			} else if (response.equalsIgnoreCase("quit")) {
				quit(sc);
			} else {
				printInvalidResponseMessage(response);
			}
		} while (user==null);
		
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
			// user is neither a customer nor an employee
			// assume they are a customer and must apply for an account
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
	
	private static User registerUser(Scanner sc) {
		BankOfBen bob = BankOfBen.getBank();
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
				user = loginUser(sc);
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
				user = loginUser(username, sc);
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
			

			try {
				user = registerUser(username, email, sc);
			} catch (InvalidEmailException e) {
				System.out.println("User registration unsuccessful. Must supply a valid email address.");
			} catch (ExistingUserException e) {
				System.out.println("User registration unsuccessful. User already exists. Please login instead.");
			} catch (ExistingEmailException e) {
				System.out.println("User registration unsuccessful. Email provided already exists. Please login instead.");
			} catch (BlankFieldException e) {
				System.out.println(e.getMessage());
			} catch (InvalidDateOfBirthException e1) {
				System.out.println("User registration unsuccessful. Date of birth invalid.");
			} catch (InvalidSsnException e) {
				System.out.println("User registration unsuccessful. Social security number invalid");
			} catch (InvalidPhoneNumberException e) {
				System.out.println("User registration unsuccessful. Phone number invalid");
			} catch (InvalidUsernameException e) {
				System.out.println("User registration unsuccessful. Username must be between 4 and 20 characters");
			} catch (InvalidPasswordException e) {
				System.out.println("User registration unsuccessful. Password invalid.");
				System.out.println(UserInterface.passwordCriteria());
			} catch (InvalidPasswordChangeException e) {
				System.out.println("User registration unsuccessful. Password for "+username+" already exists. "
						+ "Please login instead.");
			}
		}
		return user;
	}
	
	private static User registerUser(String username, String email, Scanner sc)
			throws InvalidEmailException, ExistingUserException, ExistingEmailException, BlankFieldException,
			InvalidDateOfBirthException, InvalidSsnException, InvalidPhoneNumberException, InvalidUsernameException,
			InvalidPasswordException, InvalidPasswordChangeException {
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
			password = UserInterface.requestPassword(sc);
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
		String username = UserInterface.requestUsername(sc);
		return loginUser(username, sc);
	}

}

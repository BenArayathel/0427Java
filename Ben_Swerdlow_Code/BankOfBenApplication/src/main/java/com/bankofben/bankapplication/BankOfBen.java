package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.TreeMap;

public class BankOfBen {
	
	private TreeMap<String, User> usernameUserMap;
	private TreeMap<String, String> usernameEmailMap;

	public static BankOfBen getBank() {
		// TODO Auto-generated method stub
		// make into singleton
		return null;
	}
	
	public User registerUser(Scanner sc) {
		String email = requestEmail(sc);
		String username = requestUsername(sc);
		boolean loginRequested = false;
		User user = null;
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
				registerUser(username, email, sc);
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
	

	private void registerUser(String username, String email, Scanner sc)
			throws UsernameInvalidException, EmailInvalidException, ExistingUserException, ExistingEmailException {
		String firstName = requestFirstName(sc);
		String middleName = requestMiddleName(sc);
		String lastName = requestLastName(sc);
		String momsMaidenName = requestMomsMaidenName(sc);
		LocalDate dob = requestDob(sc);
		String ssn = requestSsn(sc);
		
		String password = UserUtils.requestNewPassword(sc);
		User user = new User(username, email, password);
		if (usernameEmailMap.containsKey(username)) {
			throw new ExistingUserException();
		}
		if (usernameEmailMap.containsValue(email)) {
			throw new ExistingEmailException();
		}
		this.usernameUserMap.put(username, user);
		this.usernameEmailMap.put(username, email);
		System.out.println("User "+user.getUsername()+" registered with the Bank of Ben.");
	}

	private String requestFirstName(Scanner sc) {
		System.out.println("Please input your first name:");
		String firstName = sc.nextLine();
		while (firstName.equals(null)) {
			System.out.println("You must enter a first name.");
			System.out.println("Please input your first name:");
			firstName = sc.nextLine();
		}
		return firstName;
	}
	
	private String requestMiddleName(Scanner sc) {
		String middleName = null;
		while (middleName.equals(null)) {
			System.out.println("Please input your middle name:");
			middleName = sc.nextLine();
			if (middleName.equals(null)) {
				System.out.println("You have not input a middle name. Is that correct, or would you like to go back and enter one? (y or yes to confirm");
				String response = sc.nextLine();
				if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
					break;
				}
			}
		}
		return middleName;
	}

	private String requestLastName(Scanner sc) {
		System.out.println("Please input your last name:");
		String lastName = sc.nextLine();
		while (lastName.equals(null)) {
			System.out.println("You must enter a last name.");
			System.out.println("Please input your last name:");
			lastName = sc.nextLine();
		}
		return lastName;
	}
	
	private String requestMomsMaidenName(Scanner sc) {
		System.out.println("Please input your mother's maiden name:");
		String momsMaidenName = sc.nextLine();
		while (momsMaidenName.equals(null)) {
			System.out.println("You must enter your mother's maiden name.");
			System.out.println("Please input your mother's maiden name:");
			momsMaidenName = sc.nextLine();
		}
		return momsMaidenName;
	}
	
	private LocalDate requestDob(Scanner sc) {
		boolean noDate = true;
		LocalDate dob = null;
		while (noDate) {
			System.out.println("Please input your date of birth in the following format: DD-MM-YYYY");
			String dmyDob = sc.nextLine();
			StringBuilder ymdDob = new StringBuilder();
			for (int i=0; i<3; i++) {
				ymdDob.append(dmyDob.split("-")[2-i]);
			}
			try {
				dob = LocalDate.parse(ymdDob);
				noDate = false;
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date entry "+dmyDob);
			}
		}
		return dob;
	}
	
	private String requestSsn(Scanner sc) {
		System.out.println("Please input your social security number (XXX-XX-XXXX");
		String ssn = sc.nextLine();
		while (!(UserUtils.isValidSsn(ssn))) {
			System.out.println("Invalid social security number entry "+ssn);
			System.out.println("Please input your social security number (XXX-XX-XXXX");
			ssn = sc.nextLine();
		}
		return ssn;
	}

	private User loginUser(String username, Scanner sc) {
		String password = null;
		int loginAttempts = 0;
		while (!(passwordCorrect(username, password))) {
			if (loginAttempts > 4) {
				System.out.println("Limit of password attempts exceeded. Please try again later.");
				break;
			} else if (loginAttempts > 0) {
				System.out.println("Incorrect password.");
			}
			password = UserUtils.requestPassword(sc);
			loginAttempts++;
			// TODO: Added lag to discourage brute force attempts; not critical, attempt later
		}
		return this.usernameUserMap.get(username);
	}
	
	public User loginUser(Scanner sc) {
		String username = requestUsername(sc);
		return loginUser(username, sc);
	}

	private String requestUsername(Scanner sc) {
		System.out.println("Please input username:");
		String username = sc.nextLine();
		while (!(UserUtils.isValidUsername(username))) {
			System.out.println("You must supply a username between 4 and 20 characters.");
			System.out.println("Please input username:");
			username = sc.nextLine();
		}
		return username;
	}

	private String requestEmail(Scanner sc) {
		System.out.println("Please input your email address:");
		String email = sc.nextLine();
		while (!(UserUtils.isValidEmail(email))) {
			System.out.println("You must provide a valid email address.");
			System.out.println("Please input your email address:");
			email = sc.nextLine();
		}
		return email;
	}

	public boolean userExists(String username) {
		// Simple method to make intent clearer
		return this.usernameUserMap.containsKey(username);
	}
	
	public boolean emailExists(String email) {
		// simple method to make intent clearer
		return this.usernameEmailMap.containsValue(email);
	}
	
	public boolean passwordCorrect(String username, String password) {
		// Simple method to make intent clearer
		return (this.usernameUserMap.get(username).getPassword()==password);
	}

	public TreeMap<String, User> getUsernameUserMap() {
		return usernameUserMap;
	}

	public void setUsernameUserMap(TreeMap<String, User> usernameUserMap) {
		// Make this require employee id and approval
		// and update usernameEmailMap
		this.usernameUserMap = usernameUserMap;
	}

	public TreeMap<String, String> getUsernameEmailMap() {
		return usernameEmailMap;
	}

	public void setUsernameEmailMap(TreeMap<String, String> usernameEmailMap) {
		// Make this require employee id and approval
		// and update usernameEmailMap
		this.usernameEmailMap = usernameEmailMap;
	}
	
	
	
}

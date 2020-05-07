package com.bankofben.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInterface {
	
	/*
	 * UserInterface is a class that contains static methods that facilitate communication between the user
	 * and the application. This is separate from BankApplicationMain because these methods may be needed
	 * by other classes besides BankApplicationMain.
	 */

	public static String requestUsername(Scanner sc) {
		System.out.println("Please input username:");
		String username = sc.nextLine();
		while (!(ValidationTools.isValidUsername(username))) {
			System.out.println("You must supply a username between 4 and 20 characters.");
			System.out.println("Please input username:");
			username = sc.nextLine();
		}
		return username;
	}

	public static String requestEmail(Scanner sc) {
		System.out.println("Please input your email address:");
		String email = sc.nextLine();
		while (!(ValidationTools.isValidEmail(email))) {
			System.out.println("You must provide a valid email address.");
			System.out.println("Please input your email address:");
			email = sc.nextLine();
		}
		return email;
	}
	
	public static String requestNewPassword(Scanner sc) {
		String password = "";
		String confirmPassword = "";
		boolean confirmed = false;
		while (!(confirmed)) {
			System.out.println(passwordCriteria());
			System.out.println("Please input password:");
			password = sc.nextLine();
			System.out.println("Please condfirm password:");
			confirmPassword = sc.nextLine();
			if (ValidationTools.isValidPassword(password)) {
				if (password.equals(confirmPassword)) {
					confirmed = true;
				} else {
					System.out.println("Password confirmation failed to match password. Please try again.");
				}
			} else {
				System.out.println("Invalid password. Pleast try again.");
			}
		}
		return password;
	}
	
	public static String requestPassword(Scanner sc) {
		System.out.println("Please input password:");
		return sc.nextLine();
	}

	public static String requestFirstName(Scanner sc) {
		System.out.println("Please input your first name:");
		String firstName = sc.nextLine();
		while (firstName.isEmpty()) {
			System.out.println("You must enter a first name.");
			System.out.println("Please input your first name:");
			firstName = sc.nextLine();
		}
		return firstName;
	}
	
	public static String requestMiddleName(Scanner sc) {
		String middleName = "";
		while (middleName.isEmpty()) {
			System.out.println("Please input your middle name:");
			middleName = sc.nextLine();
			if (middleName.isEmpty()) {
				System.out.println("You have not input a middle name. Is that correct, or would you like to go "
						+ "back and enter one? (y or yes to confirm)");
				String response = sc.nextLine();
				if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
					break;
				}
			}
		}
		return middleName;
	}

	public static String requestLastName(Scanner sc) {
		System.out.println("Please input your last name:");
		String lastName = sc.nextLine();
		while (lastName.isEmpty()) {
			System.out.println("You must enter a last name.");
			System.out.println("Please input your last name:");
			lastName = sc.nextLine();
		}
		return lastName;
	}
	
	public static String requestMomsMaidenName(Scanner sc) {
		System.out.println("Please input your mother's maiden name:");
		String momsMaidenName = sc.nextLine();
		while (momsMaidenName.isEmpty()) {
			System.out.println("You must enter your mother's maiden name.");
			System.out.println("Please input your mother's maiden name:");
			momsMaidenName = sc.nextLine();
		}
		return momsMaidenName;
	}
	
	public static LocalDate requestDob(Scanner sc) {
//		boolean noDate = true;
		LocalDate dob = null;
		boolean enteredCorrectly = false;
		String monthDayYearDobString;
		while (!(enteredCorrectly)) {
			System.out.println("Please input your date of birth in the following format: MM-DD-YYYY "
					+ "(e.g. January 1st, 2000 would be 01-01-2000)");
			monthDayYearDobString = sc.nextLine();
			if (ValidationTools.isValidDateString(monthDayYearDobString)) {
				DateTimeFormatter monthDayYearFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
				try {
					dob = LocalDate.parse(monthDayYearDobString, monthDayYearFormat);
					enteredCorrectly = true;
				} catch (DateTimeParseException e) {
					System.out.println(monthDayYearDobString+" is not a valid entry for date of birth. "
							+ "Dates of birth should be of form MM-DD-YYYY (e.g. 01-01-2000)");
				}
			} else {
				System.out.println(monthDayYearDobString+" is not a valid entry for date of birth. "
							+ "Dates of birth should be of form MM-DD-YYYY (e.g. 01-01-2000)");
			}
		}
		return dob;
//		System.out.println(monthDayYearDobString);
//		String yearMonthDayDob = monthDayYearDobString;
//		
////		do {
////			// while year-month-day is invalid (which it is at first)
////			while (!(ValidationTools.isValidDateString(monthDayYearDobString))) {
////				// while monthDayYear is invalid (which it hopefully isn't)
////				System.out.println("Invalid date format: "+monthDayYearDobString);
////				System.out.println("Please input your date of birth in the following format: MM-DD-YYYY");
////				monthDayYearDobString = sc.nextLine();
////			}
////			StringBuilder ymdDob = new StringBuilder();
////			for (int i=0; i<3; i++) {
////				ymdDob.append(monthDayYearDobString.split("-")[(i+2)%3]);
////			}
////			yearMonthDayDob = ymdDob.toString();
////			// Set to null so if we have to repeat the loop, the user has a chance to re-enter information
////			monthDayYearDobString = null;
////		} while (!(ValidationTools.isValidDate(yearMonthDayDob)));
//		
//		dob = LocalDate.parse(yearMonthDayDob);
//
//		return dob;
	}
	
	public static String requestSsn(Scanner sc) {
		System.out.println("Please input your social security number (XXX-XX-XXXX)");
		String ssn = sc.nextLine();
		while (!(ValidationTools.isValidSsn(ssn))) {
			System.out.println("Invalid social security number entry "+ssn);
			System.out.println("Please input your social security number (XXX-XX-XXXX");
			ssn = sc.nextLine();
		}
		return ssn;
	}
	
	public static String phoneNumberCriteria() {
		return "Valid phone numbers\n"
				+ "* May or may not contain the +1 country code\n"
				+ "* May or may not contain spaces or following symbols: ( ) -\n"
				+ "* Must contain exactly 10 digits excluding the above optional additions";
	}

	public static String requestPhoneNumber(Scanner sc) {
		System.out.println("Please input your US phone number: ");
		String phoneNumber = sc.nextLine();
		while (!(ValidationTools.isValidPhoneNumber(phoneNumber))) {
			System.out.println("Invalid phone number entry "+phoneNumber);
			System.out.println("Please input your US phone number: ");
			phoneNumber = sc.nextLine();
		}
		return phoneNumber;
	}
	
	public static String passwordCriteria() {
		return "Passwords must be at least 8 character and contain"
				+ "\n* At least one uppercase English letter"
				+ "\n* At least one lowercase English letter"
				+ "\n* At least one digit"
				+ "\n* At least one special character (.*?[#?!@$%^&*-)";
	}

	public static String ssnCriteria() {
		return "Social security number must be exactly 9 digits, excluding the - symbol.";
	}

}

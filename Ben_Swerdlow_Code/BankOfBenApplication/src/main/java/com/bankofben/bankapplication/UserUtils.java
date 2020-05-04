package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserUtils {
	
	public static String requestNewPassword(Scanner sc) {
		String password = null;
		String confirmPassword = null;
		boolean confirmed = false;
		while (!(confirmed)) {
			System.out.println("Please input password:");
			password = sc.nextLine();
			System.out.println("Please condfirm password:");
			confirmPassword = sc.nextLine();
			if (password.equals(confirmPassword) && !(password.equals(null))) {
				confirmed = true;
			} else if (password.equals(null)){
				System.out.println("No password entry detected. Please enter your password again.");
			} else {
				System.out.println("Password and confirmation password do not match. Please enter your password again.");
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
		while (firstName.equals(null)) {
			System.out.println("You must enter a first name.");
			System.out.println("Please input your first name:");
			firstName = sc.nextLine();
		}
		return firstName;
	}
	
	public static String requestMiddleName(Scanner sc) {
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

	public static String requestLastName(Scanner sc) {
		System.out.println("Please input your last name:");
		String lastName = sc.nextLine();
		while (lastName.equals(null)) {
			System.out.println("You must enter a last name.");
			System.out.println("Please input your last name:");
			lastName = sc.nextLine();
		}
		return lastName;
	}
	
	public static String requestMomsMaidenName(Scanner sc) {
		System.out.println("Please input your mother's maiden name:");
		String momsMaidenName = sc.nextLine();
		while (momsMaidenName.equals(null)) {
			System.out.println("You must enter your mother's maiden name.");
			System.out.println("Please input your mother's maiden name:");
			momsMaidenName = sc.nextLine();
		}
		return momsMaidenName;
	}
	
	public static LocalDate requestDob(Scanner sc) {
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
	
	public static String requestSsn(Scanner sc) {
		System.out.println("Please input your social security number (XXX-XX-XXXX");
		String ssn = sc.nextLine();
		while (!(UserUtils.isValidSsn(ssn))) {
			System.out.println("Invalid social security number entry "+ssn);
			System.out.println("Please input your social security number (XXX-XX-XXXX");
			ssn = sc.nextLine();
		}
		return ssn;
	}

	public static String requestPhoneNumber(Scanner sc) {
		System.out.println("Please input your US phone number: ");
		String phoneNumber = sc.nextLine();
		while (!(isValidPhoneNumber(phoneNumber))) {
			System.out.println("Invalid phone number entry "+phoneNumber);
			System.out.println("Please input your US phone number: ");
			phoneNumber = sc.nextLine();
		}
		return phoneNumber;
	}
	
	public static boolean isValidUsername(String username) {
		if (username.equals(null)) {
			return false;
		} else if (username.length() < 4 || username.length() > 20) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isValidEmail(String email) {
		if (email.equals(null)) {
			return false;
		}
		// From http://regexlib.com/REDetails.aspx?regexp_id=26
		else if (!(email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|"
				+ "(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"))) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isValidSsn(String ssn) {
		if (ssn.equals(null)) {
			return false;
		}
		else if (ssn.replace(" ", "").replace("-", "").matches("[0-9]{9}")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidPhoneNumber(String phoneNum) {
		if (phoneNum.equals(null)) {
			return false;
		} else {
			String phoneNumber = phoneNum.replace("-", "").replace("(", "").replace(")", "").replace("+1", "");
			if (phoneNumber.matches("[0-9]{10}")) {
				return true;
			} else {
				return false;
			}
		}
	}

}

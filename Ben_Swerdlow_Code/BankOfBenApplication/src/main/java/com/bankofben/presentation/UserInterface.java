package com.bankofben.presentation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
//import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.bankofben.business.BusinessLayer;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;

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
	
	public static Date requestDob(Scanner sc) {
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
		return GregorianCalendar.from(dob.atStartOfDay(ZoneId.systemDefault())).getTime();
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
	
	public static long requestSsn(Scanner sc) throws BusinessException {
		System.out.println("Please input your social security number (XXX-XX-XXXX)");
		String ssn = sc.nextLine();
		while (!(ValidationTools.isValidSsn(ssn))) {
			System.out.println("Invalid social security number entry "+ssn);
			System.out.println("Please input your social security number (XXX-XX-XXXX");
			ssn = sc.nextLine();
		}
		long result;
		try {
			result = Long.parseLong(ssn.replace("-","").replace(" ", ""));
		} catch (NumberFormatException e) {
			throw new BusinessException("Unable to read social security number. "
					+ "Please restart the process and try again.");
		}
		return result;
	}
	
	public static String phoneNumberCriteria() {
		return "Valid phone numbers\n"
				+ "* May or may not contain the +1 country code\n"
				+ "* May or may not contain spaces or following symbols: ( ) -\n"
				+ "* Must contain exactly 10 digits excluding the above optional additions";
	}

	public static long requestPhoneNumber(Scanner sc) throws BusinessException {
		System.out.println("Please input your US phone number: ");
		String phoneNumber = sc.nextLine();
		while (!(ValidationTools.isValidPhoneNumber(phoneNumber))) {
			System.out.println("Invalid phone number entry "+phoneNumber);
			System.out.println("Please input your US phone number: ");
			phoneNumber = sc.nextLine();
		}
		long result;
		try {
			result = Long.parseLong(phoneNumber.replace("-","").replace("(","").replace(")","").replace(" ", ""));
		} catch (NumberFormatException e) {
			throw new BusinessException("Unable to read phone number. "
					+ "Please restart the process and try again.");
		}
		return result;
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

	public static String requestTransferType(Account account, Scanner sc) throws BusinessException {
		String transferType = null;
		System.out.println("Type \"pay\" if you would like to add money to account "+account.getAccountNumber());
		System.out.println("Type \"request\" if you would like to request money from account "+account.getAccountNumber());
		transferType = sc.nextLine();
		while(!(transferType.equalsIgnoreCase("pay") || transferType.equalsIgnoreCase("request"))) {
			System.out.println("Your entry \""+transferType+"\" is not a valid option. Please try again.");
			System.out.println("Type \"pay\" if you would like to add money to account "+account.getAccountNumber());
			System.out.println("Type \"request\" if you would like to request money from account"+account.getAccountNumber());
			transferType = sc.nextLine();
		}
		return transferType;
	}

	public static Account requestOtherAccount(Scanner sc) {
		BusinessLayer bl = new BusinessLayer();
		boolean destinationChosen = false;
		String destinationAccountString = null;
		String destinationRoutingString = null;
		long destinationAccountNumber = 0;
		long destinationRoutingNumber = 0;
		Account destinationAccount = null;
		while (!destinationChosen) {
			boolean accountNumberValid = false;
			while (!accountNumberValid) {
				System.out.println("Please input the 10-digit account number for the account to "
						+ "which you would like to post a money transfer.");
				destinationAccountString = sc.nextLine();
				try {
					destinationAccountNumber = bl.validateAccountNumber(destinationAccountString);
					accountNumberValid = true;
				} catch (BusinessException e) {
					System.out.println(e.getMessage()+"\nPlease try again.");
				}
			}
			boolean routingNumberValid = false;
			while (!routingNumberValid) {
				System.out.println("Please input the 9-digit routing number for the account into "
						+ "which you would like to post a money transfer.");
				destinationRoutingString = sc.nextLine();
				try {
					destinationRoutingNumber = bl.validateRoutingNumber(destinationRoutingString);
				} catch (BusinessException e){
					System.out.println(e.getMessage()+"\nPlease try again.");
				}
			}
			try {
				destinationAccount = bl.getAccount(destinationAccountNumber, destinationRoutingNumber);
				destinationChosen = true;
			} catch (BusinessException e){
				System.out.println(e.getMessage()+"\nPlease try again.");
			}
		}
		return destinationAccount;
	}

	public static Account requestMyChosenAccount(Customer customer, Scanner sc) {
		BusinessLayer bl = new BusinessLayer();
		
		String sourceAccountString = null;
		long sourceAccountNumber = 0;
		String sourceRoutingString = null;
		long sourceRoutingNumber = 0;
		Account sourceAccount = null;
		
		boolean sourceChosen = false;
		while (!sourceChosen) {
			// Get account number
			boolean accountNumberValid = false;
			while (!accountNumberValid) {
				System.out.println("Please input the 10-digit account number for the account of yours "
						+ "you would like to use as the source of this money transfer.");
				sourceAccountString = sc.nextLine();
				try {
					sourceAccountNumber = bl.validateAccountNumber(sourceAccountString);
					accountNumberValid = true;
				} catch (BusinessException e) {
					System.out.println(e.getMessage()+"\nPlease try again.");
				}
			}
			// Get routing number
			boolean routingNumberValid = false;
			while (!routingNumberValid) {
				System.out.println("Please input the 9-digit routing number for the account of yours "
						+ "you would like to use as the source of this money transfer.");
				sourceRoutingString = sc.nextLine();
				try {
					sourceRoutingNumber = bl.validateRoutingNumber(sourceRoutingString);
					routingNumberValid = true;
				} catch (BusinessException e){
					System.out.println(e.getMessage()+"\nPlease try again.");
				}
			}
			// Check account information belongs to customer
			try {
				sourceAccount = bl.validateAccount(sourceAccountNumber, sourceRoutingNumber, customer);
				sourceChosen = true;
			} catch (BusinessException e) {
				System.out.println(e.getMessage()+"\nPlease try again.");
			}
		}
		return sourceAccount;
	}

	public static long requestAccountNumber(Scanner sc) {
		BusinessLayer bl = new BusinessLayer();
		
		String accountString = null;
		long accountNumber = 0;
		boolean accountNumberValid = false;
		while (!accountNumberValid) {
			System.out.println("Please input a Bank of Ben 10-digit account number.");
			accountString = sc.nextLine();
			try {
				accountNumber = bl.validateAccountNumber(accountString);
				accountNumberValid = true;
			} catch (BusinessException e) {
				System.out.println(e.getMessage()+"\nPlease try again.");
			}
		}
		return accountNumber;
	}

	public static double requestPaymentAmount(Account myChosenAccount, Account otherAccount, Scanner sc) {
		BusinessLayer bl = new BusinessLayer();
		boolean amountChosen = false;
		double amount=0;
		String confirmation = null;
		System.out.println("Please enter in the amount to pay account "+otherAccount.getAccountNumber());
		String amountString = sc.nextLine();
		while (!amountChosen) {
			try {
				amount = bl.validateMonetaryAmount(amountString);
				System.out.println("You have chosen to pay "+otherAccount.getAccountNumber()+" $"+amount
						+" from your account "+myChosenAccount.getAccountNumber()+" with balance $"+myChosenAccount.getBalance());
				System.out.println("Please type \"confirm\" to confirm. Please type "
						+ "\"cancel\" to cancel the transfer. Any other entry will "
						+ "prompt you to enter another payment amount.");
				confirmation = sc.nextLine();
				if (confirmation.equalsIgnoreCase("confirm")) {
					if (amount <= myChosenAccount.getBalance()) {
						amountChosen = true;
					} else {
						System.out.println("$"+amount+" exceeds your balance of $"+myChosenAccount.getBalance()
							+" in account "+myChosenAccount.getAccountNumber()+". You cannot pay more than your "
							+ "account's current balance.");
						System.out.println("Please enter in the amount to pay account "+otherAccount.getAccountNumber());
						amountString = sc.nextLine();
					}
				} else if (confirmation.equalsIgnoreCase("cancel")){
					System.out.println("Transfer payment canceled.");
					amount = Double.NaN;
					break;
				} else {
					System.out.println("You have chosen to pay a different amount.");
					System.out.println("Please enter in the amount to pay account "+otherAccount.getAccountNumber());
					amountString = sc.nextLine();
				}
			} catch (BusinessException e) {
				System.out.println(e.getMessage());
				System.out.println("Please enter in the amount to pay account "+otherAccount.getAccountNumber());
				amountString = sc.nextLine();
			}
		}
		return amount;
	}

	public static double requestRequestAmount(Account myChosenAccount, Account otherAccount, Scanner sc) {
		BusinessLayer bl = new BusinessLayer();
		boolean amountChosen = false;
		double amount=0;
		String confirmation = null;
		System.out.println("Please enter in the amount to request from account "+otherAccount.getAccountNumber());
		String amountString = sc.nextLine();
		while (!amountChosen) {
			try {
				amount = bl.validateMonetaryAmount(amountString);
				System.out.println("You have chosen to request $"+amount+" from "+otherAccount.getAccountNumber()
						+" to be placed in your account "+myChosenAccount.getAccountNumber());
				System.out.println("Please type \"confirm\" to confirm. Please type "
						+ "\"cancel\" to cancel the transfer. Any other entry will "
						+ "prompt you to enter another payment amount.");
				confirmation = sc.nextLine();
				if (confirmation.equalsIgnoreCase("confirm")) {
					amountChosen = true;
				} else if (confirmation.equalsIgnoreCase("cancel")){
					System.out.println("Transfer request canceled.");
					amount = Double.NaN;
					break;
				} else {
					System.out.println("You have chosen to request a different amount.");
					System.out.println("Please enter in the amount to request from account "+otherAccount.getAccountNumber());
					amountString = sc.nextLine();
				}
			} catch (BusinessException e) {
				System.out.println(e.getMessage());
				System.out.println("Please enter in the amount to request from account "+otherAccount.getAccountNumber());
				amountString = sc.nextLine();
			}
		}
		return amount;
	}

}

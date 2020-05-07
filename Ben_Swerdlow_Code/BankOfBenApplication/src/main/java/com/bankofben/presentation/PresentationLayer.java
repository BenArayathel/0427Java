package com.bankofben.presentation;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.bankofben.business.BusinessLayer;
import com.bankofben.dao.BankOfBenDAO;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;

public class PresentationLayer {
	
	/*
	 * PUT ALL SYSOUT INFORMATION (AND SYSIN?) INTO THE LOG FILE
	 * 
	 * Presentation: 2-3 slides on technologies, 1 slide on functionality overview, then functionality demo
	 * 
	 */
	
	private static PresentationLayer pl = new PresentationLayer();
	private static BusinessLayer bl = new BusinessLayer();
	

	public static void main(String[] args) {
		User user = null;
//		int loginAttempts = 0;
		Scanner sc = new Scanner(System.in);
		String response=null;

		pl.printUserGreeting();
		boolean userResponseValidated = false;
		do {
			pl.printUserOptions();
			response = sc.nextLine();
			if (response.equalsIgnoreCase("register")) {
				try {
					user = pl.requestUserInfo(sc);
					userResponseValidated = true;
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					pl.printInvalidRegistrationMessage();
				}
			} else if (response.equalsIgnoreCase("login")) {
				try {
					user = pl.requestLoginUserInfo(sc);
					userResponseValidated = true;
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					pl.printInvalidLoginMessage();
				}
			} else if (response.equalsIgnoreCase("quit")) {
				pl.quit(sc);
//				userResponseValidated = true;
			} else {
				pl.printInvalidResponseMessage(response);
			}
		} while (!(userResponseValidated));
		
		userResponseValidated = false;
		
		if (user instanceof Customer) {
			Customer customer = (Customer) user;
			pl.printCustomerGreeting(customer);
			do {
				pl.printCustomerOptions();
				response = sc.nextLine();
				if (response.equalsIgnoreCase("view")) {

					String accountView = null;
					try {
						accountView = bl.viewBalances(customer);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					System.out.println(accountView);
					
				} else if (response.equalsIgnoreCase("deposit")) {

					Account account = null;
					try {
						account = pl.requestCustomerSelectAccountForDeposit(customer, sc);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
					double deposit = pl.requestDepositAmount(account.getBalance(), sc);
					try {
						bl.makeDeposit(deposit, account, customer);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
				} else if (response.equalsIgnoreCase("withdraw")) {
					
					Account account = null;
					try {
						account = pl.requestCustomerSelectAccountForWithdrawal(customer, sc);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
					double withdrawal = pl.requestDepositAmount(account.getBalance(), sc);
					try {
						bl.makeWithdrawal(withdrawal, account, customer);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
				} else if (response.equalsIgnoreCase("apply")) {
					
					try {
						customer = bl.applyForAccount(customer);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
				} else if (response.equalsIgnoreCase("quit")) {
					pl.quit(sc);
				} else {
					pl.printInvalidResponseMessage(response);
				}
			}  while (!(userResponseValidated));
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			pl.printEmployeeGreeting(employee);
			while (true) {
				pl.printEmployeeOptions();
				response = sc.nextLine();
				if (response.equalsIgnoreCase("view")) {
					// TODO: view customer accounts logic
				} else if (response.equalsIgnoreCase("applications")) {
					// TODO: applications logic
				} else if (response.equalsIgnoreCase("log")) {
					// TODO: log logic
				} else if (response.equalsIgnoreCase("quit")) {
					pl.quit(sc);
				} else {
					pl.printInvalidResponseMessage(response);
				}
			}
		} else {
			Customer customer = null;
			try {
				customer = bl.applyForAccount(user);
				System.out.println("Thank you for applying for your account. Your application will be reviewed by a "
						+ "Bank of Ben employee in a timely manner.");
			} catch (BusinessException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Exiting the Bank of Ben Application. Goodbye!");
			System.exit(0);
		}
		
	}

	private void printInvalidResponseMessage(String response) {
		System.out.println("I am sorry. Your request \""+response+"\" is not a valid option.");
		System.out.println("Please try again.\n");
	}

	private void printInvalidRegistrationMessage() {
		System.out.println("Invalid registration. Please try again.");
	}

	private void printInvalidLoginMessage() {
		System.out.println("Invalid login. Please try again.");
	}

	private void quit(Scanner sc) {
		System.out.println("Are you sure you would like to quit the Bank of Ben Application?");
		System.out.println("Type \"quit\" again to confirm. Enter anything else to return to your options.");
		String response = sc.nextLine();
		if (response.equalsIgnoreCase("quit")) {
			System.out.println("Thank you for using the Bank of Ben Application.");
			System.exit(0);
		}
	}

	private void printUserGreeting() {
		System.out.println("Welcome to the Bank of Ben!\n");
	}
	
	private void printUserOptions() {
		System.out.println("Please select from the following options:");
		System.out.println("Type \"register\" to register a new user");
		System.out.println("Type \"login\" to log in.");
		System.out.println("Type \"quit\" to quit the application");
	}

	private void printCustomerGreeting(Customer customer) {
		System.out.println("Welcome "+customer.getFirstName()+" "+customer.getLastName()+"!");
	}
	
	private void printCustomerOptions() {
		System.out.println("Please select from the following options:");
		System.out.println("Type \"view\" to view your balance(s)");
		System.out.println("Type \"withdraw\" to make a withdrawal from an account");
		System.out.println("Type \"deposit\" to make a deposit into an account");
		System.out.println("Type \"apply\" to apply for a new account");
		System.out.println("Type \"quit\" to quit the application");
	}

	private void printEmployeeGreeting(Employee employee) {
		System.out.println("Welcome "+employee.getFirstName()+" "+employee.getLastName()+"!");
		System.out.println("Please select from the following options:");
	}
	
	private void printEmployeeOptions() {
		System.out.println("Please select from the following options:");
		System.out.println("Type \"view\" to view user balances");
		System.out.println("Type \"applications\" to view, approve, or deny account applications.");
		System.out.println("Type \"log\" to view a log of all transactions");
		System.out.println("Type \"quit\" to quit the application");
	}
	
	private void registrationDisclaimer() {
		System.out.println("Thank you for your interest in registering with the Bank of Ben.\n");
		System.out.println("The following prompts will ask you for personal information necessary to create your "
				+ "application. If you do not intend to apply for an account after all your personal infromation "
				+ "is entered, your information will not be saved.\n");
		System.out.println("Please complete the account application process after these personal information prompts "
				+ "to complete the process. An employee will either approve or deny your account in a timely manner "
				+ "and get back to you via email. If you are denied, your personal information will not be saved.\n");
	}
	
	public User requestUserInfo(Scanner sc) throws BusinessException {
		User user = null;
		registrationDisclaimer();
		String email = UserInterface.requestEmail(sc);
		String username = UserInterface.requestUsername(sc);
		boolean loginRequested = false;
		while (bl.emailExists(email)) {
			System.out.println("The email "+email+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = pl.requestLoginUserInfo(sc);
				username = user.getUsername();
				break;
			} else {
				email = UserInterface.requestEmail(sc);
			}
		}
		while (bl.userExists(username) && !(loginRequested)) {
			System.out.println("The username "+username+" already exists. Would you like to login? (yes or y to confirm)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				try {
					user = bl.loginUser(username, sc);
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
	
	public User requestUserInfo(String username, String email, Scanner sc) throws BusinessException {
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
		
		System.out.println(user);
		
		return user;
	}
	
	public User requestLoginUserInfo(Scanner sc) throws BusinessException {
		String username = UserInterface.requestUsername(sc);
		return bl.loginUser(username, sc);
	}
	
	public Account requestCustomerSelectAccountForDeposit(Customer customer, Scanner sc) throws BusinessException {
		String accountInformation = bl.viewBalances(customer);
		List<Long> customerAccounts = bl.getAccountNumbersForCustomer(customer);
		String accountNumberString = null;
		long accountNumber = 0;
		while(accountNumber==0) {
			System.out.println("Please input the account number for your deposit.");
			System.out.println(accountInformation);
			accountNumberString = sc.nextLine();
			if (accountNumberString.matches("[0-9]{10}")) {
				try {
					accountNumber = Long.parseLong(accountNumberString);
					if (!customerAccounts.contains(accountNumber)) {
						System.out.println("The given account number does not correspond to one of your accounts. Please try again.");
						// if bad account number, reset to 0 and loop back.
						accountNumber = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid account number. Account numbers must be 10 digit numbers");
				}
			} else {
				System.out.println("Invalid account number. Account numbers must be 10 digit numbers");
			}
		}
		return bl.getAccount(accountNumber, Account.getRoutingNumber());
	}
	
	public double requestDepositAmount(double balance, Scanner sc) {
		double deposit = -1;
		while (deposit<0) {
			System.out.println("How much would you like to deposit?");
			String depositString = sc.nextLine();
			try {
				deposit = Long.parseLong(depositString);
				if (!ValidationTools.isValidMonetaryAmount(deposit)) {
					System.out.println("Invalid deposit amount. Deposit amount must be a positive number that has only"
							+"two digits after the decimal point. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid deposit amount. Deposit amount must be a positive number that has only"
						+"two digits after the decimal point. Please try again.");
			}
		}
		return deposit;
	}
	
	public Account requestCustomerSelectAccountForWithdrawal(Customer customer, Scanner sc) throws BusinessException {
		String accountInformation = bl.viewBalances(customer);
		List<Long> customerAccounts = bl.getAccountNumbersForCustomer(customer);
		String accountNumberString = null;
		long accountNumber = 0;
		while(accountNumber==0) {
			System.out.println("Please input the account number for your withdrawal.");
			System.out.println(accountInformation);
			accountNumberString = sc.nextLine();
			if (accountNumberString.matches("[0-9]{10}")) {
				try {
					accountNumber = Long.parseLong(accountNumberString);
					if (!customerAccounts.contains(accountNumber)) {
						System.out.println("The given account number does not correspond to one of your accounts. Please try again.");
						// if bad account number, reset to 0 and loop back.
						accountNumber = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid account number. Account numbers must be 10 digit numbers");
				}
			} else {
				System.out.println("Invalid account number. Account numbers must be 10 digit numbers");
			}
		}
		return bl.getAccount(accountNumber, Account.getRoutingNumber());
	}
	
	public double requestWithdrawalAmount(double balance, Scanner sc) {
		double withdrawal = -1;
		while (withdrawal<0) {
			System.out.println("How much would you like to withdraw?");
			String withdrawalString = sc.nextLine();
			try {
				withdrawal = Long.parseLong(withdrawalString);
				if (!ValidationTools.isValidMonetaryAmount(withdrawal)) {
					System.out.println("Invalid withdrawal amount. Withdrawal amount must be a positive number that has only"
							+"two digits after the decimal point. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid withdrawal amount. Withdrawal amount must be a positive number that has only"
						+"two digits after the decimal point. Please try again.");
			}
		}
		return withdrawal;
	}

}

package com.bankofben.presentation;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import com.bankofben.business.BusinessLayer;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Transfer;
import com.bankofben.models.User;

public class PresentationLayer {

	/*
	 * PUT ALL SYSOUT INFORMATION (AND SYSIN?) INTO THE LOG FILE
	 * 
	 * Presentation: 10 minute functionality presentation (plan for 7 minutes to account for questions; no powerpoint)
	 * to Vinay, Ben, and QC team.
	 * 
	 * REMEMBER TO INTRODUCE YOURSELF
	 * 
	 * No code/SQL in presentation. Show functionality only. Pretend audience has never seen code before.
	 * Imitate being a user (go through user stories). You can show the logging file to show you have been
	 * logging the events. People may ask about code in Q&A, you should be able to pull code quickly to 
	 * answer. Mention best practices you used.
	 * 
	 * should not use sysout, should only be log.info and log.error
	 * 		NEED TO EDIT TO MAKE THIS HAPPEN
	 * 
	 * Can have dummy data to speed up presentation, but should show all functionality
	 * 
	 * Project due on Monday, May 11, presentation on Wednesday, May 13
	 * 
	 * QUIZ WILL STILL BE ON MONDAY
	 * 		WILL COVER ONLY SQL, SEE QUESTION BANK!!!!
	 * 
	 */

	private static PresentationLayer pl = new PresentationLayer();
	private static BusinessLayer bl = new BusinessLayer();
	final static Logger loggy = Logger.getLogger(PresentationLayer.class);

	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
		loggy.info("This is a test! Use this method for all stdout.");
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
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					pl.printInvalidRegistrationMessage();
				}
				if (user!=null) {
					userResponseValidated = true;
				}
			} else if (response.equalsIgnoreCase("login")) {
				try {
					user = pl.requestLoginUserInfo(sc);
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					pl.printInvalidLoginMessage();
				}
				if (user!=null) {
					userResponseValidated = true;
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
					
				} else if (response.equalsIgnoreCase("transfers")) {
					boolean exitTransfers = false;
					boolean selectingOptions = false;
					while (!exitTransfers) {
						try {
							bl.viewTransfers();
						} catch (BusinessException e) {
							System.out.println(e.getMessage());
						}
						selectingOptions = true;
						String transferResponse = null;
						List<Transfer> transfers = bl.getTransfers();
						if (transfers.size()==0) {
							System.out.println("You have no pending transfers at this time.");
							while (selectingOptions) {
								pl.printTransferOptions();
								transferResponse = sc.nextLine();
								if (transferResponse.equals("post")) {
									// TODO Post transfer
									
								} else if (transferResponse.equals("view")) {
									selectingOptions = false;
								} else if (transferResponse.equals("back")) {
									selectingOptions = false;
									exitTransfers = true;
								} else {
									pl.printInvalidResponseMessage(transferResponse);
								}
							}
						} else {
							System.out.println("Your pending transfers are:");
							pl.printTransfers(transfers);
							while (selectingOptions) {
								pl.printTransferOptions();
								transferResponse = sc.nextLine();
								if (transferResponse.equalsIgnoreCase("accept")) {
									// TODO Accept transfer
									
								} else if (transferResponse.equals("post")) {
									// TODO Post transfer
									
								} else if (transferResponse.equals("reject")) {
									// TODO Reject transfer
									transfers = bl.getTransfers();
								} else if (transferResponse.equals("view")) {
									selectingOptions = false;
								} else if (transferResponse.equals("back")) {
									selectingOptions = false;
									exitTransfers = true;
								} else {
									pl.printInvalidResponseMessage(transferResponse);
								}
							}
						}
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
			} while (!(userResponseValidated));
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			pl.printEmployeeGreeting(employee);
			while (true) {
				pl.printEmployeeOptions();
				response = sc.nextLine();
				if (response.equalsIgnoreCase("view")) {
					try {
						System.out.println(bl.viewBalances());
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
				} else if (response.equalsIgnoreCase("applications")) {
					try {
						System.out.println(bl.viewPendingApplications());
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
				} else if (response.equalsIgnoreCase("log")) {
					// TODO: log logic
				} else if (response.equalsIgnoreCase("quit")) {
					pl.quit(sc);
				} else {
					pl.printInvalidResponseMessage(response);
				}
			}
		} else {
			try {
				bl.applyForAccount_returnNothing(user);
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
		System.out.println("Type \"transfers\" to view, manage, or post money transfers.");
		System.out.println("Type \"apply\" to apply for a new account");
		System.out.println("Type \"quit\" to quit the application");
	}

	private void printTransferOptions() {
		System.out.println("Please select from the following transfer options:");
		System.out.println("Type \"accept\" to accept money from another user's account.");
		System.out.println("Type \"post\" to post a transfer to an account you do or do not own");
		System.out.println("Type \"reject\" to reject money from another user's account.");
		System.out.println("Type \"view\" to view your pending transfers.");
		System.out.println("Type \"back\" to go back to the customer options menu.");
	}
	
	private void printTransferOptions_nonePending() {
		System.out.println("Please select from the following transfer options:");
		System.out.println("Type \"post\" to post a transfer to an account you do or do not own");
		System.out.println("Type \"view\" to view your pending transfers.");
		System.out.println("Type \"back\" to go back to the customer options menu.");
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
		long ssn = UserInterface.requestSsn(sc);
		boolean loginRequested = false;
		boolean goBack = false;
		while (bl.emailExists(email)) {
			System.out.println("The email "+email+" already exists. Would you like to login?\n"
					+ "(yes or y to confirm, back or b to go back)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				user = pl.requestLoginUserInfo(sc);
				break;
			} else if (response.equals("back") || response.equals("b")) {
				goBack = true;
				break;
			} else {
				email = UserInterface.requestEmail(sc);
			}
		}
		while (bl.userExists(username) && !(loginRequested)) {
			System.out.println("The username "+username+" already exists. Would you like to login?\n"
					+ "(yes or y to confirm, back or b to go back)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				try {
					user = bl.loginUser(username, sc);
				} catch (BusinessException e) {
					e.getMessage();
				}
				break;
			} else if (response.equals("back") || response.equals("b")) {
				goBack = true;
				break;
			} else {
				username = UserInterface.requestUsername(sc);
			}
		}
		while (bl.userExists(ssn) && !(loginRequested)){
			System.out.println("The ssn "+ssn+" already exists. Would you like to login?\n" 
					+ "(yes or y to confirm, back or b to go back)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				try {
					user = bl.loginUser(username, sc);
				} catch (BusinessException e) {
					e.getMessage();
				}
				break;
			} else if (response.equals("back") || response.equals("b")) {
				goBack = true;
				break;
			} else {
				username = UserInterface.requestUsername(sc);
			}
			
		}
		if (!loginRequested && !goBack) {
			/* 
			 * Try to register user.
			 */
			user = requestUserInfo(username, email, ssn, sc);
		}
		return user;
	}
	
	public User requestUserInfo(String username, String email, long ssn, Scanner sc) throws BusinessException {
		String firstName = UserInterface.requestFirstName(sc);
		String middleName = UserInterface.requestMiddleName(sc);
		String lastName = UserInterface.requestLastName(sc);
		String momsMaidenName = UserInterface.requestMomsMaidenName(sc);
		Date dob = UserInterface.requestDob(sc);
		long phoneNumber = UserInterface.requestPhoneNumber(sc);
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

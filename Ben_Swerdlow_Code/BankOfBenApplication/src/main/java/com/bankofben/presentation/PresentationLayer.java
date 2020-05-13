package com.bankofben.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import com.bankofben.business.BusinessLayer;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.models.Transfer;
import com.bankofben.models.User;
import com.bankofben.services.BankOfBenServices;

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
	 * 
	 * Can have dummy data to speed up presentation, but should show all functionality
	 * 
	 * Project due on Monday, May 11, presentation on Wednesday, May 13
	 * 
	 */

	private static PresentationLayer pl = new PresentationLayer();
	private static BusinessLayer bl = new BusinessLayer();
	final static Logger loggy = Logger.getLogger(PresentationLayer.class);

	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
//		loggy.info("This is a test! Use this method for all stdout.");
		User user = null;
		Scanner sc = new Scanner(System.in);
		String response=null;

//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		Date d=null;
//		try {
//			d = sdf.parse("10/05/1992");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			loggy.info(new Employee("Ben", "Eli", "Swerdlow", "Tobias", d, 564738291L, "ben@gmail.com", 3216621808L,
//							"benswerd", "P4ssw0rd!", "thingy", "stuff", "person", false));
//		} catch (BusinessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		System.exit(0);
		
		pl.printUserGreeting();
		boolean userResponseValidated = false;
		do {
			pl.printUserOptions();
			response = sc.nextLine();
			if (response.equalsIgnoreCase("register")) {
				try {
					user = pl.requestUserInfo(sc);
				} catch (BusinessException e) {
					loggy.info(e.getMessage()+"\nPlease try again.");
					pl.printInvalidRegistrationMessage();
				}
				if (user!=null) {
					boolean exists = false;
					try {
						exists = bl.userExists(user.getUsername());
						if (!exists) {
							double startingBalance = UserInterface.requestStartingBalance(sc);
							user = bl.applyForAccount_newUser(user, startingBalance);
							loggy.info("Thank you for applying for your account. Your application will be reviewed by a "
									+ "Bank of Ben employee in a timely manner.");
						}
					} catch (BusinessException e) {
						loggy.info(e.getMessage()+"\nPlease try again.");
					}
					userResponseValidated = true;
				}
			} else if (response.equalsIgnoreCase("login")) {
				try {
					user = pl.requestLoginUserInfo(sc);
					if(!bl.userExists(user.getUsername())) {
						double startingBalance = UserInterface.requestStartingBalance(sc);
						user = bl.applyForAccount_newUser(user, startingBalance);
						loggy.info("Thank you for applying for your account. Your application will be reviewed by a "
								+ "Bank of Ben employee in a timely manner.\nGoodbye!");
					}
				} catch (BusinessException e) {
					loggy.info(e.getMessage()+"\nPlease try again.");
//					pl.printInvalidLoginMessage();
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
			if (!customer.isApplicationPending()) {
				pl.printCustomerGreeting(customer);
				do {
					pl.printCustomerOptions();
					response = sc.nextLine();
					if (response.equalsIgnoreCase("view")) {
	
						String accountView = null;
						try {
							accountView = bl.viewBalances(customer);
						} catch (BusinessException e) {
							loggy.info(e.getMessage()+"\nPlease try again.");
						}
						loggy.info(accountView);
						
					} else if (response.equalsIgnoreCase("deposit")) {
	
						Account account = null;
						try {
							account = pl.requestCustomerSelectAccountForDeposit(customer, sc);
							double deposit = pl.requestDepositAmount(account.getBalance(), sc);
							account = bl.makeDeposit(deposit, account, customer);
							loggy.info("Deposit successful!\nNew Balance in account "+account.getAccountNumber()+": "+account.getBalance());
						} catch (BusinessException e) {
							loggy.info(e.getMessage()+"\nPlease try again.");
						}
						
					} else if (response.equalsIgnoreCase("withdraw")) {
						
						Account account = null;
						try {
							account = pl.requestCustomerSelectAccountForWithdrawal(customer, sc);
							double withdrawal = pl.requestWithdrawalAmount(account.getBalance(), sc);
							account = bl.makeWithdrawal(withdrawal, account, customer);
							loggy.info("Withdrawal successful!\nNew Balance in account "+account.getAccountNumber()+": "+account.getBalance());
						} catch (BusinessException e) {
							loggy.info(e.getMessage()+"\nPlease try again.");
						}
						
					} else if (response.equalsIgnoreCase("transfers")) {
						boolean exitTransfers = false;
						boolean selectingOptions = false;
						List<Transfer> transfers = null;
						while (!exitTransfers) {
							selectingOptions = true;
							String transferResponse = null;
							try {
								transfers = bl.getTransfers(customer);
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again.");
								break;
							}
							if (transfers.size()==0) {
								loggy.info("You have no pending transfers at this time.");
								while (selectingOptions) {
									pl.printTransferOptions_nonePending();
									transferResponse = sc.nextLine();
									if (transferResponse.equalsIgnoreCase("pay")) {
										Account otherAccount = UserInterface.requestOtherAccount(sc);
										Account myChosenAccount = UserInterface.requestMyChosenAccount(customer, sc);
										double amount = UserInterface.requestPaymentAmount(myChosenAccount, otherAccount, sc);
										if (Double.isNaN(amount)) {
											loggy.info("Payment canceled.");
										} else if (amount > myChosenAccount.getBalance()) {
											loggy.info("You cannot pay more than your current balance in this account. "
													+ "Please enter your information again.");
										} else {
											try {
												bl.postPayment(customer, myChosenAccount, otherAccount, amount);
											} catch (BusinessException e) {
												loggy.info(e.getMessage()+"\nPlease try again.");
											}
											selectingOptions = false;
										}
									} else if (transferResponse.equals("request")) {
										Account otherAccount = UserInterface.requestOtherAccount(sc);
										Account myChosenAccount = UserInterface.requestMyChosenAccount(customer, sc);
										double amount = UserInterface.requestRequestAmount(myChosenAccount, otherAccount, sc);
										if (Double.isNaN(amount)) {
											loggy.info("Request canceled.");
										} else {
											try {
												bl.postRequest(customer, myChosenAccount, otherAccount, amount);
											} catch (BusinessException e) {
												loggy.info(e.getMessage()+"\nPlease try again.");
											}
											selectingOptions = false;
										}
									} else if (transferResponse.equals("back")) {
										selectingOptions = false;
										exitTransfers = true;
									} else {
										pl.printInvalidResponseMessage(transferResponse);
									}
								}
							} else {
								loggy.info("Your pending transfers are:");
								try {
									pl.printPendingTransfers(transfers);
								} catch (BusinessException e) {
									loggy.info(e.getMessage()+"\nPlease try again.");
									selectingOptions = false;
								}
								while (selectingOptions) {
									pl.printTransferOptions();
									transferResponse = sc.nextLine();
									if (transferResponse.equalsIgnoreCase("pay")) {
										Account otherAccount = UserInterface.requestOtherAccount(sc);
										Account myChosenAccount = UserInterface.requestMyChosenAccount(customer, sc);
										double amount = UserInterface.requestPaymentAmount(myChosenAccount, otherAccount, sc);
										if (Double.isNaN(amount)) {
											loggy.info("Payment canceled.");
										} else if (amount > myChosenAccount.getBalance()) {
											loggy.info("You cannot pay more than your current balance in this account. "
													+ "Please enter your information again.");
										} else {
											try {
												bl.postPayment(customer, myChosenAccount, otherAccount, amount);
											} catch (BusinessException e) {
												loggy.info(e.getMessage()+"\nPlease try again.");
											}
											selectingOptions = false;
										}
									} else if (transferResponse.equals("request")) {
										Account otherAccount = UserInterface.requestOtherAccount(sc);
										Account myChosenAccount = UserInterface.requestMyChosenAccount(customer, sc);
										double amount = UserInterface.requestRequestAmount(myChosenAccount, otherAccount, sc);
										if (Double.isNaN(amount)) {
											loggy.info("Request canceled.");
										} else {
											try {
												bl.postRequest(customer, myChosenAccount, otherAccount, amount);
											} catch (BusinessException e) {
												loggy.info(e.getMessage()+"\nPlease try again.");
											}
											selectingOptions = false;
										}
									} else if (transferResponse.equalsIgnoreCase("accept")) {
										Transfer transfer=null;
										try {
											transfer = pl.chooseATransferToAccept(transfers, sc);
										} catch (BusinessException e) {
											loggy.info(e.getMessage()+"\nPlease try again.");
											selectingOptions = false;
											exitTransfers = true;
										}
										if (transfer!=null) {
											try{
												Account postTransferAccount = bl.acceptTransfer(transfer, customer);
												loggy.info("Tranfer accepted.\n"
														+ "Balance in account "+postTransferAccount.getAccountNumber()+": "+postTransferAccount.getBalance());
											} catch (BusinessException e) {
												loggy.info(e.getMessage()+"\nPlease try again.");
												selectingOptions = false;
											}
										}
									} else if (transferResponse.equalsIgnoreCase("halt")) {
										Transfer transfer = null;
										try {
											transfer = pl.chooseATransferToHalt(transfers, sc);
										} catch (BusinessException e) {
											loggy.info(e.getMessage()+"\nPlease try again.");
											selectingOptions = false;
											exitTransfers = true;
										}
										if (transfer!=null) {
											try{
												bl.haltTransfer(transfer, customer);
												loggy.info("Tranfer halted.\n");
											} catch (BusinessException e) {
												loggy.info(e.getMessage()+"\nPlease try again.");
												selectingOptions = false;
											}
										}
										
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
						double startingBalance=0;
						try {
							startingBalance = UserInterface.requestStartingBalance(sc);
						} catch (BusinessException e) {
							loggy.info(e.getMessage()+"\nPlease try again.");
						}
						try {
							customer = bl.applyForAccount(customer, startingBalance);
							loggy.info("Thank you for applying for your account. Your application will be reviewed by a "
									+ "Bank of Ben employee in a timely manner.");
						} catch (BusinessException e) {
							loggy.info(e.getMessage()+"\nPlease try again.");
						}
						
					} else if (response.equalsIgnoreCase("quit")) {
						pl.quit(sc);
						//userResponseValidated=true;
					} else {
						pl.printInvalidResponseMessage(response);
					}
				} while (!(userResponseValidated));
			} else {
				loggy.info("Thank you for following up with the Bank of Ben. Unfortunately your account is still under review. Please check back later.");
			}
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			pl.printEmployeeGreeting(employee);
			while (!userResponseValidated) {
				pl.printEmployeeOptions();
				response = sc.nextLine();
				if (response.equalsIgnoreCase("view")) {
					boolean choosingViews = true;
					String viewResponse = null;
					while (choosingViews) {
						pl.printEmployeeBalanceViewingOptions();
						viewResponse = sc.nextLine();
						if (viewResponse.equalsIgnoreCase("all")) {
							try {
								loggy.info(bl.viewBalances());
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again.");
							}
						} else if (viewResponse.equalsIgnoreCase("customer")) {
							String username = null;
							loggy.info("Please type the username of the customer whose accounts you would like to view");
							username = UserInterface.requestUsername(sc);
							try {
								loggy.info(bl.viewBalances(username));
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again.");
							}
						} else if (viewResponse.equalsIgnoreCase("back")) {
							choosingViews = false;
						} else {
							pl.printInvalidResponseMessage(viewResponse);
						}
					}
				} else if (response.equalsIgnoreCase("applications")) {
					String applicationMenuOption = null;
					boolean workingOnApplications = true;
					while (workingOnApplications) {
						loggy.info("Type \"new\" to view all new customer account applications.\n"
								+ "Type \"all\" to view account applications for all customers.");
						applicationMenuOption = sc.nextLine();
						if (applicationMenuOption.equalsIgnoreCase("new")) {
							try {
								HashMap<Customer, List<Account>> newCustomerAccountsMap = bl.makeNewCustomerPendingAccountsMap();
								loggy.info(pl.viewCustomerAccountMap(newCustomerAccountsMap));
								Account a = pl.requestApplicationAccountByAccountNumber(sc);
								if (a==null) break;
								boolean selectedAcceptOrReject = false;
								String responseApproveOrReject = null;
								while (!selectedAcceptOrReject) {
									pl.printApproveOrRejectApplicationNotice(a);
									responseApproveOrReject = sc.nextLine();
									if (responseApproveOrReject.equalsIgnoreCase("approve")) {
										bl.approveNewCustomerAccountApplication(a);
										loggy.info("You have approved the application for account number "+a.getAccountNumber()
										+" for new customer "+a.getCustomerId()+".");
										selectedAcceptOrReject = true;
									} else if(responseApproveOrReject.equalsIgnoreCase("reject")) {
										bl.rejectNewCustomerAccountApplication(a);
										loggy.info("You have rejected the application for account number "+a.getAccountNumber()
											+" for customer "+a.getCustomerId()+". This customer has been removed from our records.");
										selectedAcceptOrReject = true;
									} else {
										pl.printInvalidResponseMessage(responseApproveOrReject);
									}
								}
								workingOnApplications=false;
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again later.");
								workingOnApplications=false;
							}
						} else if (applicationMenuOption.equalsIgnoreCase("all")) {
							try {
								HashMap<Customer, List<Account>> customerPendingAccountsMap = bl.makePendingAccountsMap();
								loggy.info(pl.viewCustomerAccountMap(customerPendingAccountsMap));
								Account a = pl.requestApplicationAccountByAccountNumber(sc);
								if (a==null) break;
								boolean selectedAcceptOrReject = false;
								String responseApproveOrReject = null;
								while (!selectedAcceptOrReject) {
									pl.printApproveOrRejectApplicationNotice(a);
									responseApproveOrReject = sc.nextLine();
									if (responseApproveOrReject.equalsIgnoreCase("approve")) {
										bl.approveExistingCustomerAccountApplication(a);
										loggy.info("You have approved the application for account number "+a.getAccountNumber()
										+" for customer "+a.getCustomerId()+".");
										selectedAcceptOrReject = true;
									} else if(responseApproveOrReject.equalsIgnoreCase("reject")) {
										bl.rejectExistingCustomerAccountApplication(a);
										loggy.info("You have rejected the application for account number "+a.getAccountNumber()
											+" for customer "+a.getCustomerId()+". If this customer was not an already existing customer,"
											+ " their information has been removed from our records.");
										selectedAcceptOrReject = true;
									} else {
										pl.printInvalidResponseMessage(responseApproveOrReject);
									}
								}
								workingOnApplications=false;
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again later.");
								workingOnApplications=false;
							}
						} else {
							pl.printInvalidResponseMessage(applicationMenuOption);
						}
					}
				} else if (response.equalsIgnoreCase("log")) {
					boolean choosingLogViews = true;
					String viewLogResponse = null;
					while (choosingLogViews) {
						pl.printEmployeeLogViewOptions();
						viewLogResponse = sc.nextLine();
						if (viewLogResponse.equalsIgnoreCase("all")) {
							try {
								loggy.info(bl.viewTransactions());
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again.");
							}
						} else if (viewLogResponse.equalsIgnoreCase("account")) {
							long accountNumber = UserInterface.requestAccountNumber(sc);
							try {
								loggy.info(bl.viewTransactions(accountNumber));
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again.");
							}
						} else if (viewLogResponse.matches("^[0-9]+$")) {
							try {
								int latestNumber = Integer.parseInt(viewLogResponse);
								loggy.info(bl.viewTransactionsUpTo(latestNumber));
							} catch (NumberFormatException e) {
								loggy.info("The number you entered is invalid.\nPlease try again.");
							} catch (BusinessException e) {
								loggy.info(e.getMessage()+"\nPlease try again.");
							}
						} else if (viewLogResponse.equalsIgnoreCase("back")) {
							choosingLogViews = false;
						} else {
							pl.printInvalidResponseMessage(viewLogResponse);
						}
					}
				} else if (response.equalsIgnoreCase("quit")) {
					pl.quit(sc);
				} else {
					pl.printInvalidResponseMessage(response);
				}
			}
		} else {
			loggy.info("The Bank of Ben has no further functionality for this user at this time. Please check back again soon for updates!");
		}
		
	}

	private Account requestApplicationAccountByAccountNumber(Scanner sc) {
		String accountString = null;
		boolean selectedAccount = false;
		Account account = null;

		while(!selectedAccount) {
			loggy.info("Please type in the account number for the application you would like to accept or reject. Please type \"cancel\" to cancel.");
			accountString = sc.nextLine();
			if (accountString.equalsIgnoreCase("cancel")) {
				break;
			}
			while (!accountString.matches("[0-9]{10}")) {
				loggy.info("Your entry "+accountString+" is not a valid account number. Account numbers should be positive 10-digit numbers.\n"
						+ "Please try again.");
				loggy.info("Please type in the account number for the application you would like to accept or reject.");
				accountString = sc.nextLine();
			}
			try {
				account = bl.getAccount(bl.validateAccountNumber(accountString), Account.getRoutingNumber());
				selectedAccount = true;
			} catch (BusinessException e) {
				loggy.info(e.getMessage()+"\nPlease try again.");
			}
		}
		return account;
	}

	public void printInvalidResponseMessage(String response) {
		loggy.info("I am sorry. Your request \""+response+"\" is not a valid option.");
		loggy.info("Please try again.\n");
	}

	public void printInvalidRegistrationMessage() {
		loggy.info("Invalid registration. Please try again.");
	}

	public void printInvalidLoginMessage() {
		loggy.info("Invalid login. Please try again.");
	}

	public void quit(Scanner sc) {
		loggy.info("Are you sure you would like to quit the Bank of Ben Application?");
		loggy.info("Type \"quit\" again to confirm. Enter anything else to return to your options.");
		String response = sc.nextLine();
		if (response.equalsIgnoreCase("quit")) {
			loggy.info("Thank you for using the Bank of Ben Application.");
			System.exit(0);
		}
	}

	public void printUserGreeting() {
		loggy.info("Welcome to the Bank of Ben!\n");
	}
	
	public void printUserOptions() {
		loggy.info("Please select from the following options:");
		loggy.info("Type \"register\" to register a new user");
		loggy.info("Type \"login\" to log in.");
		loggy.info("Type \"quit\" to quit the application");
	}

	public void printCustomerGreeting(Customer customer) {
		loggy.info("Welcome "+customer.getFirstName()+" "+customer.getLastName()+"!");
	}
	
	public void printCustomerOptions() {
		loggy.info("Please select from the following options:");
		loggy.info("Type \"view\" to view your balance(s)");
		loggy.info("Type \"withdraw\" to make a withdrawal from an account");
		loggy.info("Type \"deposit\" to make a deposit into an account");
		loggy.info("Type \"transfers\" to view, manage, or post money transfers.");
		loggy.info("Type \"apply\" to apply for a new account");
		loggy.info("Type \"quit\" to quit the application");
	}

	public void printTransferOptions() {
		loggy.info("Please select from the following transfer options:");
		loggy.info("Type \"pay\" to post a money transfer paying money from an account you own "
				+ "into an account you do or do not own");
		loggy.info("Type \"request\" to request a money transfer from an account you do not own "
				+ "into an account you do own.");
		loggy.info("Type \"accept\" to accept a money transfer from another user's account.");
		loggy.info("Type \"halt\" to reject a money transfer from or resciend a money transfer to another user's account.");
		loggy.info("Type \"view\" to view your pending transfers.");
		loggy.info("Type \"back\" to go back to the customer options menu.");
	}
	
	public void printTransferOptions_nonePending() {
		loggy.info("Please select from the following transfer options:");
		loggy.info("Type \"pay\" to post a money transfer paying money from an account you own "
				+ "into an account you do or do not own");
		loggy.info("Type \"request\" to request a money transfer from an account you do not own "
				+ "into an account you do own.");
		loggy.info("Type \"back\" to go back to the customer options menu.");
	}
	
	public void printEmployeeGreeting(Employee employee) {
		loggy.info("Welcome "+employee.getFirstName()+" "+employee.getLastName()+"!");
	}
	
	public void printEmployeeOptions() {
		loggy.info("Please select from the following options:");
		loggy.info("Type \"view\" to view user balances");
		loggy.info("Type \"applications\" to view, approve, or deny account applications.");
		loggy.info("Type \"log\" to view a log of all transactions");
		loggy.info("Type \"quit\" to quit the application");
	}
	
	public void registrationDisclaimer() {
		loggy.info("Thank you for your interest in registering with the Bank of Ben.\n");
		loggy.info("The following prompts will ask you for personal information necessary to create your "
				+ "application. If you do not intend to apply for an account after all your personal infromation "
				+ "is entered, your information will not be saved.\n");
		loggy.info("Please complete the account application process after these personal information prompts "
				+ "to complete the process. An employee will either approve or deny your account in a timely manner "
				+ "and get back to you via email. If you are denied, your personal information will not be saved.\n");
	}

	public void printEmployeeBalanceViewingOptions() {
		loggy.info("Type \"all\" if you would like to view all balances.");
		loggy.info("Type \"customer\" if you would like to view all balances for a specific customer.");
		loggy.info("Type \"back\" if you would like to go back to the other employee options.");
	}

	public void printEmployeeLogViewOptions() {
		loggy.info("Type \"all\" if you would like to view the entire log of bank transactions.");
		loggy.info("Type \"account\" if you would like to view the entire log of transactions related to an account number.");
		loggy.info("Type any whole number to view that number of most recent log entries.");
		loggy.info("Type \"back\" if you would like to go back to the other employee options.");
	}

	public void printApproveOrRejectApplicationNotice(Account a) {
		loggy.info("Please type \"approve\" to approve the account application.\n"
				+ "\tApproving will permanently store the information concerning customer "+a.getCustomerId()
				+" and create their account with account number "+a.getAccountNumber()+".\n"
				+ "Please type \"reject\" to reject the account application.\n"
				+ "\tRejecting will permanently delete the account with account number "+a.getAccountNumber()
				+ " and, if the user is not an already existing customer, will remove their data from the database.");
	}
	
	public User requestUserInfo(Scanner sc) throws BusinessException {
		User user = null;
		registrationDisclaimer();
		String email = UserInterface.requestEmail(sc);
		boolean loginRequested = false;
		boolean goBack = false;
		while (bl.emailExists(email)) {
			loggy.info("The email "+email+" already exists. Would you like to login?\n"
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
		String username=null;
		if (!loginRequested && !goBack) {
			username = UserInterface.requestUsername(sc);
		}
		while (bl.userExists(username) && !(loginRequested) && !goBack) {
			loggy.info("The username "+username+" already exists. Would you like to login?\n"
					+ "(yes or y to confirm, back or b to go back)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				try {
					user = pl.loginUser(username, sc);
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
		long ssn=0;
		if (!loginRequested && !goBack) {
			ssn = UserInterface.requestSsn(sc);
		}
//		long ssn = UserInterface.requestSsn(sc);
		while (bl.userExists(ssn) && !(loginRequested) && !goBack){
			loggy.info("The ssn "+ssn+" already exists. Would you like to login?\n" 
					+ "(yes or y to confirm, back or b to go back)");
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
				loginRequested = true;
				try {
					user = pl.loginUser(username, sc);
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
		
		loggy.info("Thank you for your information.");
		
//		loggy.info(user);
		
		return user;
	}
	
	public User requestLoginUserInfo(Scanner sc) throws BusinessException {
		User user = null;
		String username = null;
		String response = null;
		boolean loggedIn = false;
		while (!loggedIn) {
			username = UserInterface.requestUsername(sc);
			if (bl.userExists(username)) {
				user = pl.loginUser(username, sc);
				loggedIn = true;
			} else {
				loggy.info("A user with username "+username+" does not exist. Would you like to register instead? (yes or y to confirm)");
				response = sc.nextLine();
				if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
					user = pl.requestUserInfo(sc);
					loggedIn = true;
				}
			}
		}
		return user;
	}
	
	public User loginUser(String username, Scanner sc) throws BusinessException {
		String password = null;
		int loginAttempts = 0;
		User user = null;
		for (loginAttempts=0; loginAttempts<4; loginAttempts++) {
			password = UserInterface.requestPassword(sc);
			try {
				user = bl.loginUser(username, password);
				break;
			} catch (BusinessException e) {
				if (loginAttempts < 4) {
					loggy.info(e.getMessage());
				} else {
					throw e;
				}
			}
		}
//		loggy.info(user);
		if (loginAttempts >= 4) {
			throw new BusinessException("Limit of password attempts exceeded.");
		} else if (user==null) {
			throw new BusinessException("Could not log in user with these credentials.");
		}
		return user;
	}
	
	public Account requestCustomerSelectAccountForDeposit(Customer customer, Scanner sc) throws BusinessException {
		String accountInformation = bl.viewBalances(customer);
		List<Long> customerAccounts = bl.getAccountNumbersForCustomer(customer);
		String accountNumberString = null;
		long accountNumber = 0;
		while(accountNumber==0) {
			loggy.info("Please input the account number for your deposit.");
			loggy.info(accountInformation);
			accountNumberString = sc.nextLine();
			if (accountNumberString.matches("[0-9]{10}")) {
				try {
					accountNumber = Long.parseLong(accountNumberString);
					if (!customerAccounts.contains(accountNumber)) {
						loggy.info("The given account number does not correspond to one of your accounts. Please try again.");
						// if bad account number, reset to 0 and loop back.
						accountNumber = 0;
					}
				} catch (NumberFormatException e) {
					loggy.info("Invalid account number. Account numbers must be 10 digit numbers");
				}
			} else {
				loggy.info("Invalid account number. Account numbers must be 10 digit numbers");
			}
		}
		return bl.getAccount(accountNumber, Account.getRoutingNumber());
	}
	
	public double requestDepositAmount(double balance, Scanner sc) {
		double deposit = -1;
		while (deposit<0) {
			loggy.info("How much would you like to deposit?");
			String depositString = sc.nextLine();
			try {
				deposit = Double.parseDouble(depositString);
				if (deposit < 0) {
					loggy.info("Invalid deposit amount. Deposit amount must be a positive number that has only"
							+"two digits after the decimal point. Please try again.");
				}
				if (!ValidationTools.isValidMonetaryAmount(deposit)) {
					loggy.info("Invalid deposit amount. Deposit amount must be a positive number that has only"
							+"two digits after the decimal point. Please try again.");
				}
			} catch (NumberFormatException e) {
				loggy.info("Invalid deposit amount. Deposit amount must be a positive number that has only"
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
			loggy.info("Please input the account number for your withdrawal.");
			loggy.info(accountInformation);
			accountNumberString = sc.nextLine();
			if (accountNumberString.matches("[0-9]{10}")) {
				try {
					accountNumber = Long.parseLong(accountNumberString);
					if (!customerAccounts.contains(accountNumber)) {
						loggy.info("The given account number does not correspond to one of your accounts. Please try again.");
						// if bad account number, reset to 0 and loop back.
						accountNumber = 0;
					}
				} catch (NumberFormatException e) {
					loggy.info("Invalid account number. Account numbers must be 10 digit numbers");
				}
			} else {
				loggy.info("Invalid account number. Account numbers must be 10 digit numbers");
			}
		}
		return bl.getAccount(accountNumber, Account.getRoutingNumber());
	}
	
	public double requestWithdrawalAmount(double balance, Scanner sc) {
		double withdrawal = -1;
		while (withdrawal<0) {
			loggy.info("How much would you like to withdraw?");
			String withdrawalString = sc.nextLine();
			try {
				withdrawal = Double.parseDouble(withdrawalString);
				if (withdrawal < 0) {
					loggy.info("Invalid withdrawal amount. Withdrawal amount must be a positive number that has only"
							+"two digits after the decimal point. Please try again.");
				}
				if (!ValidationTools.isValidMonetaryAmount(withdrawal)) {
					loggy.info("Invalid withdrawal amount. Withdrawal amount must be a positive number that has only"
							+"two digits after the decimal point. Please try again.");
				}
			} catch (NumberFormatException e) {
				loggy.info("Invalid withdrawal amount. Withdrawal amount must be a positive number that has only"
						+"two digits after the decimal point. Please try again.");
			}
		}
		return withdrawal;
	}

	private Transfer chooseATransferToAccept(List<Transfer> transfers, Scanner sc) throws BusinessException {
		Transfer transfer = null;
		boolean acceptingTransfers = true;
		String transferEntryString = null;
		int transferIndex = -1;
		while (acceptingTransfers) {
			pl.printPendingTransfersEnumerated(transfers);
			loggy.info("Please enter the number that corresponds to the transfer you would like "
					+ "to accept. Enter \"back\" to go back and see other transfer options.");
			transferEntryString = sc.nextLine();
			
			if (transferEntryString.matches("^[0-9]*$")) {
				try {
					// Not zero-indexed for user
					transferIndex = Integer.parseInt(transferEntryString)-1;
				} catch (NumberFormatException e) {
					pl.printInvalidResponseMessage(transferEntryString);
				}
			} else if (transferEntryString.equalsIgnoreCase("back")) {
				acceptingTransfers=false;
			} else {
				pl.printInvalidResponseMessage(transferEntryString);
			}
			
			if (transferIndex > -1) {
				if (transferIndex < transfers.size()) {
					loggy.info("You have chosen transfer number: "+transferEntryString);
					loggy.info("Type \"accept\" to accept this transfer. Type \"back\" to go "
							+ "back to the main transfer menu. Enter anything else to select a "
							+ "different transfer to accept.");
					transferEntryString = sc.nextLine();
					if (transferEntryString.equalsIgnoreCase("accept")) {
						List<Transfer> pendingTransfers = new ArrayList<>();
						for (Transfer t : transfers) {
							if (t.isPending()) {
								pendingTransfers.add(t);
							}
						}
						transfer = pendingTransfers.get(transferIndex);
						break;
					} else if (transferEntryString.equalsIgnoreCase("back")) {
						acceptingTransfers=false;
					}
				}
			}
		}
		return transfer;
	}

	private Transfer chooseATransferToHalt(List<Transfer> transfers, Scanner sc) throws BusinessException {
		Transfer transfer = null;
		boolean haltingTransfers = true;
		String transferEntryString = null;
		int transferIndex = -1;
		while (haltingTransfers) {
			pl.printPendingTransfersEnumerated(transfers);
			loggy.info("Please enter the number that corresponds to the transfer you would like "
					+ "to halt. Enter \"back\" to go back and see other transfer options.");
			transferEntryString = sc.nextLine();
			
			if (transferEntryString.matches("^[0-9]*$")) {
				try {
					// Not zero-indexed for user
					transferIndex = Integer.parseInt(transferEntryString)-1;
				} catch (NumberFormatException e) {
					pl.printInvalidResponseMessage(transferEntryString);
				}
			} else if (transferEntryString.equalsIgnoreCase("back")) {
				haltingTransfers=false;
			} else {
				pl.printInvalidResponseMessage(transferEntryString);
			}
			
			if (transferIndex > -1) {
				if (transferIndex < transfers.size()) {
					loggy.info("You have chosen transfer number:"+transferEntryString);
					loggy.info("Type \"halt\" to reject or rescind this transfer. Type \"back\" to go "
							+ "back to the main transfer menu. Enter anything else to select a "
							+ "different transfer to halt.");
					transferEntryString = sc.nextLine();
					if (transferEntryString.equalsIgnoreCase("halt")) {
						List<Transfer> pendingTransfers = new ArrayList<>();
						for (Transfer t : transfers) {
							if (t.isPending()) {
								pendingTransfers.add(t);
							}
						}
						transfer = pendingTransfers.get(transferIndex);
						break;
					} else if (transferEntryString.equalsIgnoreCase("back")) {
						haltingTransfers=false;
					}
				}
			}
		}
		return transfer;
	}

	public void printPendingTransfersEnumerated(List<Transfer> transfers) throws BusinessException {
		BankOfBenServices dbs = new BankOfBenServices();
		
		List<Payment> payments = new ArrayList<>();
		List<Request> requests = new ArrayList<>();
		
		Payment pa = null;
		Request re = null;
		for (Transfer t : transfers) {
			if (t instanceof Payment) {
				pa = (Payment) t;
				if (pa.isPending()) {
					payments.add(pa);
				}
			} else if (t instanceof Request) {
				re = (Request) t;
				if (re.isPending()) {
					requests.add(re);
				}
			} else {
				BusinessException b = new BusinessException("Unrecognized transfer type "+transfers.getClass()+". Please contact a Bank of Ben"
						+ "employee to remedy this issue.");
				loggy.error(b);
				throw b;
			}
		}
		
		int counter = 0;
		Customer customer = null;
		
		String[] paymentHeadingNames = {"Payment ID", "Initiator", "Receiving Account #", "Amount", "Status"};
		loggy.info("PENDING PAYMENTS");
		StringBuilder sb = new StringBuilder();
		sb.append("No.");
		for (int i=0; i<paymentHeadingNames.length; i++) {
			sb.append("\t|\t"+paymentHeadingNames[i]);
		}
		sb.append("\t|");
		loggy.info(sb);
		for (Payment p : payments) {
			sb = new StringBuilder();
			counter++;
			sb.append(counter+"\t|\t");
			sb.append(p.getId()+"\t|\t");
			customer = dbs.getCustomerById(p.getInitUserId());
			sb.append(customer.getLastName()+", "+customer.getFirstName()+"\t|\t");
			sb.append(p.getReceivingAccountNumber()+"\t|\t");
			sb.append(p.getAmount()+"\t|\t");
			if (p.isPending()) {
				sb.append("Pending\t|");
			} else {
				sb.append("Accepted\t|");
			}
			loggy.info(sb);
		}
		
		String[] requestHeadingNames = {"Request ID", "Initiator", "Source Account #", "Amount", "Status"};
		loggy.info("PENDING REQUESTS");
		sb = new StringBuilder();
		sb.append("No.");
		for (int i=0; i<requestHeadingNames.length; i++) {
			sb.append("\t|\t"+requestHeadingNames[i]);
		}
		sb.append("\t|");
		loggy.info(sb);
		for (Request r : requests) {
			sb = new StringBuilder();
			counter++;
			sb.append(counter+"\t|\t");
			sb.append(r.getId()+"\t|\t");
			customer = dbs.getCustomerById(r.getInitUserId());
			sb.append(customer.getLastName()+", "+customer.getFirstName()+"\t|\t");
			sb.append(r.getSoughtAccountNumber()+"\t|\t");
			sb.append(r.getAmount()+"\t|\t");
			if (r.isPending()) {
				sb.append("Pending\t|");
			} else {
				sb.append("Accepted\t|");
			}
			loggy.info(sb);
		}
	}

	private void printPendingTransfers(List<Transfer> transfers) throws BusinessException {
		BankOfBenServices dbs = new BankOfBenServices();
		
		List<Payment> payments = new ArrayList<>();
		List<Request> requests = new ArrayList<>();
		
		Payment pa = null;
		Request re = null;
		for (Transfer t : transfers) {
			if (t instanceof Payment) {
				pa = (Payment) t;
				if (pa.isPending()) {
					payments.add(pa);
				}
			} else if (t instanceof Request) {
				re = (Request) t;
				if (re.isPending()) {
					requests.add(re);
				}
			} else {
				BusinessException b = new BusinessException("Unrecognized transfer type "+transfers.getClass()+". Please contact a Bank of Ben"
						+ "employee to remedy this issue.");
				loggy.error(b);
				throw b;
			}
		}
		
		Customer customer = null;
		
		String[] paymentHeadingNames = {"Payment ID", "Initiator", "Rec. Acct. #", "Amount", "Status"};
		loggy.info("PENDING PAYMENTS\n");
		StringBuilder sb = new StringBuilder();
		sb.append(paymentHeadingNames[0]);
		for (int i=1; i<paymentHeadingNames.length; i++) {
			sb.append("\t|\t"+paymentHeadingNames[i]);
		}
		sb.append("\t|");
		loggy.info(sb);
		for (Payment p : payments) {
			sb = new StringBuilder();
			sb.append(p.getId()+"\t|\t");
			customer = dbs.getCustomerById(p.getInitUserId());
			sb.append(customer.getLastName()+", "+customer.getFirstName()+"\t|\t");
			sb.append(p.getReceivingAccountNumber()+"\t|\t");
			sb.append(p.getAmount()+"\t|\t");
			if (p.isPending()) {
				sb.append("Pending\t|");
			} else {
				sb.append("Accepted\t|");
			}
			loggy.info(sb);
		}
		loggy.info("\n");
		String[] requestHeadingNames = {"Request ID", "Initiator", "Src. Acct. #", "Amount", "Status"};
		loggy.info("PENDING REQUESTS\n");
		sb = new StringBuilder();
		sb.append(requestHeadingNames[0]);
		for (int i=1; i<requestHeadingNames.length; i++) {
			sb.append("\t|\t"+requestHeadingNames[i]);
		}
		sb.append("\t|");
		loggy.info(sb);
		for (Request r : requests) {
			sb = new StringBuilder();
			sb.append(r.getId()+"\t|\t");
			customer = dbs.getCustomerById(r.getInitUserId());
			sb.append(customer.getLastName()+", "+customer.getFirstName()+"\t|\t");
			sb.append(r.getSoughtAccountNumber()+"\t|\t");
			sb.append(r.getAmount()+"\t|\t");
			if (r.isPending()) {
				sb.append("Pending\t|");
			} else {
				sb.append("Accepted\t|");
			}
			loggy.info(sb);
		}
	}

	public String viewCustomerAccountMap(HashMap<Customer, List<Account>> accountsMap) throws BusinessException {
		StringBuilder outputBuilder = new StringBuilder();
//		// We'll start by organizing the columns
		Customer c = null;
		
		
		
		
		for (Entry<Customer, List<Account>> entry : accountsMap.entrySet()) {
			c = entry.getKey();
			if (c.isApplicationPending()) {
				outputBuilder.append("New Customer Application Pending\n");
			} else {
				outputBuilder.append("Existing Customer\n");
			}
			outputBuilder.append(c.getLastName()+", "+c.getFirstName()+"; "+c.getUsername()+"; "+c.getEmail()+"\n");
			for (Account a : entry.getValue()) {
				outputBuilder.append("Pending Act. #: "+a.getAccountNumber()+"\nStarting Balance: "+a.getBalance()+"\n");
			}
			outputBuilder.append("\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}

}

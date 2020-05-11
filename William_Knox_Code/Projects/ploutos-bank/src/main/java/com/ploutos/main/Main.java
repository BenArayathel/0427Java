package com.ploutos.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.ploutos.exception.BusinessException;
import com.ploutos.model.Account;
import com.ploutos.model.Login;
import com.ploutos.service.PloutosService;
import com.ploutos.service.PloutosServiceImpl;

public class Main { //SPAGHET
	final static Logger L = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		L.setLevel(Level.ALL);
		Scanner s = new Scanner(System.in);
		PloutosService psi = new PloutosServiceImpl();
		int choice = 0;
		do {
			L.info("Welcome to Ploutos Bank. How can we help you?\n"
					+ "Please enter your choice as a number:\n"
					+ "+-------------------------------------------+\n"
					+ "1. Customer Login Screen\n"
					+ "2. Customer Sign-up\n"
					+ "3. Employee Login Screen\n"
					+ "4. Quit");
			try {
				choice = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				L.warn("Customer tried to enter a non-number in the menu.");
				L.info("Invalid input! Please enter a number.");
			}
			switch (choice) {
			case 1: // Customer Login
				boolean inCust = false;
				do {
					L.info("Please enter your username: ");
					String username = s.nextLine();
					L.info("Please enter your password: ");
					String password = s.nextLine();
					Login login = null;
					try {
						login = psi.logIn(username, password);
					} catch (BusinessException e) {
						L.info(e.getMessage());
					}
					inCust = (login != null);
					if (inCust) {
						int choice2 = 0;
						do {
							L.info("\nWelcome, valued customer.\n"
									+ "What would you like to do today?\n"
									+ "+------------------------------+\n"
									+ "1. View my Bank Accounts\n"
									+ "2. Create new Bank Account\n"
									+ "3. Log out");
							try {
								choice2 = Integer.parseInt(s.nextLine());
							} catch (NumberFormatException e) {
								L.warn("Customer tried to enter a non-number in the menu.");
								L.info("Invalid input! Please enter a number.");
							}
							switch (choice2) {
							case 1: // View my bank accounts
								List<Account> accList = new ArrayList<Account>();
								int choice3 = 0;
								do {
									L.info("\nHere are your bank accounts:\n"
											+ "+--------------------------+");
									try {
										accList = psi.accountListByLogin(login);
										L.info(psi.accountListString(accList));
									} catch (BusinessException e1) {
										L.warn(e1.getStackTrace() + " " + e1.getMessage());
										L.info(e1.getMessage());
									}
									L.info("Please select the listing you would like to access."
											+ " To go back, enter 0.");
									try {
										choice3 = Integer.parseInt(s.nextLine()) - 1;
										accessAccount(accList.get(choice3), login, s, psi); 
									} catch (NumberFormatException e) {
										L.warn("Customer tried to enter a non-number in the menu.");
										L.info("Invalid input! Please enter a number.");
									} catch (IndexOutOfBoundsException e) {
										L.info("That is not a valid choice, please try again.");
									}
								} while (choice3 != -1);
								break;
							case 2: // Create a new bank account
								L.info("What would you like your starting balance to be?\nEnter a negative number to cancel this action.");
								try {
									psi.makeAccount(login, Integer.parseInt(s.nextLine()));
								} catch (NumberFormatException e) {
									L.warn("Customer tried to enter a non-number in the menu.");
									L.info("Invalid input! Please enter a number.");
								} catch (BusinessException e) {
									L.info(e.getMessage());
								}
								break;
							case 3: // Log out
								L.info("Have a good day, and thank you for choosing Ploutos Bank.");
								break;
							}
							if (choice2 != 3)
								choice2 = 0;
						} while (choice2 != 3);
					} else {
						int choice2 = 0;
						do {
							L.info("Sorry, but that is not a valid login. Please select one of the following:\n"
								+ "1. Go back to main menu\n"
								+ "2. Retry login");
							try {
								choice2 = Integer.parseInt(s.nextLine());
							} catch (NumberFormatException e) {
								L.warn("Customer tried to enter a non-number in the menu.");
								L.info("Invalid input! Please enter a number.");
							}
							if (choice2 == 1) {
								inCust = true;
								L.info("Returning to main menu.");
							} else {
								L.info("Retrying login.");
							}
						} while (choice2 != 1 && choice2 != 2);
					}
				} while (!inCust);
				break;
			case 2: // Customer Sign-up
				String username = "";
				do {
					L.info("Please enter your desired username. Usernames must only consist of lowercase letters and numbers.\n"
							+ "Usernames may only be up to 32 characters long. Enter nothing to cancel and exit this prompt.");
					username = s.nextLine();
				} while (!psi.isValidUsername(username) && !username.equals(""));
				if (!username.equals("")) {
					L.info("Please enter your desired password: ");
					String password = s.nextLine();
					try {
						psi.makeLoginRequest(username, password);
						L.info("Request has been made! Please wait for an employee to approve of your new Login.");
						L.warn("A new Login is pending approval.");
					} catch (BusinessException e) {
						L.info(e.getMessage());
					}
				}
				break;
			case 3: // Employee Login
				L.info("Please enter your username:");
				String eUsername = s.nextLine();
				L.info("Please enter your password:");
				String ePassword = s.nextLine();
				boolean b = false;
				try {
					b = psi.isValidEmployee(eUsername, ePassword);
				} catch (BusinessException e) {
					L.info(e.getMessage());
					L.warn(e.getStackTrace() + e.getMessage());
				}
				if (!b) {
					L.warn("An invalid Employee login attempt was made.");
					L.info("Invalid login. Please try again.");
				} else { // I'm in.
					int choice2 = 0;
					do {
						L.info("Welcome valued employee.\nWhat would you like to do?\n"
								+ "+--------------------------------------------------+\n"
								+ "1. View Login requests\n"
								+ "2. Inspect Customer\n"
								+ "3. View transaction log\n"
								+ "4. Log Out");
						try {
							choice2 = Integer.parseInt(s.nextLine());
						} catch (NumberFormatException e) {
							L.warn("Customer tried to enter a non-number in the menu.");
							L.info("Invalid input! Please enter a number.");
						}
						switch (choice2) {
						case 1: // View Login requests
							
							int choice3 = 0;
							do {
								L.info("Please enter the listing that you would like to approve.\n"
										+ "Enter the listing as a negative number to reject.\n"
										+ "Enter 0 to quit.");
								try {
									List<Login> requests = psi.listLoginsInactive();
									L.info(psi.loginListString(requests));
									choice3 = Integer.parseInt(s.nextLine());
									if (choice3 > 0) {
										psi.approveLoginRequest(requests.get(choice3 - 1));
									} else if (choice3 < 0) {
										psi.rejectLoginRequest(requests.get((choice3 * -1) - 1));
									}
								} catch (NumberFormatException e) {
									L.info("Not a valid input. Please enter a number.");
									L.warn(e.getStackTrace() + "Employee invalid input." + e.getMessage());
									break;
								} catch (BusinessException e) {
									L.info(e.getMessage());
									L.warn(e.getStackTrace() + e.getMessage());
									break;
								} catch (NullPointerException e) {
									L.info("Couldn't get the list of login requests.");
									L.warn(e.getStackTrace() + e.getMessage());
									break;
								}
							} while (choice3 != 0);
							break;
						case 2: // Inspect Customer
							int choice4 = 0;
							do {
								L.info("Please select a customer. Enter 0 to exit.");
								try {
									List<Login> loginList = psi.listLoginsActive();
									L.info(psi.loginListString(loginList));
									choice4 = Integer.parseInt(s.nextLine());
									L.info(psi.accountListString(psi.accountListByLogin(loginList.get(choice4 - 1))));
								} catch (NumberFormatException e) {
									L.info("Not a valid input. Please input a number.");
									L.warn(e.getStackTrace() + "Employee invalid input." + e.getMessage());
								} catch (IndexOutOfBoundsException e) {
									L.info("That value is out of range.");
									L.warn(e.getStackTrace() + "Employee invalid input." + e.getMessage());
								} catch (BusinessException e) {
									L.info(e.getMessage());
									L.warn(e.getStackTrace() + "Employee invalid input." + e.getMessage());
								}
							} while (choice4 > 0);
							break;
						case 3: // View Transaction Log
							L.info("Displaying Transaction log:");
							try {
								L.info(psi.transactionListString(psi.getTransactionList()));
							} catch (BusinessException e) {
								L.warn(e.getStackTrace() + e.getMessage());
								L.info(e.getMessage());
							}
							break;
						case 4: // Log Out
							L.info("Thank you for your hard work. Enjoy your day!\n\n");
							break;
						}
					} while (choice2 != 4);
				}
				break;
			case 4: // Quit
				L.info("Thank you for choosing Ploutos Bank. Have a nice day!");
				break;
			}
			if (choice != 4)
				choice = 0;
		} while (choice != 4);
		s.close();
	}
	
	public static void accessAccount(Account account, Login login, Scanner s, PloutosService psi) {
		int choice = 0;
		do {
			L.info("What would you like to do?\n"
					+ "+------------------------+\n"
					+ "1. Withdraw\n"
					+ "2. Deposit\n"
					+ "3. Transfer to an account I own\n"
					+ "4. Transfer to an account by Account Number\n"
					+ "5. Exit");
			try {
				choice = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				L.info("Not valid input. Please input a number.");
			}
			switch (choice) {
			case 1: // Withdraw
				L.info("Please specify a non-negative amount.");
				int amount;
				try {
					amount = Integer.parseInt(s.nextLine());
					psi.withdraw(account, amount);
				} catch (NumberFormatException e) {
					L.info("Invalid input. Please enter a number.");
				} catch (BusinessException e) {
					L.info(e.getMessage());
				}
				break;
			case 2: // Deposit
				L.info("Please specify a non-negative amount.");
				int amount2;
				try {
					amount2 = Integer.parseInt(s.nextLine());
					psi.deposit(account, amount2);
				} catch (NumberFormatException e) {
					L.info("Invalid input. Please enter a number.");
				} catch (BusinessException e) {
					L.info(e.getMessage());
				}
				break;
			case 3: // Transfer to me
				int choice3 = 0;
				List<Account> accList = new ArrayList<>();
				try {
					accList = psi.accountListByLogin(login);
					L.info("\nHere are your bank accounts:\n"
							+ "+--------------------------+");
					L.info(psi.accountListString(accList));
				} catch (BusinessException e) {
					L.warn(e.getStackTrace() + " " + e.getMessage());
					L.info(e.getMessage());
				}
				L.info("Please select the listing you would like to transfer money to."
						+ " To cancel this transaction, simply press the enter key without entering anything.");
				Account account2 = null;
				try {
					choice3 = Integer.parseInt(s.nextLine()) - 1;
					account2 = accList.get(choice3);
				} catch (NumberFormatException e) {
					L.warn("Customer tried to enter a non-number in the menu.");
					L.info("Invalid input! Please enter a number.");
				} catch (IndexOutOfBoundsException e) {
					L.info("That is not a valid choice, please try again.");
				}
				if (account2 != null) {
					L.info("Please input the non-negative amount you'd like to transfer to this account.");
					try {
						int amount3 = Integer.parseInt(s.nextLine());
						psi.makeTransaction(account2, account, amount3);
					} catch (NumberFormatException e) {
						L.info("Not a valid input. Please input a number.");
					} catch (BusinessException e) {
						L.info(e.getMessage());
					}
				}
				break;
			case 4: // Transfer by ACC Number
				L.info("Please enter the Account Number of the bank account you wish to send money to.");
				Account account3 = null;
				try {
					int account3Number = Integer.parseInt(s.nextLine());
					account3 = psi.getAccountByNumber(account3Number);
				} catch (NumberFormatException e) {
					L.info("Invalid input. Please input a number.");
				} catch (BusinessException e) {
					L.info(e.getMessage());
				}
				if (account3 != null) {
					L.info("Please enter the amount you wish to send.");
					int amount3;
					try {
						amount3 = Integer.parseInt(s.nextLine());
						psi.makeTransaction(account3, account, amount3);
					} catch (NumberFormatException e) {
						L.info("Invalid input. Please enter a number.");
					} catch (BusinessException e) {
						L.info(e.getMessage());
					}
				}
				break;
			case 5:
				L.info("Exiting Bank Account. Thank you for your business.");
				break;
			}
			if (choice != 5)
				choice = 0;
		} while(choice != 5);
	}
}

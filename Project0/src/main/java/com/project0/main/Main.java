package com.project0.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.project0.exception.BusinessException;
import com.project0.model.Account;
import com.project0.model.User;
import com.project0.service.Project0Service;
import com.project0.service.impl.Project0ServiceImpl;

public class Main {
	
	final static Logger loggy = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		loggy.setLevel(Level.ALL);
		System.out.println("Welcome to the BK Bank Account Management Console App");
		System.out.println("-----------------------------------------------------");
		Project0Service p0s = new Project0ServiceImpl();
		int ch = 0;
		do {
			System.out.println("Bank Main Menu");
			System.out.println("--------------");
			System.out.println("1) Login");
			System.out.println("2) Register New User");
			System.out.println("3) Quit");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid entry");
			}
			
			switch(ch) {
			case 1:
				System.out.print("Enter User Name: ");
				String username = scanner.nextLine();
				System.out.print("Enter Password: ");
				String password = scanner.nextLine();
				User u = null;
				try {
					u = p0s.getUser(username, password);
				} catch (BusinessException e) {
					loggy.error("Invalid Login Error", e);
				}
				if (u != null) {
					accountManagement(u, scanner, p0s);
				}
				break;
			case 2:
				System.out.print("Enter New User Name: ");
				String un = scanner.nextLine();
				System.out.print("Enter New Password: ");
				String pw = scanner.nextLine();
				boolean isEmp = false;
				System.out.print("Are you a BK Bank employee (Y/N)? ");
				char emp = scanner.nextLine().charAt(0);
				do {
					switch(emp) {
					case 'Y'|'y':
						isEmp = true;
						break;
					case 'N'|'n':
						isEmp = false;
						break;
					default:
						System.out.println("Enter yes or no");
						break;
					}
				} while (emp != 'Y' && emp != 'y' && emp != 'N' && emp != 'n');
				try {
					p0s.createUser(new User(un, pw, isEmp));
				} catch (BusinessException e1) {
					loggy.error("Could not create user", e1);
				}
				break;
			case 3:
				System.out.println("Thank you for banking with us!");
				System.exit(0);
				break;
			default:
				System.out.println("Entry should be between 1 and 3");
				break;
			}
		} while (ch != 3);
	}

	private static void accountManagement(User u, Scanner scanner, Project0Service p0s) {
		boolean success = false;
		if(u.isEmployee()) {
			int empl = 0;
			do {
				System.out.println("Employee Menu");
				System.out.println("--------------");
				System.out.println("1) Approve New Account");
				System.out.println("2) Reject New Account");
				System.out.println("3) View Accounts Pending Approval");
				System.out.println("4) View Accounts By Customer");
				System.out.println("5) View Transaction Log");
				System.out.println("6) Logout");
				
				try {
					empl = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e17) {
					loggy.error(e17.getMessage());
				}
				
				success = false;
				
				switch(empl) {
				case 1:
					System.out.print("Enter account number: ");
					int aNum = 0;
					try {
						aNum = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e18) {
						loggy.error(e18.getMessage());
					}
					
					try {
						Account acct = p0s.approveAccount(u, p0s.getAccountById(u, aNum));
						success = (acct.isApproved());
					} catch (BusinessException e19) {
						loggy.error(e19.getMessage());
					}
					
					if (success) {
						loggy.info("Account " + aNum + " approved by " + u.getUserName());
					}
					break;
				case 2:
					System.out.print("Enter account number: ");
					int acctNum = 0;
					try {
						acctNum = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e20) {
						loggy.error(e20.getMessage());
					}
					
					try {
						p0s.rejectAccount(u, p0s.getAccountById(u, acctNum));
						success = (p0s.getAccountById(u, acctNum) == null);
					} catch (BusinessException e21) {
						loggy.error(e21.getMessage());
					}
					
					if (success) {
						loggy.info("Account " + acctNum + " rejected by " + u.getUserName());
					}
					break;
				case 3:
					List<Account> pendingList = new ArrayList<>();
					try {
						pendingList = p0s.getPendingAccounts(u);
						for (Account a : pendingList) {
							System.out.println(a);
						}
					} catch (BusinessException e22) {
						loggy.error(e22.getMessage());
					}
					break;
				case 4:
					List<Account> customerList = new ArrayList<>();
					System.out.print("Enter name of user: ");
					String enteredUser = scanner.nextLine();
					try {
						customerList = p0s.getAccountsByUserName(u, enteredUser);
						for (Account a : customerList) {
							System.out.println(a);
						}
					} catch (BusinessException e23) {
						loggy.error(e23.getMessage());
					}
					break;
				case 5:
					try {
						p0s.getTransactionLog();
					} catch (BusinessException e24) {
						loggy.error(e24.getMessage());
					}
				case 6:
					System.out.println("Logging out...");
					break;
				default:
					System.out.println("Please enter a number between 1 and 6");
					break;
				}
			} while (empl != 6);
		} else {
			int cust = 0;
			do {
				System.out.println("Customer Menu");
				System.out.println("-------------");
				System.out.println("1) Apply for New Account");
				System.out.println("2) View Account Balance");
				System.out.println("3) Deposit Money");
				System.out.println("4) Withdraw Money");
				System.out.println("5) Transfer Money to Another Account");
				System.out.println("6) View All My Accounts");
				System.out.println("7) Logout");
				try {
					cust = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					loggy.error("Invalid Login Error", e);
				}
				
				switch(cust) {
				case 1:
					System.out.print("Enter starting balance: ");
					double balance = 0;
					try {
						balance = Double.parseDouble(scanner.nextLine());
					} catch (NumberFormatException e2) {
						loggy.error("Input error", e2);
					}
					
					try {
						Account newAcc = p0s.createAccount(u, new Account(10000, u.getUserName(), balance, false));
						if (p0s.getAccountById(u, newAcc.getAccountId()) != null) {
							loggy.info("Customer " + u.getUserName() + " applied for account " + newAcc.getAccountId() +
									   " with starting balance " + newAcc.getBalance());
						}
					} catch (BusinessException e3) {
						loggy.error(e3.getMessage());
					}
					break;
				case 2:
					System.out.print("Enter account number: ");
					int accNum = 0;
					try {
						accNum = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e4) {
						loggy.error(e4.getMessage());
					}
					
					Account acc = null;
					
					try {
						acc = p0s.getAccountById(u, accNum);
					} catch (BusinessException e5) {
						loggy.error(e5.getMessage());
					}
					
					if (acc != null) {
						System.out.println("Account balance is " + acc.getBalance());
					}
					break;
				case 3:
					System.out.print("Enter account number: ");
					int dpNum = 0;
					try {
						dpNum = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e4) {
						loggy.error(e4.getMessage());
					}
					
					Account dpAcc = null;
					
					try {
						dpAcc = p0s.getAccountById(u, dpNum);
					} catch (BusinessException e5) {
						loggy.error(e5.getMessage());
					}
					
					if (dpAcc != null) {
						System.out.print("Enter amount to deposit: ");
						double dpAmt = 0;
						try {
							dpAmt = Double.parseDouble(scanner.nextLine());
						} catch (NumberFormatException e6) {
							loggy.error(e6.getMessage());
						}
						
						try {
							double originalBalance = dpAcc.getBalance();
							dpAcc = p0s.deposit(u, dpNum, dpAmt);
							success = ((dpAcc.getBalance() == originalBalance + dpAmt) && dpAmt >= 0);
						} catch (BusinessException e7) {
							loggy.error(e7.getMessage());
						}
						
						if (success)
							loggy.info("Customer " + u.getUserName() + " deposited $" + dpAmt + " from account " +
									   dpNum + "\nRemaining balance: " + dpAcc.getBalance());
					}
					break;
				case 4:
					System.out.print("Enter account number: ");
					int wdNum = 0;
					try {
						wdNum = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e4) {
						loggy.error(e4.getMessage());
					}
					
					Account wdAcc = null;
					
					try {
						wdAcc = p0s.getAccountById(u, wdNum);
					} catch (BusinessException e8) {
						loggy.error(e8.getMessage());
					}
					
					if (wdAcc != null) {
						System.out.print("Enter amount to withdraw: ");
						double wdAmt = 0;
						try {
							wdAmt = Double.parseDouble(scanner.nextLine());
						} catch (NumberFormatException e9) {
							loggy.error(e9.getMessage());
						}
						
						try {
							double origAmt = wdAcc.getBalance();
							wdAcc = p0s.withdraw(u, wdNum, wdAmt);
							success = ((wdAcc.getBalance() == origAmt - wdAmt) && wdAmt >= 0);
						} catch (BusinessException e10) {
							loggy.error(e10.getMessage());
						}
						
						if (success)
							loggy.info("Customer " + u.getUserName() + " withdrew $" + wdAmt + " from account " +
										wdNum + "\nRemaining balance: " + wdAcc.getBalance());
					}
					break;
				case 5:
					System.out.print("Enter account number: ");
					int trNum = 0;
					try {
						trNum = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e11) {
						loggy.error(e11.getMessage());
					}
					
					Account trAcc = null;
					
					try {
						trAcc = p0s.getAccountById(u, trNum);
					} catch (BusinessException e12) {
						loggy.error(e12.getMessage());
					}
					
					if (trAcc != null) {
						System.out.print("Enter number of account the funds are to be transferred into: ");
						int targetNum = 0;
						try {
							targetNum = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e13) {
							loggy.error(e13.getMessage());
						}
						System.out.print("Enter amount to transfer: ");
						double trAmt = 0;
						try {
							trAmt = Double.parseDouble(scanner.nextLine());
						} catch (NumberFormatException e14) {
							loggy.error(e14.getMessage());
						}
						
						Account targetAcc = null;
						
						try {
							targetAcc = p0s.getAccountById(u, targetNum);
						} catch (BusinessException e15) {
							loggy.error(e15.getMessage());
						}
						
						try {
							double orig1 = trAcc.getBalance();
							double orig2 = targetAcc.getBalance();
							p0s.transferMoney(u, trNum, targetNum, trAmt);
							Account testTr = p0s.getAccountById(u, trNum);
							Account testTarget = p0s.getAccountById(u, targetNum);
							success = ((testTr.getBalance() == orig1 - trAmt) &&
										(testTarget.getBalance() == orig2 + trAmt) &&
										(trAmt >= 0));
						} catch (BusinessException e16) {
							loggy.error(e16.getMessage());
						}
						
						if (success)
							loggy.info("Customer " + u.getUserName() + " transferred $" + trAmt + " from account " +
										trNum + " into account " + targetNum + "\nRemaining balance: " + trAcc.getBalance());
					}
					break;
				case 6:
					List<Account> allCustList = new ArrayList<>();
					try {
						allCustList = p0s.getAccountsByUserName(u, u.getUserName());
						for (Account a : allCustList) {
							System.out.println(a);
						}
					} catch (BusinessException e24) {
						loggy.error(e24.getMessage());
					}
					
				case 7:
					System.out.println("Logging out...");
					break;
				default:
					System.out.println("Value must be between 1 and 6");
				}
				
			} while(cust != 7);
			
		}
	}

}

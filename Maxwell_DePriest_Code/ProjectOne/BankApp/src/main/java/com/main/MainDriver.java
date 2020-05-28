//package com.main;
//
//import com.dao.impl.UserDaoImpl;
//import com.exceptions.BusinessException;
//import com.models.User;
//import com.models.Account;
//import com.dao.impl.AccountDaoImpl;
//import com.services.impl.UserServiceImpl;
//import com.services.impl.AccountServiceImpl;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;public class MainDriver {
//	final static Logger loggy = Logger.getLogger(User.class);
//
//	public void intro() {
//		UserDaoImpl uDI = new UserDaoImpl();
//		AccountDaoImpl aDI = new AccountDaoImpl();
//		AccountServiceImpl aSI = new AccountServiceImpl();
//		UserServiceImpl uSI = new UserServiceImpl();
//		User currentUser = null;
//		Account currentAccount = null;
//		int intChoice = 0;
//		Scanner sc = new Scanner(System.in);
//	
//		while(currentUser == null) {
//			//Scanner sc = new Scanner(System.in);
//			loggy.info("\nWelcome to Loxely Bank, where we save for the rich and loan to the poor.\n");
//			loggy.info("Please choose from the options below");
//			loggy.info("1. Register new profile");
//			loggy.info("2. Login");
//			loggy.info("3. Quit");
//			try {
//				String c = sc.nextLine();
//				if(c.matches("[1-3]{1}")) {
//					if(c.equals("1")) {
//						try {
//						uSI.createNewUser(currentUser);
//							
//						} catch (BusinessException | IndexOutOfBoundsException e) {
//							loggy.error("Error thrown during new user registry");
//						}
//					}
//					else if (c.equals("2")) {
//						
//							loggy.info("Please enter your email");
//							String em = sc.nextLine();
//							loggy.info("Please enter your password");
//							String pw = sc.nextLine();
//							try {
//								if(uSI.userLogin(em, pw)) {
//									currentUser = uSI.setCurrentUser(em);
//									currentAccount = uSI.setCurrentAccount(em);
//									loggy.debug("Current User = " + currentUser.getEmail());
//								}
//							} catch (BusinessException e1) {
//								loggy.info(e1.getMessage());
//								e1.printStackTrace();
//							}
//						
//					}
//					else if (c.equals("3")) {
//						loggy.info("Have a nice day");
//						System.exit(0);
//						
//					}// end of 1-3 if block
//				}// end of 1-3 regex if block
//
//			} catch (NullPointerException e) {
//				loggy.error("Exception thrown- " + e);
//				loggy.info("Error. Please enter 1 to register a new account, 2 to log in, or 3 to quit");
//			}
//			catch(NoSuchElementException e) {
//				loggy.error("NoSuchElement- " + e);
//			}// end try block
//			
//			
//		}// end of login while block
//		
//		boolean accountCheck = uSI.checkForAccount(currentUser);
//		do {
//			//Scanner sc = new Scanner(System.in);
//			loggy.info("\nHello, " + currentUser.getName());	
//			loggy.info("How may we assist you today?");
//			loggy.info("\nPlease choose one of the options below");
//			loggy.info("1. Sign up for an account");
//			if (accountCheck) {
//				loggy.info("2. View checking account balance");
//				loggy.info("3. View savings account balance");
//				loggy.info("4. Deposit into an account");
//				loggy.info("5. Withdraw from an account");
//				loggy.info("6. Transfer funds between accounts");
//			}
//			if(currentUser.getStatus().equals("employee")) {
//				loggy.info("7. View all accounts");
//				loggy.info("8. Activate pending accounts");
//				}
//			loggy.info("9. Quit");
//			String choice = sc.nextLine();
//			
//			if (choice.matches("[1-9]{1}")) {
//				intChoice = Integer.parseInt(choice);
//				
//				if(intChoice == 9) {
//					loggy.info("Have a nice day!");
//					loggy.debug("Choice 9 entered. Exiting program");
//					System.exit(0);
//				}
//				else {
//					switch (intChoice) {
//					
//					case 1:
//						try {
//							Account aTest = aDI.selectAccountByEmail(currentUser.getEmail());
//							if (aTest.getActive() == null) {
//								uSI.signUpForAccount(currentUser.getEmail());
//							}
//							else {
//								loggy.info("You may already have an account, or we are still in the process of verifying your information. Please be patient.");
//							}
//						} catch (BusinessException e3) {
//							loggy.error("aTest threw exception");
//							e3.printStackTrace();
//						}
//						break;
//					case 2:
//						if(accountCheck) {
//							try {
//								String chkAmt= uSI.checkCheckingBalance(currentUser.getEmail());
//								loggy.info("Your current balance is $" + chkAmt);
//							} catch (BusinessException e1) {
//								loggy.info("Error. Please try again or contact the SYSADMIN");
//							}
//						}
//						else {
//							loggy.info("You must have an active account to continue");
//						}
//						break;
//					case 3:
//						if(accountCheck) {
//							try {
//								String svgAmt = uSI.checkSavingsBalance(currentUser.getEmail());
//								loggy.info("Your current balance is $" + svgAmt);
//							} catch (BusinessException e1) {
//								loggy.info("Error. Please try again or contact the SYSADMIN");
//								e1.printStackTrace();
//							}
//						}
//						else {
//							loggy.info("You must have an active account to continue");
//						}
//						break;
//					case 4:
//						if (accountCheck) {
//							loggy.info("Which account would you like to deposit into?\nChecking or Savings");
//							String ch = sc.nextLine();
//							loggy.info("How much would you like to deposit?");
//							String amt = sc.nextLine();
//							//if (amt.matches("[0-9.]{1,7}") && Double.parseDouble(amt) > 0) {
//								if (ch.equalsIgnoreCase("checking")) {
//									try {
//										uSI.depositMoney("checkingBalance", amt, currentUser.getEmail());
//									} catch (BusinessException e) {
//										loggy.info("Error. Please try again");
//										loggy.error("Error thrown depositing into checking");
//									}
//								}
//								else if (ch.equalsIgnoreCase("savings")) {
//									try {
//										uSI.depositMoney("savingsBalance", amt, currentUser.getEmail());
//									} catch (BusinessException e) {
//										loggy.info("Error. Please try again");
//										loggy.info("Error thrown depositing into savings");
//										e.printStackTrace();
//									}
//								}
//								else {
//									loggy.info("Error. Please enter checking or savings");
//								}
//							//} 
//							//else {
//								//loggy.info("Error. Amount cannot exceed 1000.00");
//							//}// end of amt.matches if block	
//						}
//						else {
//							loggy.info("You must have an active account to continue");
//						}
//						break;
//					case 5:
//						if(accountCheck) {
//							loggy.info("Which account would you like to withdraw from?\nChecking or Savings");
//							String ch = sc.nextLine();
//							loggy.info("How much would you like to withdraw?");
//							String amt = sc.nextLine();
//							//if (amt.matches("[0-9.]{1,7}")) {
//								if (ch.equalsIgnoreCase("checking")) {
//									try {
//										uSI.withdrawMoney("checkingBalance", amt, currentUser.getEmail());
//									} catch (BusinessException e) {
//										loggy.info("Error. Please try again");
//										loggy.error("Error thrown from checking");
//									}
//								}
//								else if (ch.equalsIgnoreCase("savings")) {
//									try {
//										uSI.withdrawMoney("savingsBalance", amt, currentUser.getEmail());
//									} catch (BusinessException e) {
//										loggy.info("Error. Please try again");
//										loggy.info("Error thrown withdrawing from savings");
//										e.printStackTrace();
//									}
//								//}
//								//else {
//									//loggy.info("Error. Please enter checking or savings");
//								//}
//							}
//							else {
//								loggy.info("You must have an active account to continue");
//							}
//						}// end if account check 
//						break;
//					case 6: 
//						if (accountCheck) {
//							loggy.info("What is the account number of the account that you wish to transfer money to?");
//							String accNum = sc.nextLine();
//							loggy.info("How much would you like to send?");
//							String amt = sc.nextLine();
//							
//							try {
//								uSI.transferFunds(currentUser.getEmail(), "checkingBalance", accNum, amt);
//								uSI.printStatement(currentUser);
//							} catch (BusinessException e) {
//								loggy.info("Error. Please try again.");
//								e.printStackTrace();
//							}
//						}
//						else {
//							loggy.info("You must have an active account to continue");
//						}
//						break;
//					case 7: 
//						uSI.viewAllAccounts(currentUser);
//						break;
//					case 8:
//						uSI.activatePendingAccounts(currentUser);
//						break;
//						
//					} // end of switch
//				}
//				
//				
//			} // end of 1-6 if check
//			
//			else {
//				loggy.error("Input != 1-6 in main menu");
//				loggy.info("Error. Please choose the number from the list provided");
//			}
//		
//		}while (intChoice != 9); // end while loop
//		
//
//	}// End of main
//
//}// End of class
//

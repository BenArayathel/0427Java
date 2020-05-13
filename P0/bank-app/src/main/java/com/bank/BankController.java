package com.bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.data.*;

/**
 * This class is the central controller and communicator between the bank and
 * users
 * 
 * @author Dave Wroblewski
 *
 */
public class BankController {

	private ResultSet rs, cs;
	private Customer you;
	private int accNum;
	private int pin;
	
	final static Logger log = Logger.getLogger(BankController.class);

	public BankController(String inTable) {
		log.setLevel(Level.ALL);
		this.rs = JDBC.getResultSet(inTable);
		this.cs = JDBC.getResultSet("customerstable");
	}

	public BankController(ResultSet inResultSet) {
		log.setLevel(Level.ALL);
		this.rs = inResultSet;
		this.cs = JDBC.getResultSet("customerstable");
	}

	/**
	 * Checks against a map to verify correct information entered to access account
	 * @param inAccountNumber the number of the current account being accessed 
	 * @param inPin the corresponding pin associated with the account
	 * @throws SQLException	
	 * @throws InvalidAccountException
	 */
	public void logIn(int inAccountNumber, int inPin) throws SQLException, InvalidAccountException {
		
		this.accNum = 0;
		this.pin = 0;
		boolean isApproved = false;
		while(cs.next()) {
			if(cs.getInt(1) == inAccountNumber && cs.getBoolean(7) == true) {
				isApproved = true;
			}
		}
		
		while(rs.next()) {
			if(rs.getInt(1) == inAccountNumber) {
				this.accNum = rs.getInt(1);
				this.pin = rs.getInt(2);
			}
		}
		if(isApproved) {
			// Verification of account and pin number
			if (this.accNum == inAccountNumber && this.pin == inPin) {
				System.out.println("Success");
				this.you = new Customer(accNum);
				int userSelection = 0;
				boolean trigger = false;
				while (!trigger) {
					Scanner input = new Scanner(System.in);
					System.out.println("==================================================\n"
							+ "What would you like to do?\n" 
							+ "1: Check Balance\n" 
							+ "2: Deposit\n"
							+ "3: Withdraw\n"
							+ "4: Transfer between checking and savings\n"
							+ "5: Transfer to another account\n"
							+ "9: Logout\n");
	
					if (input.hasNextInt()) {
						userSelection = input.nextInt();
						switch (userSelection) {
						case 9:
							//Kicks user out of logged in status
							trigger = true;
							System.out.println("You have been succussfully logged out.");
							break;
						case 8:
	
							break;
						case 7:
	
							break;
						case 6:
							
							break;
						case 5:
							checkBalance(inAccountNumber, 0); 
							System.out.println("=================================================="
									+ "\nWould you like to transfer from savings or checking?\n"
									+ "1: Savings\n"
									+ "2: Checking\n"
									+ "3: Back");
							int sel = Integer.parseInt(input.next());
							boolean check = false;
							do {
								//From Savings into another account
								if(sel == 1) {
									BankController.transferToOutsideAccount(true, this.accNum);
									check = true;
								}else if(sel == 2) {
									BankController.transferToOutsideAccount(false, this.accNum);
									check = true;
								}else if(sel == 3) {
									check = true;
								}else {
									System.out.println("Please enter a valid selection (1 - 2)\n");
								}
	
							}while(!check);
							break;
						case 4:
							checkBalance(accNum, 0);
							System.out.println("==================================================\n"
									+ "Please make a selection, transfer from account to account\n"
									+ "1: Savings -> Checking\n"
									+ "2: Checking -> Savings\n"
									+ "3: Back");
							String userSel = input.next();
							
							boolean correctInput = false;
							do {
								//Savings into checking
								if(userSel.equals("1")) {
									BankController.transferCheckingAndSaving(false, this.accNum);
									correctInput = true;
								//Checking into savings
								}else if(userSel.equals("2")) {
									BankController.transferCheckingAndSaving(true, this.accNum);
									correctInput = true;
								}else if(userSel.equals("3")) {
									correctInput = true;
								}else {
									System.out.println("Please enter a valid selection (1 - 2)\n");
								}
							}while(!correctInput);
							break;
						case 3:
							System.out.println("Would you like to withdraw from savings or checking?\n" 
									+ "1: Savings\n"
									+ "2: Checking\n");
	
							String savChW = input.next();
							if (savChW.contentEquals("1")) {
								BankController.withdraw(you.getAccountNumber(), "savingsAmt");
							} else if (savChW.equals("2")) {
								BankController.withdraw(you.getAccountNumber(), "checkingAmt");
							}else if(savChW.equals("3")) {
								
							}
							else {
	
							}
							break;
						case 2:
							System.out.println("Would you like to deposit into savings or checking?\n"
									+ "1: Savings\n"
									+ "2: Checking");
	
							String savChD = input.next();
							if (savChD.contentEquals("1")) {
								BankController.deposit(you.getAccountNumber(), "savingsAmt");
							} else if (savChD.equals("2")) {
								BankController.deposit(you.getAccountNumber(), "checkingAmt");
							} else {
	
							}
							break;
						case 1:
							BankController.checkBalance(you.getAccountNumber(), 1);
							break;
						default:
	
							break;
						}
					} else {
						System.out.println("Please enter a valid selection.");
						try {
							int read = System.in.read(new byte[2]);
						} catch (IOException e) {
							System.out.println("There was an error, please contact your system administrator.");
						}
					}
					//input.close();
				}
	
			} else {
				System.out.println("Failure");
			}
		}else {
			System.out.println("Sorry but your account has not yet been approved or has been denied approval by the bank.\n"
					+ "Please contact your local branch for more information.\n");
		}
	}
	
	/**
	 * Transfers money between checking and savings and vice versa 
	 * inside the same account
	 * @param forward boolean variable, if true transfer is from checking into savings. False is savings into checking
	 * @throws SQLException 
	 */
	public static void transferCheckingAndSaving(boolean forward, int inAccountNumber) throws SQLException {
		Scanner input = new Scanner(System.in);
		if(forward) {
			System.out.println("==================================================\n"
					+ "How much would you like to transfer into savings?"); //FROM CHECKING
			double xferAmt = Integer.parseInt(input.next());	//The amount the customer wishes to transfer
			double newCheckingAmount, newSavingsAmount;
			Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
			while(localSet.next()) {
				//Update receiving account to reflect increase
				if(localSet.getInt(1) == inAccountNumber) {
					double savingsValue = localSet.getDouble(3);	//Gets savingsAmt value
					double checkingValue = localSet.getDouble(4);	//Gets checkingAmt value
					newSavingsAmount = savingsValue + xferAmt;
					newCheckingAmount = checkingValue - xferAmt;
					
					if(newCheckingAmount >= 0) {
						//Update Increase into checking
						JDBC.update("accountstable", "savingsAmt", newSavingsAmount, inAccountNumber);
						//Update Decrease from checking
						JDBC.update("accountstable", "checkingAmt", newCheckingAmount, inAccountNumber);
						log.info("$" + newSavingsAmount + " was transfered from checking to savings in account " + inAccountNumber);
					}else {
						System.out.println("You do not have sufficient funds to complete this transaction.");
						log.info("FAIL: $" + newSavingsAmount + " transfer from checking to savings in account " + inAccountNumber +". Insufficient funds");
					}
				}
			}
		}else {
			System.out.println("==================================================\n"
					+ "How much would you like to transfer into checking?");
			double xferAmt = Double.parseDouble(input.next());	//The amount the customer wishes to transfer
			double newCheckingAmount, newSavingsAmount;
			Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
			while(localSet.next()) {
				//Update receiving account to reflect increase
				if(localSet.getInt(1) == inAccountNumber) {
					double savingsValue = localSet.getDouble(3);	//Gets savingsAmt value
					double checkingValue = localSet.getDouble(4);	//Gets checkingAmt value
					newSavingsAmount = savingsValue - xferAmt;
					newCheckingAmount = checkingValue + xferAmt;
					
					if(newSavingsAmount >= 0) {
						//Update Decrease from savings
						JDBC.update("accountstable", "savingsAmt", newSavingsAmount, inAccountNumber);
						//Update Increase into checking
						JDBC.update("accountstable", "checkingAmt", newCheckingAmount, inAccountNumber);
						log.info("$" + newSavingsAmount + " was transfered from savings to checking in account " + inAccountNumber);
					}else {
						System.out.println("You do not have sufficient funds to complete this transaction.");
						log.info("FAIL: $" + newSavingsAmount + " transfer from savings to checking in account " + inAccountNumber +". Insufficient funds");
					}
				}
			}
		}
	}
	
	/**
	 * Transfers money from one account to another
	 * @param forward If true, transfer is made from savings, if false transfer is made from checking
	 * @param inAccountNumber
	 * @throws SQLException
	 */
	public static void transferToOutsideAccount(boolean forward, int inAccountNumber) throws SQLException {
		Scanner input = new Scanner(System.in);
		if(forward) {
			//From savings into another account
			System.out.println("==================================================\n"
					+ "Please enter the account number you wish to deposit into: ");
			int sendToAcctNum = Integer.parseInt(input.next());
			System.out.println("How much would you like to transfer from savings?");
			double xferAmt = Double.parseDouble(input.next());
			Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
			double addAmt = -1;
			double subAmt = -1;
			boolean corrAcct = false;
			while(localSet.next()) {
				//Update receiving account to reflect increase
				if(localSet.getInt(1) == sendToAcctNum) {
					addAmt = localSet.getDouble(3);
					addAmt += xferAmt;
					corrAcct = true;
				}
				//Update sending account to reflect decrease
				if(localSet.getInt(1) == inAccountNumber) {
					subAmt = localSet.getDouble(3);
					subAmt -= xferAmt;
				}
			}
			if(corrAcct) {
				if(subAmt >= 0) {
					JDBC.update("accountstable", "savingsAmt", addAmt, sendToAcctNum); 	
					JDBC.update("accountstable", "savingsAmt", subAmt, inAccountNumber);
					log.info("$" + addAmt + " was transfered from savings account " + inAccountNumber + ", to account " + sendToAcctNum);
				}else {
					System.out.println("Insufficient funds to to complete this transaction.");
					log.warn("FAIL: Transfer from account " + inAccountNumber + ". Insufficient funds,");
				}
			}else {
				System.out.println("Invalid account number");
			}
		}else {
			System.out.println("==================================================\n"
					+ "Please enter the account number you wish to deposit into: ");
			int sendToAcctNum = Integer.parseInt(input.next());
			System.out.println("How much would you like to transfer from checking?");
			double xferAmt = Double.parseDouble(input.next());
			Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
			double addAmt = -1;
			double subAmt = -1;
			boolean corrAcct = false;
			
			while(localSet.next()) {
				//Update receiving account to reflect increase
				if(localSet.getInt(1) == sendToAcctNum) {
					addAmt = localSet.getDouble(3);
					addAmt += xferAmt;	
					corrAcct = true;
				}
				//Update sending account to reflect decrease
				if(localSet.getInt(1) == inAccountNumber) {
					subAmt = localSet.getDouble(4);
					subAmt -= xferAmt;
				}
			}
			if(corrAcct) {
				if(subAmt >= 0) {
				JDBC.update("accountstable", "savingsAmt", addAmt, sendToAcctNum); 
				JDBC.update("accountstable", "checkingAmt", subAmt, inAccountNumber);
				log.info("$" + addAmt + " was transfered from checking account " + inAccountNumber + ", to account " + sendToAcctNum);
				}else {
					System.out.println("Insufficient funds to to complete this transaction.");
					log.warn("FAIL: Transfer from account " + inAccountNumber + ". Insufficient funds,");
				}
			}else {
				System.out.println("Invalid account number");
			}
		}
	}

	/**
	 * Returns a print out of the account balance
	 * @param inAccountNumber The number of the account currently being accessed
	 * @param inType 1 to stop execution before continuation and wait for user to hit enter, 0 to continue through execution
	 * @throws SQLException
	 */
	public static void checkBalance(int inAccountNumber, int inType) throws SQLException {
		ResultSet inRs = JDBC.getResultSet("accountstable");
		try {
			while (inRs.next()) {
				if (inRs.getInt(1) == inAccountNumber) {
					System.out.println("==================================================\n"
							+ "Your savings account balance is: " + inRs.getDouble(3)
							+ "\nYour checking account balance is: " + inRs.getDouble(4));
					log.info("Account " + inAccountNumber + " accessed for balance inquery.");
				}
			}
		} catch (SQLException e1) {
			System.out.println("There was an error, please contact your system administrator.");
		}
		try {
			if(inType != 0) {
				System.out.println("Press ENTER to continue.");
				int read = System.in.read(new byte[2]);
			}
		} catch (IOException e) {
			System.out.println("There was an error, please contact your system administrator.");
		}
	}
	
	/**
	 * Allows the user to deposit funds into the account
	 * @param inAccountNumber The number of the account currently being accessed
	 * @param inAccountType The type of account (Checking/Savings) you wish to interact with
	 * @throws SQLException
	 */
	public static void deposit(int inAccountNumber, String inAccountType) throws SQLException {
		ResultSet localSet = JDBC.getResultSet("accountstable");
		System.out.println("==================================================\n"
				+ "How much would you like to deposit?\n");
		Scanner depositInput = new Scanner(System.in);
		double depAmt = depositInput.nextDouble();
		int sw = 0;
		if (inAccountType.equals("checkingAmt")) {
			sw = 4;
		} else {
			sw = 3;
		}
		double currBalance = 0.00d;
		while(localSet.next()) {
			if(localSet.getInt(1) == inAccountNumber) {
				currBalance = localSet.getDouble(sw);
			}
		}
		currBalance += depAmt;
		
		JDBC.update("accountstable", inAccountType, currBalance, inAccountNumber);
		log.info("$" + depAmt +" was deposited into account " + inAccountNumber);
		
		
		// Allows user to exit using enter button. Might not need for this method
		// Possibly use timer instead
		System.out.println("Press ENTER key to continue.");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			System.out.println("There was an error, please contact your system administrator.");
		}
		//depositInput.close();
		
	}

	/**
	 * Allows user to withdraw from an account
	 * @param inAccountNumber The number of the account currently being accessed
	 * @param inAccountType The type of account (Checking/Savings) you wish to interact with
	 * @throws SQLException
	 */
	public static void withdraw(int inAccountNumber, String inAccountType) throws SQLException {
		ResultSet localSet = JDBC.getResultSet("accountstable");
		System.out.println("==================================================\n"
				+ "How much would you like to withdraw?\n");
		Scanner withdrawlInput = new Scanner(System.in);
		double depAmt = withdrawlInput.nextDouble();
		int sw = 0;
		if (inAccountType.equals("checkingAmt")) {
			sw = 4;
		} else {
			sw = 3;
		}
		double currBalance = 0.00d;
		while(localSet.next()) {
			if(localSet.getInt(1) == inAccountNumber) {
				currBalance = localSet.getDouble(sw);
			}
		}
		currBalance -= depAmt;
		//General debugging
//		System.out.println("DEBUGGING (BankController:157): " + "In account type: " + inAccountType + " sw val: " + sw
//				+ "\nDB val: " + currBalance + " + input val: " + depAmt + " = total value " + currBalance);
		if(currBalance >= 0) {
			log.info("$" + depAmt +" was withdrawn from account " + inAccountNumber);
			JDBC.update("accountstable", inAccountType, currBalance, inAccountNumber);
		}else {
			System.out.println("You do not have sufficient funds to make this tranaction, make another selection and try again");
			log.info("$" + depAmt +" withdraw failed due to insufficient funds from account " + inAccountNumber);
		}

		// Allows user to exit using enter button. Might not need for this method
		// Possibly use timer instead
		System.out.println("Press ENTER to continue.");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			System.out.println("There was an error, please contact your system administrator.");
		}
	}
}

class InvalidAccountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountException() {
		System.out.println("You have entered an invalid account/PIN combination!");
	}
}

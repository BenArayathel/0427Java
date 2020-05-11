package com.bank;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.data.*;

/**
 * This class is the central controller and communicator between the bank and
 * users
 * 
 * @author Dave Wroblewski
 *
 */
public class BankController {

	private ResultSet rs;
	private Customer you;
	private int accNum;
	private int pin;

	public BankController(String inTable) {
		
		rs = JDBC.getResultSet(inTable);
	}

	public BankController(ResultSet inResultSet) {
		this.rs = inResultSet;
	}

	// checks against a map to verify correct information entered to access account
	public void logIn(int inAccountNumber, int inPin) throws SQLException, InvalidAccountException {
		
		this.accNum = 0;
		this.pin = 0;
		while(rs.next()) {
			if(rs.getInt(1) == inAccountNumber) {
				this.accNum = rs.getInt(1);
				this.pin = rs.getInt(2);
			}
		}

		// Verification of account and pin number
		if (this.accNum == inAccountNumber && this.pin == inPin) {
			System.out.println("Success");
			this.you = new Customer(accNum);
			
			// This is where the customer will remain to make transactions in the account
			// TODO: Have to fix the loop if user hits invalid entry in selections
			int userSelection = 0;
			boolean trigger = false;
			while (!trigger) {
				Scanner input = new Scanner(System.in);
				System.out.println("What would you like to do?\n" 
						+ "1: Check Balance\n" 
						+ "2: Deposit\n"
						+ "3: Withdraw\n"
						+ "4: Transfer between checking and savings\n"
						+ "5: Transfer to another account\n"
						+ "9: Exit\n");

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
						boolean check = false;
						do {
							checkBalance(inAccountNumber, 0); 
							System.out.println("=================================================="
									+ "\nWould you like to transfer from savings or checking?\n"
									+ "1: Savings\n"
									+ "2: Checking");
							int sel = Integer.parseInt(input.next());
							
							if(sel == 1) {
								System.out.println("Please enter the account number you wish to deposit into: ");
								int sendToAcctNum = Integer.parseInt(input.next());
								System.out.println("How much would you like to transfer from savings?");
								double xferAmt = Double.parseDouble(input.next());
								Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					                    ResultSet.CONCUR_UPDATABLE);
								ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
								while(localSet.next()) {
									//Update receiving account to reflect increase
									if(localSet.getInt(1) == sendToAcctNum) {
										double addAmt = localSet.getDouble(3);
										addAmt += xferAmt;
										JDBC.update("accountstable", "savingsAmt", addAmt, sendToAcctNum); 	
									}
									//Update sending account to reflect decrease
									if(localSet.getInt(1) == this.accNum) {
										double subAmt = localSet.getDouble(3);
										subAmt -= xferAmt;
										JDBC.update("accountstable", "savingsAmt", subAmt, this.accNum);
									}
								}
								check = true;
							}else if(sel == 2) {
								System.out.println("Please enter the account number you wish to deposit into: ");
								int sendToAcctNum = Integer.parseInt(input.next());
								System.out.println("How much would you like to transfer from checking?");
								double xferAmt = Double.parseDouble(input.next());
								Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					                    ResultSet.CONCUR_UPDATABLE);
								ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
								while(localSet.next()) {
									//Update receiving account to reflect increase
									if(localSet.getInt(1) == sendToAcctNum) {
										double addAmt = localSet.getDouble(3);
										addAmt += xferAmt;
										JDBC.update("accountstable", "savingsAmt", addAmt, sendToAcctNum); 	
									}
									//Update sending account to reflect decrease
									if(localSet.getInt(1) == this.accNum) {
										double subAmt = localSet.getDouble(4);
										subAmt -= xferAmt;
										JDBC.update("accountstable", "checkingAmt", subAmt, this.accNum);
									}
									check = true;
								}
							}else {
								System.out.println("Please enter a valid selection (1 - 2)\n");
							}
						}while(!check);

						break;
					case 4:
						checkBalance(accNum, 0);
						System.out.println("Please make a selection, transfer from account to account\n"
								+ "1: Savings -> Checking\n"
								+ "2: Checking -> Savings\n");
						String userSel = input.next();
						boolean correctInput = false;
						do {
							//Savings into checking
							if(userSel.equals("1")) {
								System.out.println("How much would you like to transfer into checking?");
								double xferAmt = Integer.parseInt(input.next());	//The amount the customer wishes to transfer
								double newCheckingAmount, newSavingsAmount;
								Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					                    ResultSet.CONCUR_UPDATABLE);
								ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
								while(localSet.next()) {
									//Update receiving account to reflect increase
									if(localSet.getInt(1) == accNum) {
										double savingsValue = localSet.getDouble(3);	//Gets savingsAmt value
										double checkingValue = localSet.getDouble(4);	//Gets checkingAmt value
										newSavingsAmount = savingsValue - xferAmt;
										newCheckingAmount = checkingValue + xferAmt;
										
										//Update Decrease from savings
										JDBC.update("accountstable", "savingsAmt", newSavingsAmount, accNum);
										//Update Increase into checking
										JDBC.update("accountstable", "checkingAmt", newCheckingAmount, accNum);
									}
								}
								correctInput = true;
							//Checking into savings
							}else if(userSel.equals("2")) {
								System.out.println("How much would you like to transfer into savings?");
								double xferAmt = Integer.parseInt(input.next());	//The amount the customer wishes to transfer
								double newCheckingAmount, newSavingsAmount;
								Statement ps = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					                    ResultSet.CONCUR_UPDATABLE);
								ResultSet localSet = ps.executeQuery("SELECT * FROM accountstable");
								while(localSet.next()) {
									//Update receiving account to reflect increase
									if(localSet.getInt(1) == accNum) {
										double savingsValue = localSet.getDouble(3);	//Gets savingsAmt value
										double checkingValue = localSet.getDouble(4);	//Gets checkingAmt value
										newSavingsAmount = savingsValue + xferAmt;
										newCheckingAmount = checkingValue - xferAmt;
										
										//Update Increase into checking
										JDBC.update("accountstable", "savingsAmt", newSavingsAmount, accNum);
										//Update Decrease from checking
										JDBC.update("accountstable", "checkingAmt", newCheckingAmount, accNum);
									}
								}
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
							BankController.withdraw(you.getAccountNumber(), rs, "savingsAmt");
						} else if (savChW.equals("2")) {
							BankController.withdraw(you.getAccountNumber(), rs, "checkingAmt");
						} else {

						}
						break;
					case 2:
						System.out.println("Would you like to deposit into savings or checking?\n" + "1: Savings\n"
								+ "2: Checking\n");

						String savChD = input.next();
						if (savChD.contentEquals("1")) {
							BankController.deposit(you.getAccountNumber(), rs, "savingsAmt");
						} else if (savChD.equals("2")) {
							BankController.deposit(you.getAccountNumber(), rs, "checkingAmt");
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				input.close();
			}

		} else {
			System.out.println("Failure");
		}

	}

	public static void checkBalance(int inAccountNumber, int inType) throws SQLException {
		ResultSet inRs = JDBC.getResultSet("accountstable");
		try {
			while (inRs.next()) {
				if (inRs.getInt(1) == inAccountNumber) {
					System.out.println("Your savings account balance is: " + inRs.getDouble(3)
							+ "\nYour checking account balance is: " + inRs.getDouble(4));
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(inType != 0) {
				System.out.println("Press any key to continue.");
				int read = System.in.read(new byte[2]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Creates the account and adds the information to the master map
	public static void createAccount() {

	}
	
	

	// Allows the user to deposit funds into the account
	public static void deposit(int inAccountNumber, ResultSet inRs2, String inAccountType) throws SQLException {
		ResultSet localSet = JDBC.getResultSet("accountstable");
		System.out.println("How much would you like to deposit?\n");
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
		
//		System.out.println("DEBUGGING (BankController:162): " + "In account type: " + inAccountType + " sw val: "
//				+ sw + "\nDB val: " + currBalance + " + input val: " + depAmt + " = total value "
//				+ currBalance);

		
		JDBC.update("accountstable", inAccountType, currBalance, inAccountNumber);

		// Allows user to exit using enter button. Might not need for this method
		// Possibly use timer instead
		System.out.println("Press any key to continue.");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		depositInput.close();
	}

	// Allows user to withdraw from an account
	public static void withdraw(int inAccountNumber, ResultSet inRs, String inAccountType) throws SQLException {
		ResultSet localSet = JDBC.getResultSet("accountstable");
		System.out.println("How much would you like to withdraw?\n");
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
			JDBC.update("accountstable", inAccountType, currBalance, inAccountNumber);
		}else {
			System.out.println("You do not have sufficient funds to make this tranaction, make another selection and try again");
		}

		// Allows user to exit using enter button. Might not need for this method
		// Possibly use timer instead
		System.out.println("Press any key to continue.");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

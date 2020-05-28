package com.bank;

import com.data.*;
import com.exception.BankException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the central controller and communicator between the employees,
 * users, and the database
 * 
 * @author Dave Wroblewski
 *
 */
public class EmployeeController {

	private ResultSet rs;
	private ArrayList<String[]> masterList;

	public EmployeeController() {
		
	}
	
	/**
	 * 
	 * @param inTable table in the database to be queried
	 * @throws BankException
	 */
	public EmployeeController(String inTable) throws BankException {
		setRs(JDBC.getResultSet(inTable));
		boolean loggedOut = false;
		do {
			System.out.println("What would you like to do?\n" + "1: Approve/Reject new accounts\n"
					+ "2: View all accounts\n" + "3: View log of daily transactions\n" + "9: Exit System");
			Scanner empInput = new Scanner(System.in);
			int nextInput = Integer.parseInt(empInput.next());
			boolean correctChoice = false;
			do {
				// Approve or deny a new account
				if (nextInput == 1) {
					setRs(JDBC.getResultSet("customerstable"));
					try {
						// This is where all the code to iterate through
						// the ResultSet and decide whether to approve or deny
						// the account based on system recommendations
						int points = 0;
//						int acctNum;
//						boolean goodStanding, isMarried, isHomOwner;
//						double annualSalary;
						while (rs.next()) {
							if (!rs.getBoolean(7)) {
								System.out.println("Account Number: " + rs.getInt(1) + ", " + "Good Standing: "
										+ rs.getBoolean(2) + ", " + "Occupation: " + rs.getString(3) + ", "
										+ "Annual Income: " + rs.getDouble(4) + ", " + "Married: " + rs.getBoolean(5)
										+ ", " + "Home Owner: " + rs.getBoolean(6));

								// Sets points based on criteria
								if (rs.getBoolean(2)) {
									points += 15;
									//System.out.println("Points for good standing");
								}
								if (rs.getDouble(4) > 15000.00 && rs.getDouble(4) <= 25000.00) {
									points += 5;
									//System.out.println("Points for low income");
								} else if (rs.getDouble(4) > 25000.00 && rs.getDouble(4) <= 50000.00) {
									points += 10;
									//System.out.println("Points for mid income");
								} else if (rs.getDouble(4) > 50000.00) {
									points += 20;
									//System.out.println("Points for high income");
								} else {
									// do nothing
								}
								if (rs.getBoolean(5)) {
									points += 10;
									//System.out.println("Points for being married");
								}
								if (rs.getBoolean(6)) {
									points += 15;
									//System.out.println("Points for for owning a home");
								}
								System.out.println("Points: " + points);

								if (points >= 20) {
									System.out.println(
											"This customer account is applicible for approval\n" + "Approve? Y/N");
									Scanner approvalInput = new Scanner(System.in);
									boolean corrChoice = false;
									do {
										String approvalInString = approvalInput.next();
										if (approvalInString.equalsIgnoreCase("Y")) {
											JDBC.update("customerstable", "acctApproved", true, rs.getInt(1));
											System.out.println("Account " + rs.getInt(1) + " has been approved.");
											corrChoice = true;
										} else if (approvalInString.equalsIgnoreCase("N")) {
											corrChoice = true;
										} else {
											System.out.println("Please enter a valid selection (Y/N)");
										}
									} while (!corrChoice);
								} else {
									System.out.println(
											"This customer account is not recommended for approval, please forward to manager.");
								}
								points = 0;
							}
						}
					} catch (SQLException e) {
						System.out.println("There was an error, please contact your system administrator.");
					}
					//approveDenyAccount();
					correctChoice = true;
					// View all accounts
				} else if (nextInput == 2) {
					try {
						this.masterList = returnAllAccounts();
						System.out.println(
								"=================================================================================================================================================================================================================================================================================================================================\n"
										+ "Social Security Number\t|\tFirst Name\t|\tLast Name\t|\tIs In Good Standing\t|\tOccupation\t\t|\tAnnualIncome\t|\tMarried\t|\tOwn Home\t|\tAccount is Active\t|\tAccount Number\t|  Savings Amount\t|  Checking Amount\t|\n"
										+ "=================================================================================================================================================================================================================================================================================================================================");
						for (int i = 0; i < masterList.size(); i++) {
							System.out.println(masterList.get(i)[0] + "\t\t|\t" + masterList.get(i)[1] + "\t\t|\t"
									+ masterList.get(i)[2] + "\t|\t" + masterList.get(i)[3] + "\t\t\t|\t"
									+ masterList.get(i)[4] + "\t|\t" + masterList.get(i)[5] + "\t|\t"
									+ masterList.get(i)[6] + "\t|\t" + masterList.get(i)[7] + "\t\t|\t"
									+ masterList.get(i)[8] + "\t\t\t|\t" + masterList.get(i)[9] + "\t\t|\t"
									+ masterList.get(i)[10] + "\t\t|\t" + masterList.get(i)[11] + "\t\t|");
							System.out.println(
									"__________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
						}

					} catch (SQLException e) {
						throw new BankException("There was an error with your query, contact system administrator.");
					}

					correctChoice = true;

					// View daily transactions logs
				} else if (nextInput == 3) {
					try {
						openLog();
					} catch (IOException e) {
						System.out.println("There was an error, contact system administrator");
					}
					correctChoice = true;
				} else if (nextInput == 9) {
					correctChoice = true;
					loggedOut = true;
				} else {
					System.out.println("You must choose a valid selection (1 - 3)! Try again.");
				}
				if (!correctChoice) {
					empInput.close();
				}

			} while (!correctChoice);

		} while (!loggedOut);
	}

	// Method call to approve or deny a new account
	// Makes call to JDBC to update the column in the specified row in the customer
	// database
	private void approveDenyAccount() {
		setRs(JDBC.getResultSet("customerstable"));
		try {
			// This is where all the code to iterate through
			// the ResultSet and decide whether to approve or deny
			// the account based on system recommendations
			int points = 0;
			while (rs.next()) {
				if (!rs.getBoolean(7)) {
					// Sets points based on criteria
					if (rs.getBoolean(2)) {
						points += 15;
					}
					if (rs.getDouble(4) > 15000.00 && rs.getDouble(4) <= 25000.00) {
						points += 5;
					} else if (rs.getDouble(4) > 25000.00 && rs.getDouble(4) <= 50000.00) {
						points += 10;
					} else if (rs.getDouble(4) > 50000.00) {
						points += 20;
					} else {
						// do nothing
					}
					if (rs.getBoolean(5)) {
						points += 10;
					}
					if (rs.getBoolean(6)) {
						points += 15;
					}

					if (points >= 20) {
						System.out.println(
								"This customer account is applicible for approval\n" + "Approve? Y/N");
						Scanner approvalInput = new Scanner(System.in);
						boolean corrChoice = false;
						do {
							String approvalInString = approvalInput.next();
							if (approvalInString.equalsIgnoreCase("Y")) {
								JDBC.update("customerstable", "acctApproved", true, rs.getInt(1));
								System.out.println("Account " + rs.getInt(1) + " has been approved.");
								corrChoice = true;
							} else if (approvalInString.equalsIgnoreCase("N")) {
								corrChoice = true;
							} else {
								System.out.println("Please enter a valid selection (Y/N)");
							}
						} while (!corrChoice);
					} else {
						System.out.println(
								"This customer account is not recommended for approval, please forward to manager.");
					}
					points = 0;
				}
			}
		} catch (SQLException e) {
			System.out.println("There was an error, please contact your system administrator.");
		}
	}

	/**
	 * Populates and returns an ArrayList<String> with all the 
	 * concatenated values from 'userstable', 'accountstable', and 'customerstable'
	 * @return The ArrayList<String[]> containing arrays of whole rows of customer account data
	 * @throws SQLException
	 */
	public ArrayList<String[]> returnAllAccounts() throws SQLException {
		ArrayList<String[]> listBuilder = new ArrayList<String[]>();

		
		ResultSet accountSet = JDBC.getResultSet("accountstable");
		ResultSet customerSet = JDBC.getResultSet("customerstable");
		ResultSet userSet = JDBC.getResultSet("userstable");

		// Iterates through the largest ResultSet and populates the ArrayList<String[]>
		// with string arrays
		while (customerSet.next()) {
			String[] customerRow = new String[12];
			if (userSet.next()) {
				customerRow[0] = userSet.getInt(1) + "";
				customerRow[1] = userSet.getString(2);
				customerRow[2] = userSet.getString(3);
			}
			customerRow[3] = customerSet.getBoolean(2) + "";
			customerRow[4] = customerSet.getString(3);
			customerRow[5] = customerSet.getDouble(4) + "";
			customerRow[6] = customerSet.getBoolean(5) + "";
			customerRow[7] = customerSet.getBoolean(6) + "";
			customerRow[8] = customerSet.getBoolean(7) + "";

			if (accountSet.next()) {
				customerRow[9] = accountSet.getInt(1) + "";
				customerRow[10] = accountSet.getDouble(3) + "";
				customerRow[11] = accountSet.getDouble(4) + "";
			}
			listBuilder.add(customerRow);
		}
		
		

		return listBuilder;
	}

	/**
	 * Opens the log of all account transactions
	 * @throws IOException
	 */
	private void openLog() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("Notepad.exe",
				"C:\\Users\\Dave\\Documents\\Revature\\Eclipse Files\\Week1\\bank-app\\target\\accountTransactionsLog.log");
		pb.start();
	}

	
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}

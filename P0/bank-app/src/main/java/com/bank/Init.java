package com.bank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.data.DBConnector;
import com.data.JDBC;

public class Init {

//	private static String password = System.getenv("TRAINING_DB_PASSWORD");
	
	private int accNo;
	private int pin;
	private int employeeID;
	
	public void logIn() {
		Scanner input = new Scanner(System.in);
	
		System.out.print("Enter your account number: ");
		this.accNo = input.nextInt();							//Retrieve account number
										//Retrieve pin number
		if(accNo == 0000) {
			employeeLogin();
		}else {
			customerLogin();
		}
		
	}


	private void employeeLogin() {
		Scanner empInput = new Scanner(System.in);
		
		//Gets the employee ID an pin number for login verification
		System.out.println("This is where the business logic for the employee login and controller will kick off!");
		System.out.println("Please enter your 6 digit employee ID: ");
		int idNum = Integer.parseInt(empInput.next());
		System.out.println("Enter your 4 digit employee passcode");
		int empPin = Integer.parseInt(empInput.next());
		//Instantiate a new EmployeeController object and set table to query
		EmployeeController ec = new EmployeeController("employeetable");
		
		
	}
	
	private void customerLogin() {
		Scanner custInput = new Scanner(System.in);
		System.out.println("Now enter your 4 digit pin: ");
		this.pin = custInput.nextInt();
		//Instantiate new bank controller and verify login information
		try {
			//Creates a new BankController for this users session
			BankController bc = new BankController(JDBC.getResultSet("accountstable"));
			try {
				System.out.println("I AM CREATING A NEW BANKCONTROLLER INSTANCE HERE!");
				bc.logIn(this.accNo, this.pin);
			} catch (InvalidAccountException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			
			//This allows the program to continue to loop if the user enters an incorrect input
			boolean correctSelection2 = true;
			while(!correctSelection2) {
				System.out.println("Invalid account/pin combination try again?");
				String retryEntry = custInput.next();
				if(retryEntry == "Y" || retryEntry == "y") {
					correctSelection2 = true;
				}else if(retryEntry =="N" || retryEntry == "n") {
					correctSelection2 = true;
				}else {
					System.out.println("Please enter a valid input!");
				}
			}
		}
	}
	
	public void createCustomerAccount() throws SQLException {
		boolean isInGoodStanding = true;
		boolean isEmployee = false;
		boolean married = false;
		boolean homeowner = false;
		boolean accountApproved = false;
		Scanner createAcctScanner = new Scanner(System.in);
		System.out.println("Are you an employee (Y/N)?");
		String emp = createAcctScanner.next();
		if(emp.equalsIgnoreCase("Y")) {
			//Ask for employee reference number and if correct create an employee account
			isEmployee = true;
		}
		System.out.print("Please enter your first name: ");
		String firstName = createAcctScanner.next();
		System.out.print("Now enter your last name: ");
		String lastName = createAcctScanner.next();
		System.out.println("Please enter your SSN: ");
		String ssn = createAcctScanner.next();
		System.out.println("What is your annual salary?");
		double annualSalary = Double.parseDouble(createAcctScanner.next());
		System.out.println("What is your occupation?");
		String occupation = createAcctScanner.next();
		
		//String occupation = createAcctScanner.next();
		boolean correctSelection = false;

		
		
		System.out.println("Occupation " + occupation);
		//Married Y/N
		do {
			System.out.println("Are you a married? (Y/N)");
			String isHomeowner = createAcctScanner.next();
			if(isHomeowner.equalsIgnoreCase("Y")) {
				
				correctSelection = true;
			}else if(isHomeowner.equalsIgnoreCase("N")) {
				
				correctSelection = true;
			}else {
				System.out.println("Please enter a valid selection (Y/N), try again.\n");
			}
		}while(!correctSelection);
		
		correctSelection = false;
		//Homeowner Y/N
		do {
			System.out.println("Are you a homeowner? (Y/N)");
			String isHomeowner = createAcctScanner.next();
			if(isHomeowner.equalsIgnoreCase("Y")) {
				
				correctSelection = true;
			}else if(isHomeowner.equalsIgnoreCase("N")) {
				
				correctSelection = true;
			}else {
				System.out.println("Please enter a valid selection (Y/N), try again.\n");
			}
		}while(!correctSelection);
		
		
		boolean pinMatch = false;
		boolean correctInput = false;
		int pinOne= 0;
		int pinTwo = 1;
		
		do {
			System.out.println("Please select a 4 digit pin: ");
			do {
				pinOne = Integer.parseInt(createAcctScanner.next());
				System.out.println("Please re-enter 4 digit pin: ");
				pinTwo = Integer.parseInt(createAcctScanner.next());
				String pinInput = pinOne + "";
				if(pinInput.matches("[0-9]{4}")) {
					correctInput = true;
				}else {
					System.out.println("Please enter a 4 digit numeric pin number only please");
				}
			}while(!correctInput);
			if(pinOne == pinTwo) {
				pinMatch = true;
			}else {
				System.out.println("You must enter the same pin to validate! Please try again");
			}
		}while(!pinMatch);
		
		boolean correctAmount = false;
		Double inputAmount = 0.00d;
		do {
			System.out.println("Enter the amount you are starting your account with.\n(Must be at least $1): ");
			inputAmount = Double.parseDouble(createAcctScanner.next());
			if(inputAmount >= 1.00d) {
				correctAmount = true;
			}else {
				System.out.println("You must deposit at least $1.00 into your account, please try again.");
			}
		}while(!correctAmount);
		
		Statement stmt = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery("SELECT accountNumber FROM accountstable");
		rs.last();
		int nextAccountNumber = rs.getInt(1);
		nextAccountNumber++;
		
		JDBC.insert("usersTable", "ssn, firstName, lastName, isEmployee",
				"'" + ssn + "', '" + firstName + "' ,'" + lastName + "', " + false );
		
		JDBC.insert("customerstable", "accountNumber, isInGoodStanding, occupation, annualIncome, isMarried, ownsHome, acctApproved, ssn",
				nextAccountNumber + ", " + isInGoodStanding + ", '" + occupation + "', " + annualSalary + ", " +
						married + ", " + homeowner + ", " + accountApproved + 
						", (SELECT ssn FROM userstable WHERE ssn = " + ssn + ")");
		
		JDBC.insert("accountstable", "accountNumber, pinNumber, savingsAmt, checkingAmt", "(SELECT accountNumber FROM customerstable WHERE accountNumber = "+ nextAccountNumber + "), " + pinOne +
				", " + inputAmount + ", " + 0);
	
		System.out.println("\nYou have succussfully created an account! Your account number is " + nextAccountNumber + " and your"
				+ " pin number is " + pinOne + ". Please retain for your records.");
		
		//After account creation log the user back in
		//logIn();
		System.out.println("Thank you for applying for an account with th First National Bank of Dave.\nWe will contact you shortly to let you know whether you are approved or not.\n");
	}
}

package com.bank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.data.DBConnector;
import com.data.JDBC;
import com.exception.BankException;

public class Init {

//	private static String password = System.getenv("TRAINING_DB_PASSWORD");
	
	private int accNo;
	private int pin;
	private int employeeID;
	
	/**
	 * Entry point from main method to log in dependent on whether 
	 * user is a customer or an employee
	 */
	public void logIn() {
		Scanner input = new Scanner(System.in);
	
		System.out.print("Enter your account number: ");
		this.accNo = input.nextInt();							//Retrieve account number
										//Retrieve pin number
		if(accNo == 0000) {
			try {
				employeeLogin();
			} catch (SQLException e) {
				System.out.println("There was an error, contact system administrator");
			}
		}else {
			customerLogin();
		}
		
	}


	/**
	 * Login method for Employee
	 * @throws SQLException
	 */
	private void employeeLogin() throws SQLException {
		Scanner empInput = new Scanner(System.in);
		
		//Gets the employee ID an pin number for login verification
		System.out.println("This is where the business logic for the employee login and controller will kick off!");
		
		//Instantiate a new EmployeeController object and set table to query
		try {
			boolean correctCredentials = false;
			do {
				Statement stmt = DBConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet et = stmt.executeQuery("SELECT * FROM employeetable");
				System.out.println("Please enter your 6 digit employee ID: ");
				int idNum = Integer.parseInt(empInput.next());
				System.out.println("Enter your 4 digit employee passcode");
				int empPin = Integer.parseInt(empInput.next());
				while(et.next()) {
					if(et.getInt(1) == idNum && et.getInt(2) == empPin) {
						System.out.println("I am logged in!!!");
						EmployeeController ec = new EmployeeController("employeetable");
						correctCredentials = true;
					}else {
						System.out.println("You have entered invalid credentials, try again.");
					}
				}
			}while(!correctCredentials);
		} catch (BankException e) {
			System.out.println("There was an error, contact system administrator");
		}
	}
	
	/**
	 * Method for Customer login
	 */
	private void customerLogin() {
		Scanner custInput = new Scanner(System.in);
		System.out.println("Now enter your 4 digit pin: ");
		this.pin = custInput.nextInt();
		//Instantiate new bank controller and verify login information
		try {
			//Creates a new BankController for this users session
			BankController bc = new BankController(JDBC.getResultSet("accountstable"));
			try {
				bc.logIn(this.accNo, this.pin);
			} catch (InvalidAccountException e) {
				System.out.println("There was an error, contact system administrator");
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
	
	/**
	 * Allows a customer to apply for a new account
	 * @throws SQLException
	 */
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
		//Home owner Y/N
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
	
		System.out.println("\nYour application has been received! Your account number is " + nextAccountNumber + " and your"
				+ " pin number is " + pinOne + " pending on approval.\nPlease retain for your records.");
		
	
		System.out.println("Thank you for applying for an account with The First National Bank of Dave.\nWe will contact you shortly to let you know whether you are approved or not.\n");
	}
	
	
	public Customer getCustomer(int inAccountNumber) throws SQLException {
		
		ResultSet cSet = JDBC.getResultSet("customerstable");
		ResultSet uSet = JDBC.getResultSet("userstable");
		Customer cust = new Customer();
		
		while(cSet.next()) {
			if(cSet.getInt(1) == inAccountNumber) {
				cust.setAccountNumber(cSet.getInt(1));
				cust.setInGoodStanding(cSet.getBoolean(2));
				cust.setOccupation(cSet.getString(3));
				cust.setAnnualIncome(cSet.getDouble(4));
				cust.setMarried(cSet.getBoolean(5));
				cust.setOwnsHome(cSet.getBoolean(6));
				cust.setSsn(cSet.getInt(8));
			}
		}
		
		while(uSet.next()) {
			if(uSet.getInt(1) == cust.getSsn()) {
				cust.setFirstNme(uSet.getString(2));
				cust.setLastName(uSet.getString(3));
			}
		}
		
		return cust;
	}
	
	public Employee getEmployee(int inIdNum) throws SQLException {
		ResultSet set = JDBC.getResultSet("employeetable");
		Employee emp = new Employee();
		
		while(set.next()) {
			if(set.getInt(1) == inIdNum) {
				emp.setIdNum(set.getInt(1));
				emp.setEmpPin(set.getInt(2));
				emp.setPosition(set.getString(3));
			}
		}
		
		return emp;
		
	}
	
	public Account getAccount(int inAccountNumber) throws SQLException {
		ResultSet set = JDBC.getResultSet("accountstable");
		Account acct = new Account();
		
		while(set.next()) {
			if(set.getInt(1) == inAccountNumber) {
				acct.setAccountNumber(set.getInt(1));
				acct.setPinNumber(set.getInt(2));
				acct.setSavingsAmount(set.getDouble(3));
				acct.setCheckingAmount(set.getDouble(4));
			}
		}
		
		return acct;
		
	}
}

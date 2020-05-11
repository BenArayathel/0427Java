package com.bank;

import com.data.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the central controller and communicator between the employees,
 * users, and the database
 * @author Dave Wroblewski
 *
 */
public class EmployeeController {

	private ResultSet rs;
	private ArrayList<Employee> employeeList;
	private ArrayList<String> masterList;
	
	public EmployeeController(String inTable) {
		rs = JDBC.getResultSet(inTable);
		employeeList = new ArrayList<Employee>();
//		try {
//			while(rs.next()) {
////				Employee emp = new Employee();
////				emp.setIdNum(rs.getInt(1));
////				emp.setEmpPin(rs.getInt(2));
////				emp.setPosition(rs.getString(3));
////				emp.setSsn(rs.getInt(4));
////				employeeList.add(emp);
////				System.out.println(emp.toString());
//				emp = null;
//			}
			//System.out.println("ArrayList size is " + employeeList.size());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("What would you like to do?\n"
				+ "1: Approve/Reject new accounts\n"
				+ "2: View all accounts\n"
				+ "3: View log of daily transactions\n");
		Scanner empInput = new Scanner(System.in);
		int nextInput = Integer.parseInt(empInput.next());
		boolean correctChoice = false;
		do {
			//Approve or deny a new account
			if(nextInput == 1) {
				approveDenyAccount();
				correctChoice = true;
			//View all accounts
			}else if(nextInput == 2) {
				try {
					//System.out.println("Number of rows before: " + JDBC.getRowCount("accountstable"));
					this.masterList = returnAllAccounts();
					System.out.println("=================================================================================================================================================================================================================================================================================================================================\n"
							+ "Social Security Number\t|\tFirst Name\t|\tLast Name\t|\tIs In Good Standing\t|\tOccupation\t\t|\tAnnualIncome\t|\tMarried\t|\tOwn Home\t|\tAccount is Active\t|\tAccount Number\t|  Savings Amount\t|  Checking Amount\t|\n"
							+ "=================================================================================================================================================================================================================================================================================================================================");
					for (int i = 0; i < JDBC.getRowCount("accountstable"); i++) {
						System.out.println(masterList.get(0) + "\t\t|\t" + masterList.get(1) + "\t\t|\t" +
								masterList.get(2) + "\t|\t" + masterList.get(3) + "\t\t\t|\t" + 
								masterList.get(4) + "\t|\t" + masterList.get(5) + "\t|\t" + 
								masterList.get(6) + "\t|\t" + masterList.get(7) + "\t\t|\t" + 
								masterList.get(8) + "\t\t\t|\t" + masterList.get(9) + "\t\t|\t" + 
								masterList.get(10) + "\t\t|\t" + masterList.get(11) + "\t\t|");
						System.out.println("=================================================================================================================================================================================================================================================================================================================================");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				correctChoice = true;
				
			//View daily transactions logs
			}else if(nextInput == 3) {
				
				correctChoice = true;
			}else {
				System.out.println("You must choose a valid selection (1 - 3)! Try again.");
			}
			
		}while(!correctChoice);
	}
	
	
	
	//Method call to approve or deny a new account
	//Makes call to JDBC to update the column in the specified row in the customer database
	private void approveDenyAccount() {
		
	}
	
	//Populates and returns an ArrayList<String> with all the concatenated values
	//from 'userstable', 'accountstable', and 'customerstable'
	private ArrayList<String> returnAllAccounts() throws SQLException {
		ArrayList<String> listBuilder = new ArrayList<String>();
		ResultSet userSet = JDBC.getResultSet("userstable");
		ResultSet accountSet = JDBC.getResultSet("accountstable");
		ResultSet customerSet = JDBC.getResultSet("customerstable");
		int rowCount = 0;

		
		while(userSet.next()){
				listBuilder.add(userSet.getInt(1) + "");
				listBuilder.add(userSet.getString(2));
				listBuilder.add(userSet.getString(3));
//				System.out.println("User SSN: " + userSet.getInt(1));
//				System.out.println("First Name: " + userSet.getString(2));
//				System.out.println("Last Name: " + userSet.getString(3));
		}
		userSet.close();
		while(customerSet.next()) {
			listBuilder.add(customerSet.getBoolean(2) + "");
			listBuilder.add(customerSet.getString(3));
			listBuilder.add(customerSet.getDouble(4) + "");
			listBuilder.add(customerSet.getBoolean(5) + "");
			listBuilder.add(customerSet.getBoolean(6) + "");
			listBuilder.add(customerSet.getBoolean(7) + "");
//				System.out.println("The customer is in good standing with our institution: " + customerSet.getBoolean(2));
//				System.out.println("Occupation: " + customerSet.getString(3));
//				System.out.println("Annual Salary: " + customerSet.getDouble(4));
//				System.out.println("The customer is married: " + customerSet.getBoolean(5));
//				System.out.println("The customer owns a home: " + customerSet.getBoolean(6));
//				System.out.println("Account has been approved by a bank manager: " + customerSet.getBoolean(7));
		}
		customerSet.close();
		while(accountSet.next()) {
			listBuilder.add(accountSet.getInt(1) + "");
			listBuilder.add(accountSet.getDouble(3) + "");
			listBuilder.add(accountSet.getDouble(4) + "");
//				System.out.println("Account Number: " + accountSet.getInt(1));
//				System.out.println("Savings account balance: " + accountSet.getDouble(3));
//				System.out.println("Checking account balance: " + accountSet.getDouble(4));
		}
		accountSet.close();
		System.out.println("I m returning an array list");
		return listBuilder;
	}



	public ArrayList<String> getMasterList() {
		return masterList;
	}



	public void setMasterList(ArrayList<String> masterList) {
		this.masterList = masterList;
	}
	
	
	
	
	
}

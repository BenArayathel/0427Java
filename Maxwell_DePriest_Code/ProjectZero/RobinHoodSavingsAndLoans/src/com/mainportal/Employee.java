package com.mainportal;

import java.awt.List;
import java.util.ArrayList;


public class Employee extends User {
	
	private ArrayList<Account> customerAccounts = new ArrayList<>();

	public Employee() {

	}

	public Employee(String name, String email, String phoneNumber, String password, Account account) {
		super(name, email, phoneNumber, password, account);
		this.setStatus("employee");
		
		this.customerAccounts.add(new Account(12345, 56789, 1000.00, 3000.00, "nick@email.com"));
		this.customerAccounts.add(new Account(11111, 22222, 2000.00, 5000.00, "bob@email.com"));
		this.customerAccounts.add(new Account(44444, 55555, 1500.00, 8000.00, "tom@email.com"));
	}
	
	// public void updateRecord
	
	
	public ArrayList<Account> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(ArrayList<Account> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}
	
	
//	public ArrayList<Account> viewAllRecords() {
//		ArrayList<Account> allRecords = customerAccounts;
//		return allRecords;
//	}
	
	public void activateCustomerAccounts(ArrayList<Account> allRecords) {
		
		// for (Customer cust : allCustomers) {cust.getAccount.getActive = true
		for(Account record : allRecords) {
			if(!record.getActive()) {
				record.setActive(true);
			}
			
		}// end for loop
		
		// public void updateRecords(allRecords)
		
		for(Account rec : allRecords) {
			System.out.println(rec.toString());
		}
		
		

	}// end activateUserAccounts()

}// end class


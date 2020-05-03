package com.mainportal;

import java.util.Set;
import java.util.TreeSet;

public class Employee extends User {
	
	private Set<Account> customerAccounts = new TreeSet<>();

	public Employee() {

	}

	public Employee(String name, String email, String phoneNumber, String password, Account account) {
		super(name, email, phoneNumber, password, account);
	}
	
	// public void retrievePendingAccounts
	// public void activateAccount
	// public void updateRecord
	
	//public void viewAllRecords
	
	
	public Set<Account> getCustomerAccounts() {
		return customerAccounts;
	}

}

package com.mainportal;

import java.util.ArrayList;
import java.io.Serializable;


public class Employee extends User {
	
	private ArrayList<Customer> customerInfo = new ArrayList<>();
	private static final long serialVersionUID = 620321232225377344L;

	public Employee() {

	}

	public Employee(String name, String email, String phoneNumber, String password, Account account) {
		super(name, email, phoneNumber, password, account);
		this.setStatus("employee");
	}
	
	
	public ArrayList<Customer> getCustomerInfo() {
		return Lobby.readCustomers("./customerRecords");
	}

	public void setCustomerInfo(ArrayList<Customer> customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	public void updateCustomerInfo(ArrayList<Customer> newCustomerEdits) {
		Lobby.writeCustomers("./customerRecords", newCustomerEdits);
	}
	
	public void activateCustomerAccounts(ArrayList<Customer> allRecords) {

		for(Customer record : allRecords) {
			if(!record.getAccount().getActive()) {
				record.getAccount().setActive(true);
			}
			updateCustomerInfo(allRecords);
			
		}// end for loop
		
		

	}// end activateUserAccounts()

}// end class


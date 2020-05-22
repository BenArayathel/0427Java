package com.bankofben.models;

import java.util.Date;

import com.bankofben.exceptions.BusinessException;

public class Customer extends User {
	
	private final String id;
	private boolean applicationPending;

	public Customer() {
		super();
		this.id = null;
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String middleName, String lastName, String momsMaidenName, Date dob,
			long ssn, String email, long phoneNumber, String username, String password, String id, 
			boolean applicationPending) throws BusinessException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
		this.applicationPending = applicationPending;
		this.id = id;
	}

	public boolean isApplicationPending() {
		return applicationPending;
	}

	public void setApplicationPending(boolean applicationPending, Employee employee) {
		this.applicationPending = applicationPending;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", applicationPending=" + applicationPending + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", momsMaidenName=" + momsMaidenName
				+ ", dob=" + dob + ", ssn=" + ssn + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	

}

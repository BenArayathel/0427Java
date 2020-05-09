package com.bankofben.models;

import java.util.Date;

import com.bankofben.exceptions.BusinessException;

public class Employee extends User {
	
	private final String id;
	private String designation;
	private boolean canHire;

	public Employee() {
		super();
		this.id = null;
	}

	public Employee(String firstName, String middleName, String lastName, String momsMaidenName, Date dob,
			long ssn, String email, long phoneNumber, String username, String password, String id,
			String designation, boolean canHire) throws BusinessException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
		this.designation = designation;
		this.canHire = canHire;
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}
	
	// An employee's designation is set on the database side
	
	public String getId() {
		return id;
	}

	public boolean canHire() {
		return canHire;
	}
	
	//The ability to hire is set on the database side

	@Override
	public String toString() {
		return "Employee [id=" + id + ", designation=" + designation + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", momsMaidenName=" + momsMaidenName + ", dob=" + dob
				+ ", ssn=" + ssn + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
}

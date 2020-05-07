package com.bankofben.models;

import java.time.LocalDate;

import com.bankofben.exceptions.BusinessException;

public class Employee extends User {
	
	private String designation;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Employee() {
		super();
	}

	public Employee(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob,
			String ssn, String email, String phoneNumber, String username, String password, String designation)
			throws BusinessException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
		setDesignation(designation);
	}

}

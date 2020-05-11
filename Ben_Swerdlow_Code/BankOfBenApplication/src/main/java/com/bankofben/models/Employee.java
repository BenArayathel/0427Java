package com.bankofben.models;

import java.util.Date;

import com.bankofben.exceptions.BusinessException;

public class Employee extends User {
	
	private final String id;
	private String designation;
	private String supervisorEmployeeId;
	private boolean canHire;

	public Employee() {
		super();
		this.id = null;
	}

	public Employee(String firstName, String middleName, String lastName, String momsMaidenName, Date dob,
			long ssn, String email, long phoneNumber, String username, String password, String id,
			String designation, String supervisorEmployeeId, boolean canHire) throws BusinessException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
//		System.out.println(designation);
		this.designation = designation;
//		System.out.println(supervisorEmployeeId);
		this.supervisorEmployeeId = supervisorEmployeeId;
//		System.out.println(canHire);
		this.canHire = canHire;
//		System.out.println(id);
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getId() {
		return id;
	}

	public boolean canHire() {
		return canHire;
	}

	public String getSupervisorEmployeeId() {
		return supervisorEmployeeId;
	}

	public void setSupervisorEmployeeId(String supervisorEmployeeId) {
		this.supervisorEmployeeId = supervisorEmployeeId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", designation=" + designation + ", supervisorEmployeeId=" + supervisorEmployeeId
				+ ", canHire=" + canHire + "]";
	}
	
	//The ability to hire is set on the database side
	
	
	
}

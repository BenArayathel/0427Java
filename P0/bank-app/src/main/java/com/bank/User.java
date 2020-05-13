package com.bank;


public class User {

	private transient int ssn;
	private String firstName;
	private String lastName;
	private boolean isEmployee;
	
	public User(){
		
	}
	
	/**
	 * 
	 * @param ssn 
	 * @param nfirstNme
	 * @param lastName
	 * @param isEmployee
	 */
	public User(int ssn, String nfirstNme, String lastName, boolean isEmployee) {
		super();
		this.ssn = ssn;
		this.firstName = nfirstNme;
		this.lastName = lastName;
		this.isEmployee = isEmployee;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getFirstNme() {
		return firstName;
	}

	public void setFirstNme(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	@Override
	public String toString() {
		return "User [ssn=" + ssn + ", nfirstNme=" + firstName + ", lastName=" + lastName + ", isEmployee=" + isEmployee
				+ "]";
	}
	
}

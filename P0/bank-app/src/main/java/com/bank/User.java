package com.bank;


public class User {

	private long ssn;
	private String nfirstNme;
	private String lastName;
	private boolean isEmployee;
	
	public User() {
		
	}
	
	public User(long ssn, String nfirstNme, String lastName, boolean isEmployee) {
		super();
		this.ssn = ssn;
		this.nfirstNme = nfirstNme;
		this.lastName = lastName;
		this.isEmployee = isEmployee;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public String getNfirstNme() {
		return nfirstNme;
	}

	public void setNfirstNme(String nfirstNme) {
		this.nfirstNme = nfirstNme;
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
		return "User [ssn=" + ssn + ", nfirstNme=" + nfirstNme + ", lastName=" + lastName + ", isEmployee=" + isEmployee
				+ "]";
	}
	
}

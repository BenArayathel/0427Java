package com.bank;

public class Customer extends User{

	private int accountNumber;
	private boolean isInGoodStanding;
	private String occupation;
	private int annualIncome;
	private boolean isMarried;
	private boolean ownsHome;
	private long ssn;
	
	public Customer(int inAccountNumber) {
		super();
		this.accountNumber = inAccountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean isInGoodStanding() {
		return isInGoodStanding;
	}

	public void setInGoodStanding(boolean isInGoodStanding) {
		this.isInGoodStanding = isInGoodStanding;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public boolean isOwnsHome() {
		return ownsHome;
	}

	public void setOwnsHome(boolean ownsHome) {
		this.ownsHome = ownsHome;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "Customer [accountNumber=" + accountNumber + ", isInGoodStanding=" + isInGoodStanding + ", occupation="
				+ occupation + ", annualIncome=" + annualIncome + ", isMarried=" + isMarried + ", ownsHome=" + ownsHome
				+ ", ssn=" + ssn + "]";
	}
}

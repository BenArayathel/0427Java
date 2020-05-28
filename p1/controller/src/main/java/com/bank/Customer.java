package com.bank;

public class Customer extends User{

	private int accountNumber;
	private boolean isInGoodStanding;
	private String occupation;
	private double annualIncome;
	private boolean isMarried;
	private boolean ownsHome;
	private transient int ssn;

	public Customer() {
		super();
	}
	
	/**
	 * Single parameter constructor
	 * @param inAccountNum the number of the account we are currently accessing
	 */
	public Customer(int inAccountNum) {
		super();
		this.accountNumber = inAccountNum;
	}
	
	/**
	 * 
	 * @param accountNumber The number of the account currently being accessed
	 * @param isInGoodStanding is the customer in good standing with the bank
	 * @param occupation the customers occupation
	 * @param annualIncome the customers annual salary
	 * @param isMarried is the customer single or married
	 * @param ownsHome does the customer own their home
	 * @param ssn the customers social security number
	 */
	public Customer(int accountNumber, boolean isInGoodStanding, String occupation, double annualIncome, boolean isMarried,
			boolean ownsHome, int ssn) {
		super(ssn, occupation, occupation, ownsHome);
		this.accountNumber = accountNumber;
		this.isInGoodStanding = isInGoodStanding;
		this.occupation = occupation;
		this.annualIncome = annualIncome;
		this.isMarried = isMarried;
		this.ownsHome = ownsHome;
		this.ssn = ssn;
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

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double d) {
		this.annualIncome = d;
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

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	

	@Override
	public String toString() {
		return "Customer [accountNumber=" + accountNumber + ", isInGoodStanding=" + isInGoodStanding + ", occupation="
				+ occupation + ", annualIncome=" + annualIncome + ", isMarried=" + isMarried + ", ownsHome=" + ownsHome
				+ ", ssn=" + ssn + "]";
	}
}

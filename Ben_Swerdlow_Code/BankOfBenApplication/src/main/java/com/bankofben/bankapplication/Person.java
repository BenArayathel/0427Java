package com.bankofben.bankapplication;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person {
	
	private String firstName;
	private String middleName;
	private String LastName;
	private String momsMaidenName;
	private LocalDate dob;
	private String ssn;
//	private String stateId;
//	private String streetAddress;
//	private String suiteAptOther;
//	private String zipCode;
	private String email;
	private String phoneNumber;
	
	public Person() {
		super();
	}
	
	public Person(Scanner sc) {
		super();
		
		System.out.println("Please input your first name:");
		String firstName = sc.nextLine();
		this.firstName = firstName;
		
		System.out.println("Please input your middle name:");
		String middleName = sc.nextLine();
		this.middleName = middleName;
		
		System.out.println("Please input your last name:");
		String lastName = sc.nextLine();
		this.LastName = lastName;
		
		System.out.println("Please input your mother's maiden name:");
		String momsMaidenName = sc.nextLine();
		this.momsMaidenName = momsMaidenName;
		
		boolean noDate = true;
		while (noDate) {
			System.out.println("Please input your date of birth in the following format: DD-MM-YYYY");
			String dmyDob = sc.nextLine();
			StringBuilder ymdDob = new StringBuilder();
			for (int i=0; i<3; i++) {
				ymdDob.append(dmyDob.split("-")[2-i]);
			}
			try {
				LocalDate dob = LocalDate.parse(ymdDob);
				setDob(dob);
				noDate = false;
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date entry "+dmyDob);
			}
		}
		
		boolean noSsn = true;
		while (noSsn) {
			System.out.println("Please input your social security number: (XXX-XX-XXXX)");
			String ssn = sc.nextLine();
			try {
				setSsn(ssn);
				noSsn = false;
			} catch (InvalidSsnException e) {
				System.out.println("Invalid social security number entry "+ssn);
			}
		}
//		this.stateId = stateId;
//		this.streetAddress = streetAddress;
//		this.suiteAptOther = suiteAptOther;
//		this.zipCode = zipCode;
		
		boolean noEmail = true;
		while (noEmail) {
			System.out.println("Please input your email:");
			String email = sc.nextLine();
			try {
				setEmail(email);
				noEmail = false;
			} catch (EmailInvalidException e) {
				System.out.println("Invalid email entry "+email);
			}
		}
		
		boolean noPhoneNumber = true;
		while (noPhoneNumber) {
			System.out.println("Please input your phone number:");
			String phoneNumber = sc.nextLine();
			try {
				setPhoneNumber(phoneNumber);
				noPhoneNumber = false;
			} catch (InvalidPhoneNumberException e) {
				System.out.println("Invalid phone number entry "+phoneNumber);
			}
		}
	}

	public Person(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob,
			String ssn, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		LastName = lastName;
		this.momsMaidenName = momsMaidenName;
		this.dob = dob;
		this.ssn = ssn;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getMomsMaidenName() {
		return momsMaidenName;
	}
	
	public void setMomsMaidenName(String momsMaidenName) {
		this.momsMaidenName = momsMaidenName;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setSsn(String ssn) throws InvalidSsnException {
		if (UserUtils.isValidSsn(ssn)) {
			this.ssn = ssn;
		} else {
			throw new InvalidSsnException();
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws EmailInvalidException {
		if (UserUtils.isValidEmail(email)) {
			this.email = email;
		} else {
			throw new EmailInvalidException();
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		if (UserUtils.isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidPhoneNumberException();
		}
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

}

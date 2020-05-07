package com.bankofben.models;

import java.time.LocalDate;
//import java.time.Period;
//import java.util.List;
//import java.util.ArrayList;
//import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

import com.bankofben.business.BusinessException;
import com.bankofben.presentation.UserInterface;
import com.bankofben.presentation.ValidationTools;

public class Person {
	
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected String momsMaidenName;
	protected LocalDate dob;
	protected String ssn;
//	private String stateId;
//	private String streetAddress;
//	private String suiteAptOther;
//	private String zipCode;
	protected String email;
	protected String phoneNumber;
	
	public Person() {
		super();
	}
	
//	public Person(Scanner sc) {
//		super();
//		
//		System.out.println("Please input your first name:");
//		String firstName = sc.nextLine();
//		this.firstName = firstName;
//		
//		System.out.println("Please input your middle name:");
//		String middleName = sc.nextLine();
//		this.middleName = middleName;
//		
//		System.out.println("Please input your last name:");
//		String lastName = sc.nextLine();
//		this.LastName = lastName;
//		
//		System.out.println("Please input your mother's maiden name:");
//		String momsMaidenName = sc.nextLine();
//		this.momsMaidenName = momsMaidenName;
//		
//		boolean noDate = true;
//		while (noDate) {
//			System.out.println("Please input your date of birth in the following format: DD-MM-YYYY");
//			String dmyDob = sc.nextLine();
//			StringBuilder ymdDob = new StringBuilder();
//			for (int i=0; i<3; i++) {
//				ymdDob.append(dmyDob.split("-")[2-i]);
//			}
//			try {
//				LocalDate dob = LocalDate.parse(ymdDob);
//				setDob(dob);
//				noDate = false;
//			} catch (DateTimeParseException e) {
//				System.out.println("Invalid date entry "+dmyDob);
//			}
//		}
//		
//		boolean noSsn = true;
//		while (noSsn) {
//			System.out.println("Please input your social security number: (XXX-XX-XXXX)");
//			String ssn = sc.nextLine();
//			try {
//				setSsn(ssn);
//				noSsn = false;
//			} catch (InvalidSsnException e) {
//				System.out.println("Invalid social security number entry "+ssn);
//			}
//		}
////		this.stateId = stateId;
////		this.streetAddress = streetAddress;
////		this.suiteAptOther = suiteAptOther;
////		this.zipCode = zipCode;
//		
//		boolean noEmail = true;
//		while (noEmail) {
//			System.out.println("Please input your email:");
//			String email = sc.nextLine();
//			try {
//				setEmail(email);
//				noEmail = false;
//			} catch (EmailInvalidException e) {
//				System.out.println("Invalid email entry "+email);
//			}
//		}
//		
//		boolean noPhoneNumber = true;
//		while (noPhoneNumber) {
//			System.out.println("Please input your phone number:");
//			String phoneNumber = sc.nextLine();
//			try {
//				setPhoneNumber(phoneNumber);
//				noPhoneNumber = false;
//			} catch (InvalidPhoneNumberException e) {
//				System.out.println("Invalid phone number entry "+phoneNumber);
//			}
//		}
//	}

	public Person(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob,
			String ssn, String email, String phoneNumber) throws BusinessException { 
//					throws BlankFieldException, InvalidDateOfBirthException,
//			InvalidSsnException, InvalidEmailException, InvalidPhoneNumberException {
		super();
		
//		List<String> blankFieldMessages = new ArrayList<>();
		setFirstName(firstName);
		this.middleName = middleName;
		setLastName(lastName);
		setMomsMaidenName(momsMaidenName);
		
		setDob(dob);
		
		setSsn(ssn);
		
		setEmail(email);
		
		setPhoneNumber(phoneNumber);
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) throws BusinessException {
		if (firstName==null) {
			throw new BusinessException("No entry for first name detected. A first name must be provided.");
		} else {
			char[] firstNameArray = firstName.toCharArray();
			firstNameArray[0] = Character.toUpperCase(firstNameArray[0]);
			this.firstName = String.valueOf(firstNameArray);
		}
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		char[] middleNameArray = middleName.toCharArray();
		middleNameArray[0] = Character.toUpperCase(middleNameArray[0]);
		this.middleName = String.valueOf(middleNameArray);
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) throws BusinessException {
		if (lastName==null) {
			throw new BusinessException("No entry for last name detected. A last name must be provided.");
		} else {
			char[] lastNameArray = lastName.toCharArray();
			lastNameArray[0] = Character.toUpperCase(lastNameArray[0]);
			this.lastName = String.valueOf(lastNameArray);
		}
	}
	
	public String getMomsMaidenName() {
		return momsMaidenName;
	}
	
	public void setMomsMaidenName(String momsMaidenName) throws BusinessException {
		if (momsMaidenName==null) {
			throw new BusinessException("Mother's maiden name must be provided.");
		} else {
			char[] momsMaidenNameArray = momsMaidenName.toCharArray();
			momsMaidenNameArray[0] = Character.toUpperCase(momsMaidenNameArray[0]);
			this.momsMaidenName = String.valueOf(momsMaidenNameArray);
		}
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setSsn(String ssn) throws BusinessException {
		if (ssn==null) {
			throw new BusinessException("No entry detected for social security number. A social security number must be provided.");
		} 
		if (ValidationTools.isValidSsn(ssn)) {
			this.ssn = ssn;
		} else {
			throw new BusinessException("Invalid social security number.\n"+UserInterface.ssnCriteria());
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws BusinessException {
		if (email==null) {
			throw new BusinessException("No entry detected for email. An email must be provided.");
		}
		if (ValidationTools.isValidEmail(email)) {
			this.email = email;
		} else {
			throw new BusinessException("The entry provided is not a valid email address.");
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) throws BusinessException {
		if (phoneNumber == null) {
			throw new BusinessException("No entry detected for phone number. A phone number must be provided.");
		}
		if (ValidationTools.isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new BusinessException("Invalid phone number.\n"+UserInterface.phoneNumberCriteria());
		}
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) throws BusinessException {
		if (dob==null) {
			throw new BusinessException("No entry for date of birth detected. A date of birth must be provided.");
		} else if (dob.isAfter(LocalDate.now())) {
			throw new BusinessException("Invalid date of birth. Dates of birth cannot occur after the present date.");
		} else {
			this.dob = dob;
		}
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", momsMaidenName=" + momsMaidenName + ", dob=" + dob + ", ssn=" + ssn + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((momsMaidenName == null) ? 0 : momsMaidenName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (momsMaidenName == null) {
			if (other.momsMaidenName != null)
				return false;
		} else if (!momsMaidenName.equals(other.momsMaidenName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		return true;
	}

}

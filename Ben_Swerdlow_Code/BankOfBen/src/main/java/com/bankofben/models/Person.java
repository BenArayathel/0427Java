package com.bankofben.models;

import java.util.Date;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.validators.ValidationTools;

public class Person {
	
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected String momsMaidenName;
	protected Date dob;
	protected long ssn;
	protected String email;
	protected long phoneNumber;
	
	public Person() {
		super();
	}

	public Person(String firstName, String middleName, String lastName, String momsMaidenName, Date dob,
			long ssn, String email, long phoneNumber) throws BusinessException {
		super();
	
		setFirstName(firstName);
		setMiddleName(middleName);
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
		if (middleName!=null && !middleName.isEmpty()) {
			char[] middleNameArray = middleName.toCharArray();
			middleNameArray[0] = Character.toUpperCase(middleNameArray[0]);
			this.middleName = String.valueOf(middleNameArray);
		}
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
	
	public Long getSsn() {
		return ssn;
	}
	
	public void setSsn(long ssn) throws BusinessException {
		if (ssn==0) {
			throw new BusinessException("No entry detected for social security number. A social security number must be provided.");
		} 
		if (ValidationTools.isValidSsn(Long.toString(ssn))) {
			this.ssn = ssn;
		} else {
			throw new BusinessException("Invalid social security number.\n");
		}
	}
	
	public void setSsn(String ssn) throws BusinessException {
		if (ssn==null) {
			throw new BusinessException("No entry detected for social security number. A social security number must be provided.");
		} 
		if (ValidationTools.isValidSsn(ssn)) {
			try {
				this.ssn = Long.parseLong(ssn.replace("-","").replace(" ", ""));
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid social security number.\n");
			}
		} else {
			throw new BusinessException("Invalid social security number.\n");
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
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) throws BusinessException {
		if (phoneNumber == 0) {
			throw new BusinessException("No entry detected for phone number. A phone number must be provided.");
		}
		if (ValidationTools.isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new BusinessException("Invalid phone number.\n");
		}
	}
	
	public void setPhoneNumber(String phoneNumber) throws BusinessException {
		if (phoneNumber == null) {
			throw new BusinessException("No entry detected for phone number. A phone number must be provided.");
		}
		if (ValidationTools.isValidPhoneNumber(phoneNumber)) {
			try {
				this.phoneNumber = Long.parseLong(phoneNumber);
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid phone number.\n");
			}
		} else {
			throw new BusinessException("Invalid phone number.\n");
		}
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) throws BusinessException {
		if (dob==null) {
			throw new BusinessException("No entry for date of birth detected. A date of birth must be provided.");
		} else if (dob.after(new Date())) {
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
		result = prime * result + (int) (ssn ^ (ssn >>> 32));
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
		if (ssn != other.ssn)
			return false;
		return true;
	}

}

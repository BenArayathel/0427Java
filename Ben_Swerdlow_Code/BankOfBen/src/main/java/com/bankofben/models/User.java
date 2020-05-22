package com.bankofben.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
//import java.util.HashSet;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.validators.ValidationTools;

public class User extends Person implements Comparable<User> {

	private String username;
	private String password;
//	// For now, we will use counter to generate unique ids
//	// This will change once we do database stuff
//	private static Integer counter = 0;

	public User() {
		super();
	}

	public User(String firstName, String middleName, String lastName, String momsMaidenName, Date dob, long ssn,
			String email, long phoneNumber, String username, String password) throws BusinessException { 
//			throws BlankFieldException,
//			InvalidDateOfBirthException, InvalidSsnException, InvalidEmailException, InvalidPhoneNumberException,
//			InvalidUsernameException, InvalidPasswordException, InvalidPasswordChangeException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber);
		setUsername(username);
		setPassword(password);
	}
	
	public boolean passwordMatch(String password) {
		return password.equals(this.password);
	}
	
	@Override
	public void setDob(Date dob) throws BusinessException {
		super.setDob(dob);
		LocalDate localDateDob = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (Period.between(localDateDob, LocalDate.now()).getYears() < 18) {
			this.dob = null;
			throw new BusinessException("While the Bank of Ben appreciate young people's interest in financial responsibility, "
					+ "you must be at least 18 years old to be a customer or employee of the Bank of Ben. We look forward to "
					+ "your business with BoB once you are of age.");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws BusinessException {
		if (ValidationTools.isValidUsername(username)) {
			this.username = username;
		} else {
			throw new BusinessException("Invalid username. Username must be between 4 and 20 characters.");
		}
	}
	
	public String getPassword() {
		// TODO figure out security issue of having a getPassword method
		// Right now it's necessary for creating a user
		return this.password;
	}
	
	private void setPassword(String password) throws BusinessException {
		if (this.password==null) {
			if (ValidationTools.isValidPassword(password)) {
				this.password = password;
			} else {
				throw new BusinessException("Invalid password.\n");
			}
		} else {
			throw new BusinessException("Password for "+this.username+" already exists. "
					+ "If you would like to change it, please login and change your password via the proper protocol. "
					+ "If you forgot your password or cannot login, contact a Bank of Ben employee to assist you retrieving "
					+ "your account information and setting up a new password.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int compareTo(User otherUser) {
		String otherUsername = otherUser.getUsername();
		return this.username.compareTo(otherUsername);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", momsMaidenName=" + momsMaidenName
				+ ", dob=" + dob + ", ssn=" + ssn + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}

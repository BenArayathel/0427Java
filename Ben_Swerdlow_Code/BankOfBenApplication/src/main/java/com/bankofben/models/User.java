package com.bankofben.models;

import java.time.LocalDate;
import java.time.Period;
//import java.util.HashSet;
import java.util.Scanner;

import com.bankofben.business.BusinessException;
import com.bankofben.presentation.UserInterface;
import com.bankofben.presentation.ValidationTools;

public class User extends Person implements Comparable<User> {
	
	private String username;
	private String password;
	private final int id;
	// For now, we will use counter to generate unique ids
	// This will change once we do database stuff
	private static Integer counter = 0;

	public User() {
		super();
		// Change this when you learn database stuff
		counter++;
		this.id = counter.hashCode();
	}

	public User(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob, String ssn,
			String email, String phoneNumber, String username, String password) throws BusinessException { 
//			throws BlankFieldException,
//			InvalidDateOfBirthException, InvalidSsnException, InvalidEmailException, InvalidPhoneNumberException,
//			InvalidUsernameException, InvalidPasswordException, InvalidPasswordChangeException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber);
		setUsername(username);
		setPassword(password);
		// Change this when you learn database stuff
		counter++;
		this.id = counter.hashCode();
	}
	
	public boolean passwordMatch(String password) {
		return password.equals(this.password);
	}
	
	@Override
	public void setDob(LocalDate dob) throws BusinessException {
		super.setDob(dob);
		if (Period.between(dob, LocalDate.now()).getYears() < 18) {
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
	
	private void setPassword(String password) throws BusinessException {
		if (this.password==null) {
			if (ValidationTools.isValidPassword(password)) {
				this.password = password;
			} else {
				throw new BusinessException("Invalid password.\n"+UserInterface.passwordCriteria());
			}
		} else {
			throw new BusinessException("Password for "+this.username+" already exists. "
					+ "If you would like to change it, please login and change your password via the proper protocol. "
					+ "If you forgot your password or cannot login, contact a Bank of Ben employee to assist you retrieving "
					+ "your account information and setting up a new password.");
		}
	}
	
	private boolean passwordChangeConfirmation(Scanner sc) {
		System.out.println("Please input current password to confirm password change:");
		String passwordEntry = sc.nextLine();
		return this.password.equals(passwordEntry);
	}
	
	public void changePassword(Scanner sc) {
		String password;
		boolean confirmation = passwordChangeConfirmation(sc);
		if (confirmation) {
			password = UserInterface.requestNewPassword(sc);
			this.password = password;
			System.out.println("Password has been changed.");
		} else {
			System.out.println("Incorrect current password entry. Did not change password.");
		}
	}
	
	public void changePassword(String oldPassword) {
		if (oldPassword.equals(this.password)) {
			Scanner sc = new Scanner(System.in);
			String password = UserInterface.requestNewPassword(sc);
			this.password = password;
			System.out.println("Password has been changed.");
		} else {
			System.out.println("Current password entered is incorrect. Did not change password.");
		}
	}

	public int getId() {
		return id;
	}
	
	public void applyForAccount() {
		
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
		return "User [username=" + username + ", password=" + password + ", id=" + id + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", momsMaidenName=" + momsMaidenName
				+ ", dob=" + dob + ", ssn=" + ssn + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}

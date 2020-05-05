package com.bankofben.bankapplication;

import java.time.LocalDate;
//import java.util.HashSet;
import java.util.Scanner;

public class User extends Person implements Comparable<User> {
	
	protected String username;
	protected String password;
	public final String id;

	public User() {
		super();
		// Change this when you learn database stuff
		this.id = "0";
	}

	public User(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob, String ssn,
			String email, String phoneNumber, String username, String password) throws BlankFieldException,
			InvalidDateOfBirthException, InvalidSsnException, InvalidEmailException, InvalidPhoneNumberException,
			InvalidUsernameException, InvalidPasswordException, InvalidPasswordChangeException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber);
		setUsername(username);
		setPassword(password);
		// Change this when you learn database stuff
		this.id = "0";
	}
	
	public boolean passwordMatch(String password) {
		return password.equals(this.password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws InvalidUsernameException {
		if (ValidationTools.isValidUsername(username)) {
			this.username = username;
		} else {
			throw new InvalidUsernameException();
		}
	}
	
	private void setPassword(String password) throws InvalidPasswordException, InvalidPasswordChangeException {
		if (this.password.equals(null)) {
			if (ValidationTools.isValidPassword(password)) {
				this.password = password;
			} else {
				throw new InvalidPasswordException();
			}
		} else {
			throw new InvalidPasswordChangeException();
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

}

package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.util.HashSet;

public class Customer extends User {
	
	private HashSet<Account> userAccounts;
//	private BankOfBen bob = BankOfBen.getBank();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob,
			String ssn, String email, String phoneNumber, String username, String password) throws BlankFieldException,
			InvalidDateOfBirthException, InvalidSsnException, InvalidEmailException, InvalidPhoneNumberException,
			InvalidUsernameException, InvalidPasswordException, InvalidPasswordChangeException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
		// TODO Auto-generated constructor stub
	}

	public void setUserAccounts(HashSet<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}
	
	
	public HashSet<Account> getUserAccounts() {
		return userAccounts;
	}

}

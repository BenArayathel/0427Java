package com.bankofben.bankapplication;

import java.time.LocalDate;
import java.util.HashSet;

public class Customer extends User {
	
	private HashSet<Account> myAccounts;
//	private TreeSet<Integer, Account> myAccountNumbers;
//	private BankOfBen bob = BankOfBen.getBank();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String middleName, String lastName, String momsMaidenName, LocalDate dob,
			String ssn, String email, String phoneNumber, String username, String password, HashSet<Account> customerAccounts)
					throws BlankFieldException, InvalidDateOfBirthException, InvalidSsnException, InvalidEmailException, 
					InvalidPhoneNumberException, InvalidUsernameException, InvalidPasswordException, InvalidPasswordChangeException {
		super(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
		this.myAccounts = customerAccounts;
	}

	public void setUserAccounts(HashSet<Account> userAccounts) {
		this.myAccounts = userAccounts;
	}
	
	
	public HashSet<Account> getUserAccounts() {
		return myAccounts;
	}

}

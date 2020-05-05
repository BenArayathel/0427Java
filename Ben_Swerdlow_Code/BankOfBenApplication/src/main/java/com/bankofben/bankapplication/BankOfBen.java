package com.bankofben.bankapplication;

import java.time.LocalDate;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.Scanner;
import java.util.TreeMap;

public class BankOfBen {
	
	private static BankOfBen bob;
	
	private TreeMap<String, User> usernameUserMap;
	private TreeMap<String, String> usernameEmailMap;
	
	private BankOfBen() {
		// No default properties yet
		// TODO: Adapt to persistent data
	}

	public static BankOfBen getBank() {
		// TODO: Adapt to persistent data
		if (bob==null) {
			// Will change when we have persistent data
			bob = new BankOfBen();
			LocalDate dob = LocalDate.parse("1992-01-01");
			User ben;
			try {
				ben = new User("Ben", "Eli", "Swerdlow", "Tobias", dob, "111-11-1111", 
						"ben@gmail.com", "555-555-5555", "benswerdlow", "Lego105!");
				bob.usernameUserMap.put("benswerdlow", ben);
				bob.usernameEmailMap.put("benswerdlow", "ben@gmail.com");
			} catch (BlankFieldException | InvalidDateOfBirthException | InvalidSsnException | InvalidEmailException
					| InvalidPhoneNumberException | InvalidUsernameException | InvalidPasswordException
					| InvalidPasswordChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bob;
	}
	
	public User registerUser(User user) throws ExistingEmailException, ExistingUserException, NullPointerException  {
		if (usernameEmailMap.values().contains(user.getEmail())) {
			throw new ExistingEmailException();
		} else if (usernameUserMap.keySet().contains(user.getUsername())) {
			throw new ExistingUserException();
		}
		try {
			// Putting these in the same try block returning a NullPointerException
			// so if one is invalid, the other map doesn't update
			usernameUserMap.put(user.getUsername(), user);
			usernameEmailMap.put(user.getUsername(), user.getEmail());
		} catch (NullPointerException e) {
			throw e;
		}
		return user;
	}
	
	public User loginUser(String username, String password) throws UserNotFoundException, InvalidLoginException, NullPointerException {
		User user = null;
		if (!(usernameUserMap.keySet().contains(username))) {
			throw new UserNotFoundException();
		} else if (usernameUserMap.get(username).passwordMatch(password)) {
			user = usernameUserMap.get(username);
			return user;
		} else {
			throw new InvalidLoginException();
		}
	}

	public boolean userExists(String username) {
		// Simple method to make intent clearer
		boolean userExists;
		try {
			userExists = this.usernameUserMap.containsKey(username);
		} catch (NullPointerException e) {
			userExists = false;
		}
		return userExists;
	}
	
	public boolean emailExists(String email) {
		// simple method to make intent clearer
		boolean emailExists;
		try {
			emailExists = this.usernameEmailMap.containsValue(email);
		} catch (NullPointerException e) {
			emailExists = false;
		}
		return emailExists;
	}
	
//	private static User getUser(String username) {
//		User user = null;
//		switch (input.toLowerCase()) {
//			case "apply": 
//				user = bob.applyForAccount();
//				break;
//			case "login":
//				user = bob.signIn();
//				break;
//			default:
//				System.out.println();
//		}
//		return user;
//	}

	public TreeMap<String, User> getUsernameUserMap() {
		return usernameUserMap;
	}

	public void setUsernameUserMap(TreeMap<String, User> usernameUserMap) {
		// Make this require employee id and approval
		// and update usernameEmailMap
		this.usernameUserMap = usernameUserMap;
	}

	public TreeMap<String, String> getUsernameEmailMap() {
		return usernameEmailMap;
	}

	public void setUsernameEmailMap(TreeMap<String, String> usernameEmailMap) {
		// Make this require employee id and approval
		// and update usernameEmailMap
		this.usernameEmailMap = usernameEmailMap;
	}
	
	
	
}

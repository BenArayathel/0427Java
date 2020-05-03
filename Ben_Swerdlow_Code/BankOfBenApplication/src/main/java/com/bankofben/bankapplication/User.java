package com.bankofben.bankapplication;

import java.util.HashSet;
import java.util.Scanner;

public class User {
	
	private String username;
	private String password;
	private HashSet<Account> userAccounts;
	private BankOfBen bob = BankOfBen.getBank();
	
	public User() {
		super();
	}

	public User(Scanner sc) {
		super();
		String username = null;
		String password = null;
		String response = null;
		boolean confirmed = false;
		while (!confirmed) {
			System.out.println("Username: ");
			username = sc.nextLine();
			if (!(bob.containsUser(username))) {
				System.out.println("Bank of Ben does not have record of username "+username
						+"Would you like to register this username? (yes or y to confirm)");
				response = sc.nextLine();
				if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
					try {
						registerUser(username, sc);
					} catch (InvalidUserException e) {
						System.out.println("Invalid user credentials. Please enter your credentials again.");
					}
					confirmed = true;
				} else {
					System.out.println("Please enter your credentials again.");
				}
			} else {
				password = requestPassword(sc);
				setUsername(username);
			}
		}
		
	}
	
	private void registerUser(String username, Scanner sc) throws InvalidUserException {
		setUsername(username);
		String password = requestPassword(sc);
		setPassword(password);
		bob.addUser(this);
	}
	
	private String requestPassword(Scanner sc) {
		String password = null;
		String confirmPassword = null;
		boolean confirmed = false;
		while (!(confirmed)) {
			System.out.println("Please input password:");
			password = sc.nextLine();
			System.out.println("Please condfirm password:");
			confirmPassword = sc.nextLine();
			if (password.equals(confirmPassword) && !(password.equals(null))) {
				confirmed = true;
			} else if (password.equals(null)){
				System.out.println("No password entry detected. Please enter your password again.");
			} else {
				System.out.println("Password and confirmation password do not match. Please enter your password again.");
			}
		}
		return password;
	}

	private void registerUser(String username, String password) {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HashSet<Account> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(HashSet<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BankOfBen getBob() {
		return bob;
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

}

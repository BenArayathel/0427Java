package com.bankofben.bankapplication;

import java.util.HashMap;

public class BankOfBen {
	
	private HashMap<String, User> userMap;

	public static BankOfBen getBank() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean containsUser(String username) {
		return this.userMap.containsKey(username);
	}

	public void addUser(User user) throws InvalidUserException {
		
	}
	
}

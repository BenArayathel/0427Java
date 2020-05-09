package com.bank.dao_interface;

import java.util.List;

import com.bank.models.User;
import com.bank.tools.BankException;

public interface UserDAOInterface {
	
	//abstract methods for interacting with my bank_user table in my db
	
	public User createUser(User user) throws BankException;
	public List<User> listUsers() throws BankException;
	

}

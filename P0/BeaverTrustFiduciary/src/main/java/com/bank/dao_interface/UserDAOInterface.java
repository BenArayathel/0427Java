package com.bank.dao_interface;

import java.util.List;

import com.bank.models.User;
import com.bank.tools.BankException;

public interface UserDAOInterface {
	
	//abstract methods for interacting with my bank_user table in my db
	
	public User createUser(User user) throws BankException;
	public List<User> listUsers() throws BankException;
	public boolean loginUser(String username, String password) throws BankException;
	public User accessUserObject(String username) throws BankException;
	void approve(String user_id) throws BankException;
	void removeApproval(String user_id) throws BankException;
	

}

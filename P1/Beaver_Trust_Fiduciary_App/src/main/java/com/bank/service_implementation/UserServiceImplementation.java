package com.bank.service_implementation;

import java.util.List;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.dao_interface.UserDAOInterface;
import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;


public class UserServiceImplementation implements UserServiceInterface {
	private UserDAOInterface udi = new UserDAOImplementation();

	@Override
	public User createUser(User user) throws BankException {
		try {
			udi.createUser(user);
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Something went wrong with account creation");
		}
		return null;			
	}

	@Override
	public List<User> listUsers() throws BankException {
		List<User> users = udi.listUsers();
		return users;
	}

	@Override
	public boolean loginUser(String username, String password) throws BankException {
		return udi.loginUser(username, password);
	}

	@Override
	public User accessUserObject(String username) throws BankException {
		udi.accessUserObject(username);
		return null;
	}
	
	// EMPLOYEE approves pending account application
	@Override
	public void approve(String user_id) throws BankException {
		udi.approve(user_id);
	}

	@Override
	public void removeApproval(String user_id) throws BankException {
		udi.removeApproval(user_id);
		
	}
}

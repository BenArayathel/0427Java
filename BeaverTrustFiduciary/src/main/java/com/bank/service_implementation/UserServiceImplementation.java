package com.bank.service_implementation;

import java.util.List;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.User;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;


public class UserServiceImplementation implements UserServiceInterface {
	//note for the future: the reason this couldn't be private is because I was
	//trying to declare it down in the method!!!
	private UserDAOInterface udi = new UserDAOImplementation();

	@Override
	public User createUser(User user) throws BankException {
		// TODO Auto-generated method stub
		udi.createUser(user);
		return null;
	}

	@Override
	public List<User> listUsers() throws BankException {
		
		// always have to call the methods from an instantiation of
		// a class...why though? they aren't private...it has to do with not being static...
		// it has to do with instance methods vs static methods. 
		udi.listUsers();
		
		return null;
	}

	@Override
	public boolean loginUser(String username, String password) throws BankException {
		return udi.loginUser(username, password);
	}

	
	
	// the SERVICES layer does most of the checks and tests before passing
	// the action down to the DAO, which actually finishes the task



}

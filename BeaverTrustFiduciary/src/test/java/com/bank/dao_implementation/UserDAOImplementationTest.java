package com.bank.dao_implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bank.models.User;
import com.bank.tools.BankException;

public class UserDAOImplementationTest {
	
	UserDAOImplementation udi = new UserDAOImplementation();
	AccountDAOImplementation adi = new AccountDAOImplementation();

	@Test
	public void loginUser() throws BankException {
		// test that existing user can succesfully log in
		assert(udi.loginUser("ethan1", "ethan1"));
	}
	
	@Test
	public void listUsers() throws BankException {
		// fill empty arraylist to test that listUsers is working
		List<User> users = new ArrayList<User>();
		
		assert(users.isEmpty());
		
		List<User> usersTest = udi.listUsers();
		
		for (User u : usersTest) {
			users.add(u);
		}
		
		assert(!usersTest.isEmpty());
	}
	
	@Test
	public void testCreateUser() throws BankException {
		
		User user = new User();
		user.setUsername("UNITtest2");
		user.setPassword("UNITtest2");
		
		List<User> usersBefore = udi.listUsers();
		
		int sizeBefore = usersBefore.size();

		udi.createUser(user);
		
		List<User> usersAfter = udi.listUsers();
		
		int sizeAfter = usersAfter.size();
		
		assert(sizeBefore < sizeAfter);
						
		udi.deleteUser("UNITtest2");
	}
	
	@Test
	public void approveTest() throws BankException {
		boolean showApproval;
		String id;
		
		// approve needs user id string
		// 
		
		User user = new User();
		user.setUsername("UNITtest3");
		user.setPassword("UNITtest3");
		udi.createUser(user);
		
		System.out.println(user);
		List<User> users = udi.listUsers();
		
		for (User u: users) {
			if (u.getUsername() == "UNITtest3") {
				id = u.getUser_id();
				adi.approve(id);
				showApproval = true;
			} else {
				showApproval = false;
			}
			System.out.println(showApproval);
			assert(showApproval);
		}
		
		
		udi.deleteUser("UNITtest3");
	}
}

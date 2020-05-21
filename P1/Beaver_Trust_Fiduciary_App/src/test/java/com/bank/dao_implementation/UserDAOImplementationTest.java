package com.bank.dao_implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;

import com.bank.models.User;
import com.bank.tools.BankException;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOImplementationTest {
	
	UserDAOImplementation udi = new UserDAOImplementation();
	AccountDAOImplementation adi = new AccountDAOImplementation();

	@Test
	public void loginUser() throws BankException {
		// test that existing user can succesfully log in
		assert(udi.loginUser("ethan1", "ethan1"));
		assertEquals(false, (udi.loginUser("madeupnotexist", "madeupdoesntexist")));
		assertEquals(false, (udi.loginUser("ethan1", "wrongpassword")));
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
	public void testCreateObject() throws BankException {
		String username = "ethan1";
		User user = udi.accessUserObject(username);
		assert(user.getPassword().equals("ethan1"));
	}
	
	
	@Test	
	  public void approveTest() throws BankException {
        // get the test user to work on
        final String user_name = "UNITtestApprove";
        final User user = getUserByName(user_name);

        // find their user id (it is 590)
        String user_id = user.getUser_id();

        // approve the account, test that the boolean/number set to 1
        udi.approve(user_id);
        assertEquals(1, getUserByName(user_name).getApproved());

        // remove approval, test that the boolean/number reset to 0
        udi.removeApproval(user_id);
        assertEquals(0, getUserByName(user_name).getApproved());

    }
	
	// distance accessing the object to a new function so they don't overlap
    public User getUserByName(final String user_name) throws BankException {
        return udi.accessUserObject(user_name);
    }
}

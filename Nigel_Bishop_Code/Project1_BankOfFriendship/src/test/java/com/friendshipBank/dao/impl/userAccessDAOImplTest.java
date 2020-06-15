package com.friendshipBank.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.friendshipBank.dao.userAccessDAO;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;

public class userAccessDAOImplTest {



	@Test
	public final void testLoginByUsernameAndPassword() throws BusinessException {

		String expectedUserID = "FBUA10000";
//		String expectedCustomerID = "FBJS196710040";
		
		userAccessDAO userDAO = new userAccessDAOImpl();
		userAccess userAccess = new userAccess();
		
		userAccess = userDAO.loginByUsernameAndPassword("johnsmith", "JohnSmith18");
		String results = userAccess.getUserID();
		
		assertEquals(expectedUserID,results);	
	}


	@Test
	public final void testGetAllUserLoginAccounts() throws BusinessException {
		userAccessDAO userDAO = new userAccessDAOImpl();
		
		List<userAccess> resultList = new ArrayList<>();
		
		resultList = userDAO.getAllUserLoginAccounts();
		
		assertNotNull(resultList);
	}

}

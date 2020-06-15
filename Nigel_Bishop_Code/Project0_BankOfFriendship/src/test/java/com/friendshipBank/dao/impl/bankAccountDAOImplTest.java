package com.friendshipBank.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;

public class bankAccountDAOImplTest {


	@Test
	public final void testGetAccountInfo() throws BusinessException {
		
//		AccountID: FBAC10011
		
		String expectedAccountID = "FBAC10011";
		
		bankAccountDAO bankDAO = new bankAccountDAOImpl();
		bankAccount bAccount = new bankAccount();
		
		bAccount = bankDAO.getAccountInfo("FBJA199910082", "CHECKING");
		String results = bAccount.getAccountID();
		
		assertEquals(expectedAccountID,results);	
	}

	@Test
	public final void testGetAccountInfoByAccountID() throws BusinessException {
		String expectedCustomerID = "FBJA199910082";
		
		bankAccountDAO bankDAO = new bankAccountDAOImpl();
		bankAccount bAccount = new bankAccount();
		
		bAccount = bankDAO.getAccountInfoByAccountID("FBAC10011");
		String results = bAccount.getCustomerID();
		
		assertEquals(expectedCustomerID,results);
	}

	@Test
	public final void testGetAllBankAccounts() throws BusinessException {
		
		bankAccountDAO bankDAO = new bankAccountDAOImpl();
		
		List<bankAccount> resultList = new ArrayList<>();
		
		resultList = bankDAO.getAllBankAccounts();
		
		assertNotNull(resultList);
		
		
		
	}

}

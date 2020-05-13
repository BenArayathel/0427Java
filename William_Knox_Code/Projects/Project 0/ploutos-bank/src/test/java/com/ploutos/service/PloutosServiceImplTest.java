package com.ploutos.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ploutos.exception.BusinessException;
import com.ploutos.model.Account;
import com.ploutos.model.Login;

public class PloutosServiceImplTest {
	PloutosServiceImpl psi = new PloutosServiceImpl();

	@Test
	public void usernameValidityTest() {
		assertEquals(false, psi.isValidUsername("fourscoreandsevenyearsagoiwasayoungmanandletmetellyousomethingthosetimesweredifferentyahear"));
		assertEquals(false, psi.isValidUsername("robert loggia"));
		assertEquals(false, psi.isValidUsername("robert_loggia"));
		assertEquals(true, psi.isValidUsername("stevieboscomi123"));
	}
	
	@Test
	public void logInTest() throws BusinessException {
		assertEquals(false, (psi.getLogin("bobby","dole") != null));
		assertEquals(true, (psi.getLogin("rob","password") != null));
		assertEquals(false, (psi.getLogin("123","123") != null));
	}
	
	@Test(expected=BusinessException.class)
	public void accountListByLoginTest() throws BusinessException {
		psi.accountListByLogin(new Login("rob","incorrectpassword"));
	}
	
	@Test
	public void accountListByLoginTest2() throws BusinessException {
		psi.accountListByLogin(new Login("rob","password"));
	}
	
	@Test(expected=BusinessException.class)
	public void makeAccountTest() throws BusinessException {
		psi.makeAccount(new Login("1 2 3","1 2 3"), 0);
		psi.makeAccount(new Login("1 2 3","1 2 3"), -110);
		psi.makeAccount(new Login("rob","password"), -110);
	}
	
	@Test(expected=BusinessException.class)
	public void makeAccountTest3() throws BusinessException {
		psi.makeAccount(new Login("asdf","asdf"), -1200);
	}
	
	
	@Test
	public void makeAccountTest2() throws BusinessException {
		psi.makeAccount(new Login("rob","password"), 110);
	}
	
	@Test(expected=BusinessException.class)
	public void makeLoginRequestTest() throws BusinessException {
		psi.makeLoginRequest("robert", "loggia");
		psi.makeLoginRequest("robert", "loggia");
	}
	
	@Test
	public void withdrawTest() { // trying to withdraw when request is higher than balance
		Account ac1 = new Account(0000000000,"rob",150000);
		Account ac2 = new Account(1111111111,"steve",-2000);
		try {
			psi.withdraw(ac1, 99999999);
		} catch (BusinessException e) {}
		try {
			psi.withdraw(ac2, 10);
		} catch (BusinessException e) {}
		assertEquals(150000,ac1.getBalance());
		assertEquals(-2000, ac2.getBalance()); 
	}
	
	@Test(expected=BusinessException.class) // making a transaction when the transfer amount is higher than what's in the donor account
	public void makeTransactionTest() throws BusinessException {
		Account ac = new Account(1111111111,"robbie",0);
		Account bac = new Account(123,"steve",-1230);
		psi.makeTransaction(ac, bac, 5); 
	}
	
	@Test
	public void accountListStringTest() throws BusinessException { 
		assertNotNull(psi.accountListString(psi.accountListByLogin(new Login("rob","password"))));
	}


}

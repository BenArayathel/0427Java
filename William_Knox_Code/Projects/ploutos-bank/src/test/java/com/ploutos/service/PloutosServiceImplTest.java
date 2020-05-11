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
		System.out.println("    Testing isValidUsername");
	}
	
	@Test
	public void logInTest() throws BusinessException {
		assertEquals(false, (psi.logIn("bobby","dole") != null));
		assertEquals(true, (psi.logIn("rob","robpass") != null));
		assertEquals(false, (psi.logIn("123","123") != null));
	}
	
	@Test
	public void accountListByLoginTest() throws BusinessException {
		assertNotNull(psi.accountListByLogin(new Login("robbie","rotten")));
		assertNotNull(psi.accountListByLogin(new Login("rob","robpass")));
		assertNull(psi.accountListByLogin(new Login("rob","notrobpass")));
		assertNull(psi.accountListByLogin(new Login("notrob","notrobpass")));
		assertNull(psi.accountListByLogin(new Login("bob","bobpass")));
		assertNull(psi.accountListByLogin(new Login("123","123")));
		
	}
	
	@Test
	public void getAccountByNumberTest() throws BusinessException {
		assertNull(psi.getAccountByNumber(1111111111));
		assertNull(psi.getAccountByNumber(-1111111111));
		assertNotNull(psi.getAccountByNumber(1000176223));
	}
	
	@Test
	public void makeAccountTest() throws BusinessException {
		assertNotNull(psi.makeAccount(new Login("1 2 3","1 2 3"), 0));
		assertNotNull(psi.makeAccount(new Login("1 2 3","1 2 3"), -110));
		assertNotNull(psi.makeAccount(new Login("rob","robpass"), -110));
		assertNotNull(psi.makeAccount(new Login("rob","robpass"), 110));
	}
	
	@Test
	public void makeLoginRequestTest() throws BusinessException {
		psi.makeLoginRequest("robert", "loggia");
		psi.makeLoginRequest("robert", "loggia");
	}
	
	@Test
	public void withdrawTest() {
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
	
	@Test
	public void depositTest() throws BusinessException {
		Account ac1 = new Account(0000000000,"rob",150000);
		Account ac2 = new Account(1111111111,"steve",-2000);
		psi.deposit(ac2, 4000);
		psi.deposit(ac1, 50000);
		try {
			psi.deposit(ac1, -50000);
		} catch (BusinessException e) {}
		assertEquals(200000,ac1.getBalance());
		assertEquals(2000,ac2.getBalance());
	}
	
	@Test
	public void makeTransactionTest() throws BusinessException {
		Account ac1 = new Account(0000000000,"rob",150000);
		Account ac2 = new Account(1111111111,"robbie",0);
		Account bac1 = new Account(123,"steve",-1230);
		Account bac2 = new Account(1111111222,"george",999);
		assertNotNull(psi.makeTransaction(new Account(1000176223,"rob",10), new Account(1000176223,"rob",10), 5));
		assertNotNull(psi.makeTransaction(ac1, ac2, 1000));
		assertNull(psi.makeTransaction(ac2, ac1, 500));
		assertNull(psi.makeTransaction(bac1,ac2, 5));
		assertNull(psi.makeTransaction(bac2,bac1,500));
		assertNull(psi.makeTransaction(ac1,ac2,-400));
		assertNull(psi.makeTransaction(ac2,ac1,900000));
	}
	
	@Test
	public void accountListStringTest() throws BusinessException {
		assertNotNull(psi.accountListString(psi.accountListByLogin(new Login("robbie","rotten"))));
		assertNotNull(psi.accountListString(psi.accountListByLogin(new Login("rob","robpass"))));
	}


}

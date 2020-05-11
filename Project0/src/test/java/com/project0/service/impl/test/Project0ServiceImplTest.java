package com.project0.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project0.dao.AccountDAO;
import com.project0.dao.impl.AccountDaoImpl;
import com.project0.exception.BusinessException;
import com.project0.model.Account;
import com.project0.model.User;
import com.project0.service.Project0Service;
import com.project0.service.impl.Project0ServiceImpl;

public class Project0ServiceImplTest {
	private static Project0Service p0s;
	private static AccountDAO accountDao;
	private static User testUser, employee;
	private static Account testAccount;
	
	@BeforeClass
	public static void createServiceObjects() {
		p0s = new Project0ServiceImpl();
		accountDao = new AccountDaoImpl();
		testUser = new User("BenK", "passw0rd", false);
		employee = new User("Bob Executive", "8us1n355", true);
		testAccount = new Account(10005, testUser.getUserName(), 10050.50, false);
	}
	
	@Test
	public void testCreateUserAddsNewUserToDatabase() throws BusinessException {
		p0s.createUser(testUser);
		User fetchedUser = p0s.getUser(testUser.getUserName(), testUser.getPassword());
		assertEquals(fetchedUser, testUser);
	}
	
	@Test
	public void testCreateAccountAddsNewAccountToDatabaseIfCalledByAuthorizedUser() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, testAccount);
		testAccount.setAccountId(newAccount.getAccountId());
		assertEquals(newAccount, testAccount);
	}
	
	@Test
	public void testGetAccountRetrievesExistingAccountInformationWithCorrectUserData() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(11000, testUser.getUserName(), 10000, false));
		Account fetchedAccount = p0s.getAccountById(testUser, newAccount.getAccountId());
		Account businessFetched = p0s.getAccountById(employee, fetchedAccount.getAccountId());
		assertEquals(newAccount, fetchedAccount);
		assertEquals(newAccount, businessFetched);
	}
	
	@Test
	public void testWithdrawRemovesMoneyFromExistingAccountWithCorrectUserData() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(15000, testUser.getUserName(), 10000, true));
		Account wdAccount = p0s.withdraw(testUser, newAccount.getAccountId(), 5000);
		assertEquals(wdAccount.getBalance(), 5000, 0.0001);
	}
	
	@Test
	public void testWithdrawDoesNotRemoveMoneyIfAccountIsNotApproved() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, testAccount);		// test will fail because testAccount is not approved
		Account wdAccount = p0s.withdraw(testUser, newAccount.getAccountId(), 10000);
		assertEquals(wdAccount.getBalance(), 50.5, 0.0001);
	}
	
	@Test
	public void testWithdrawDoesNotRemoveMoneyIfTransactionIsOverdrawn() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(12000, testUser.getUserName(), 10000, true));
		Account wdAccount = p0s.withdraw(testUser, newAccount.getAccountId(), 20000);	// test will fail due to overdraw
		assertEquals(wdAccount.getBalance(), 10000, 0.0001);
	}
	
	@Test
	public void testWithdrawDoesNotRemoveMoneyIfAccessedByUnauthorizedUser() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account (10050, testUser.getUserName(), 10000, true));
		Account wdAccount = p0s.withdraw(new User("fraudman", "cantgetin", false), newAccount.getAccountId(), 2000);
		assertEquals(wdAccount.getBalance(), 8000, 0.0001);
	}
	
	@Test
	public void testDepositAddsMoneyToExistingAccountWithCorrectUserData() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(13000, testUser.getUserName(), 10000, true));
		Account dpAccount = p0s.deposit(testUser, newAccount.getAccountId(), 5000);
		assertEquals(dpAccount.getBalance(), 15000, 0.0001);
	}
	
	@Test
	public void testDepositDoesNotAddMoneyIfAccountIsNotApproved() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, testAccount);
		Account dpAccount = p0s.deposit(testUser, newAccount.getAccountId(), 8000);
		assertEquals(dpAccount.getBalance(), 8050.50, 0.0001);
	}
	
	@Test
	public void testDepositDoesNotAddMoneyIfAccessedByUnauthorizedUser() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(12333, testUser.getUserName(), 10000, true));
		Account dpAccount = p0s.deposit(new User("fraudman", "cantgetin", false), newAccount.getAccountId(), 5000);
		assertEquals(dpAccount.getBalance(), 15000, 0.0001);
	}
	
	@Test
	public void testApproveAccountChangesApprovedToTrueIfUserIsEmployee() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(12343, testUser.getUserName(), 10023, false));
		Account approvedAccount = p0s.approveAccount(employee, newAccount);
		assertTrue(approvedAccount.isApproved());
	}
	
	@Test
	public void testApproveAccountDoesNotChangeApprovedToTrueIfUserIsNotEmployee() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(12343, testUser.getUserName(), 10023, false));
		Account approvedAccount = p0s.approveAccount(testUser, newAccount);
		assertTrue(approvedAccount.isApproved());
	}
	
	@Test
	public void testRejectAccountRemovesAccountFromDatabaseIfUserIsEmployee() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(11111, testUser.getUserName(), 33333, false));
		p0s.rejectAccount(employee, newAccount);
		assertNull(accountDao.getAccountById(newAccount.getAccountId()));
	}
	
	@Test
	public void testRejectAccountDoesNotRemoveAccountFromDatabaseIfUserIsNotEmployee() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(11111, testUser.getUserName(), 33333, false));
		p0s.rejectAccount(testUser, newAccount);
		assertNull(accountDao.getAccountById(newAccount.getAccountId()));
	}
	
	@Test
	public void testTransferMoneySuccessfullyTransfersMoneyBetweenTwoAccountsIfInputsAreValid() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(11111, testUser.getUserName(), 50000, true));
		Account targetAccount = p0s.createAccount(testUser, new Account(11111, testUser.getUserName(), 40000, false));
		p0s.transferMoney(testUser, newAccount.getAccountId(), targetAccount.getAccountId(), 10000);
		assertEquals(p0s.getAccountById(testUser, newAccount.getAccountId()).getBalance(), 40000, 0.0001);
		assertEquals(p0s.getAccountById(testUser, targetAccount.getAccountId()).getBalance(), 50000, 0.0001);
	}
	
	@Test
	public void testTransferMoneyDoesNotTransferMoneyBetweenTwoAccountsIfUserDoesNotOwnAccount() throws BusinessException {
		Account newAccount = p0s.createAccount(testUser, new Account(11111, testUser.getUserName(), 50000, true));
		Account targetAccount = p0s.createAccount(testUser, new Account(11111, testUser.getUserName(), 40000, false));
		p0s.transferMoney(employee, newAccount.getAccountId(), targetAccount.getAccountId(), 10000);
		assertEquals(p0s.getAccountById(testUser, newAccount.getAccountId()).getBalance(), 40000, 0.0001);
		assertEquals(p0s.getAccountById(testUser, targetAccount.getAccountId()).getBalance(), 50000, 0.0001);
	}
	
	@AfterClass
	public static void destroyServiceObjects() {
		p0s = null;
		accountDao = null;
	}

}

package com.project0.dao.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project0.dao.AccountDAO;
import com.project0.dao.UserDAO;
import com.project0.dao.impl.AccountDaoImpl;
import com.project0.dao.impl.UserDaoImpl;
import com.project0.exception.BusinessException;
import com.project0.model.Account;
import com.project0.model.User;

public class Project0DAOImplTest {
	
	private static UserDAO userDAO;
	private static AccountDAO accountDAO;
	private static User testUser;
	private static Account testAccount;
	
	@BeforeClass
	public static void createrDaoObjects() {
		userDAO = new UserDaoImpl();
		accountDAO = new AccountDaoImpl();
		testUser = new User("NewCustomer", "password", false);
		testAccount = new Account(10002, testUser.getUserName(), 10000.49, true);
	}
	
	@Test
	public void testCreateUserAddsNewUserToDatabase() throws BusinessException {
		userDAO.createUser(testUser);
		User retrievedUser = userDAO.getUser(testUser.getUserName(), testUser.getPassword());
		assertEquals(testUser, retrievedUser);
	}
	
	@Test
	public void testGetUserRetrievesExistingUserFromDatabase() throws BusinessException {
		User retrievedUser = userDAO.getUser(testUser.getUserName(), testUser.getPassword());
		assertEquals(testUser, retrievedUser);
	}
	
	@Test
	public void testCreateAccountAddsNewAccountToDatabase() throws BusinessException {
		Account newAccount = accountDAO.createAccount(testAccount);
		assertEquals(testAccount, newAccount);
	}
	
	@Test
	public void testGetAccountRetrievesExistingAccountFromDatabase() throws BusinessException {
		Account newAccount = accountDAO.createAccount(testAccount);
		Account retrievedAccount = accountDAO.getAccountById(newAccount.getAccountId());
		assertEquals(testAccount, retrievedAccount);
	}
	
	@Test
	public void testWithdrawTakesMoneyFromExistingAccount() throws BusinessException {
		Account newAccount = accountDAO.createAccount(testAccount);
		Account wdAccount = accountDAO.withdraw(newAccount.getAccountId(), 5000.49);
		assertEquals(wdAccount.getBalance(), 5000.00, 0.0001);
	}
	
	@Test
	public void testDepositAddsMoneyToExistingAccount() throws BusinessException {
		Account newAccount = accountDAO.createAccount(testAccount);
		Account dpAccount = accountDAO.deposit(newAccount.getAccountId(), 4999.51);
		assertEquals(dpAccount.getBalance(), 15000.00, 0.0001);
	}
	
	@Test
	public void testApproveAccountFlipsApprovedToTrue() throws BusinessException {
		Account newAccount = accountDAO.createAccount(new Account(14008, testUser.getUserName(), 10000, false));
		Account approvedAccount = accountDAO.approveAccount(newAccount);
		assertTrue(approvedAccount.isApproved());
	}
	
	@Test
	public void testRejectAccountRemovesAccountFromDatabase() throws BusinessException {
		Account newAccount = accountDAO.createAccount(new Account(14008, testUser.getUserName(), 25000, false));
		accountDAO.rejectAccount(newAccount);
		assertNull(accountDAO.getAccountById(newAccount.getAccountId()));
	}
	
	@Test
	public void testListAccountsByUserNameReturnsArrayListOfAccountsByUserName() throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		accountList = accountDAO.getAccountsByUserName(testUser.getUserName());
		for (Account a : accountList) {
			assertEquals(a.getUserName(), testUser.getUserName());
		}
	}
	
	@Test
	public void testListPendingAccountsReturnsArrayListOfUnapprovedAccounts() throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		accountList = accountDAO.getPendingAccounts();
		assertTrue(accountList.size() > 0);
		for (Account a : accountList) {
			assertFalse(a.isApproved());
		}
	}
	
	@AfterClass
	public static void destroyUserDaoObject() {
		userDAO = null;
	}

}

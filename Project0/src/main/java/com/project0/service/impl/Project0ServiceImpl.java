package com.project0.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project0.dao.AccountDAO;
import com.project0.dao.UserDAO;
import com.project0.dao.impl.AccountDaoImpl;
import com.project0.dao.impl.UserDaoImpl;
import com.project0.exception.BusinessException;
import com.project0.model.Account;
import com.project0.model.User;
import com.project0.service.Project0Service;

public class Project0ServiceImpl implements Project0Service {
	
	private UserDAO userDao = new UserDaoImpl();
	private AccountDAO accountDao = new AccountDaoImpl();

	@Override
	public void createUser(User user) throws BusinessException {
		if(userDao.getUser(user.getUserName(), user.getPassword()) == null) {
			userDao.createUser(user);
		} 
	}

	@Override
	public User getUser(String userName, String password) throws BusinessException {
		User u = userDao.getUser(userName, password);
		if (u == null) {
			throw new BusinessException("User does not exist");
		}
		return u;
	}

	@Override
	public Account createAccount(User user, Account account) throws BusinessException {
		Account a = null;
		if(user.getUserName().equals(account.getUserName())) {
			a = accountDao.createAccount(account);
		} else {
			throw new BusinessException("User is not authorized to create this account");
		}
		
		return a;
	}

	@Override
	public Account getAccountById(User user, int accountId) throws BusinessException {
		Account a = accountDao.getAccountById(accountId);
		if(a == null) {
			throw new BusinessException("Account does not exist");
		} else if (!(user.getUserName().equals(a.getUserName())) && !(user.isEmployee())) {
			a = null;
			throw new BusinessException("User is not authorized to access this account");
		} 
		
		return a;
	}

	@Override
	public Account withdraw(User user, int accountId, double amount) throws BusinessException {
		Account a = getAccountById(user, accountId);
		if (a != null && a.isApproved()) {
			if(amount > a.getBalance()) {
				throw new BusinessException("Cannot withdraw more money than is in the account");
			} else if (amount < 0) {
				throw new BusinessException("Cannot withdraw negative amount");
			} else {
				a = accountDao.withdraw(accountId, amount);
			}
		} else {
			throw new BusinessException("Invalid account");
		}
		
		return a;
	}

	@Override
	public Account deposit(User user, int accountId, double amount) throws BusinessException {
		Account a = getAccountById(user, accountId);
		if (amount < 0) {
			throw new BusinessException("Cannot deposit negative amount");
		} else if (a != null && a.isApproved()) {
			a = accountDao.deposit(accountId, amount);
		} else {
			throw new BusinessException("Invalid account");
		}
		return a;
	}

	@Override
	public Account approveAccount(User user, Account account) throws BusinessException {
		Account a = null;
		
		if(user.isEmployee()) {
			a = accountDao.approveAccount(account);
		} else {
			throw new BusinessException("You are not authorized to approve accounts");
		}
		
		return a;
	}

	@Override
	public void rejectAccount(User user, Account account) throws BusinessException {
		if(user.isEmployee()) {
			accountDao.rejectAccount(account);
		} else {
			throw new BusinessException("You are not authorized to reject accounts");
		}
	}

	@Override
	public void transferMoney(User user, int accountId, int targetId, double amount) throws BusinessException {
		Account a = getAccountById(user, accountId);
		if (a != null && a.getUserName().equals(user.getUserName()) && a.isApproved() && amount >= 0) {
			if(amount <= a.getBalance()) {
				accountDao.withdraw(accountId, amount);
				Account t = accountDao.deposit(targetId, amount);
			} else if (amount < 0) {
				throw new BusinessException("Cannot transfer negative amount of money");
			} else {
				throw new BusinessException("Cannot transfer more money than the balance of the account");
			}
		} else {
			throw new BusinessException("Invalid account - cannot initiate transfer");
		}
	}

	@Override
	public List<Account> getAccountsByUserName(User user, String userName) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		if(user.getUserName().equals(userName) || user.isEmployee()) {
			accountList = accountDao.getAccountsByUserName(userName);
		} else if (accountList.isEmpty()) {
			throw new BusinessException("No accounts found for user " + userName);
		} else {
			throw new BusinessException("Invalid user - you do not have permission to access these accounts");
		}
		return accountList;
	}

	@Override
	public List<Account> getPendingAccounts(User user) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		if(user.isEmployee()) {
			accountList = accountDao.getPendingAccounts();
		} else {
			throw new BusinessException("Invalid user - you do not have permission to view these accounts");
		}
		return accountList;
	}
	
	@Override
	public void getTransactionLog() throws BusinessException { 
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\bbqpl\\eclipse-workspace\\Project0\\logs\\info-hack-bank.log"));
		} catch (FileNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		String line;
		try {
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
	}

}

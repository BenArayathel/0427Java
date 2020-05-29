package com.project1.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.project1.dao.AccountDAO;
import com.project1.dao.UserDAO;
import com.project1.dao.impl.AccountDaoImpl;
import com.project1.dao.impl.UserDaoImpl;
import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.model.User;
import com.project1.service.Project1Service;

public class Project1ServiceImpl implements Project1Service {
	
	private UserDAO userDao = new UserDaoImpl();
	private AccountDAO accountDao = new AccountDaoImpl();
	final static Logger logger = LogManager.getLogger(Project1ServiceImpl.class);

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
			logger.error("User does not exist");
			throw new BusinessException("User does not exist");
		}
		return u;
	}
	
	@Override
	public User makeUserEmployee(User user, String userName) throws BusinessException {
		User u = null;
		
		if (user.isEmployee()) {
			try {
				u = userDao.makeUserEmployee(userName);
			} catch (BusinessException e) {
				logger.error(e.getMessage());
				throw new BusinessException(e.getMessage());
			}
		} else if (!user.isEmployee()) {
			logger.error("User is not authorized to make this user an employee");
			throw new BusinessException("User is not authorized to make this user an employee");
		} else if (u == null) {
			logger.error("User does not exist");
			throw new BusinessException("User does not exist");
		}
		
		return u;
	}

	@Override
	public Account createAccount(User user, Account account) throws BusinessException {
		Account a = null;
		if(user.getUserName().equals(account.getUserName())) {
			a = accountDao.createAccount(account);
			logger.info("Account " + account.getAccountId() + " created by " + user.getUserName());
		} else {
			logger.error("User is not authorized to create this account");
			throw new BusinessException("User is not authorized to create this account");
		}
		
		return a;
	}

	@Override
	public Account getAccountById(User user, int accountId) throws BusinessException {
		Account a = accountDao.getAccountById(accountId);
		if(a == null) {
			logger.error("Account does not exist");
			throw new BusinessException("Account does not exist");
		} else if (!(user.getUserName().equals(a.getUserName())) && !(user.isEmployee())) {
			a = null;
			logger.error("User is not authorized to access this account");
			throw new BusinessException("User is not authorized to access this account");
		} 
		
		return a;
	}

	@Override
	public Account withdraw(User user, int accountId, double amount) throws BusinessException {
		Account a = getAccountById(user, accountId);
		if (a != null && a.isApproved()) {
			if(amount > a.getBalance()) {
				logger.error("Cannot withdraw more money than is in the account");
				throw new BusinessException("Cannot withdraw more money than is in the account");
			} else if (amount < 0) {
				logger.error("Cannot withdraw negative amount");
				throw new BusinessException("Cannot withdraw negative amount");
			} else {
				a = accountDao.withdraw(accountId, amount);
				logger.info("$" + amount + " withdrawn from account " + accountId + " by user " + user.getUserName());
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
			logger.error("Cannot deposit negative amount");
			throw new BusinessException("Cannot deposit negative amount");
		} else if (a != null && a.isApproved()) {
			a = accountDao.deposit(accountId, amount);
			logger.info("$" + amount + " deposited into account " + accountId + " by user " + user.getUserName());
		} else {
			logger.error("Invalid account");
			throw new BusinessException("Invalid account");
		}
		return a;
	}

	@Override
	public Account approveAccount(User user, Account account) throws BusinessException {
		Account a = null;
		
		if(user.isEmployee()) {
			a = accountDao.approveAccount(account);
			logger.info("Account " + account.getAccountId() + " approved by employee " + user.getUserName());;
		} else {
			logger.error("You are not authorized to approve accounts");
			throw new BusinessException("You are not authorized to approve accounts");
		}
		
		return a;
	}

	@Override
	public void rejectAccount(User user, Account account) throws BusinessException {
		if(user.isEmployee()) {
			accountDao.rejectAccount(account);
			logger.info("Account " + account.getAccountId() + " rejected by employee " + user.getUserName());
		} else {
			logger.error("You are not authorized to reject accounts");
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
				logger.info("$" + amount + " transferred from Account #" + accountId + " to Account #" + targetId);
			} else if (amount < 0) {
				logger.error("Cannot transfer negative amount of money");
				throw new BusinessException("Cannot transfer negative amount of money");
			} else {
				logger.error("Cannot transfer more money than the balance of the account");
				throw new BusinessException("Cannot transfer more money than the balance of the account");
			}
		} else {
			logger.error("Cannot transfer more money than the balance of the account");
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
	public ArrayList<String> getTransactionLog() throws BusinessException { 
		BufferedReader br;
		ArrayList<String> transactions = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\bbqpl\\eclipse-workspace\\Project1\\logs\\info_hack_bank.log"));
		} catch (FileNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		String line;
		try {
			while((line = br.readLine()) != null) {
				transactions.add(line);
			}
			br.close();
		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
		
		return transactions;
	}

}

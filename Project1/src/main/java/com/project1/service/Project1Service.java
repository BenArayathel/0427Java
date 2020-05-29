package com.project1.service;

import java.util.ArrayList;
import java.util.List;

import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.model.User;

public interface Project1Service {

	public void createUser(User testUser) throws BusinessException;
	public User getUser(String userName, String password) throws BusinessException;
	public User makeUserEmployee(User user, String userName) throws BusinessException;
	public Account createAccount(User user, Account account) throws BusinessException;
	public Account getAccountById(User user, int accountId) throws BusinessException;
	public Account withdraw(User user, int accountId, double amount) throws BusinessException;
	public Account deposit(User user, int accountId, double amount) throws BusinessException;
	public Account approveAccount(User user, Account account) throws BusinessException;
	public void rejectAccount(User user, Account account) throws BusinessException;
	public void transferMoney(User user, int accountId, int targetId, double amount) throws BusinessException;
	public List<Account> getAccountsByUserName(User user, String userName) throws BusinessException;
	public List<Account> getPendingAccounts(User user) throws BusinessException;
	public ArrayList<String> getTransactionLog() throws BusinessException;
}

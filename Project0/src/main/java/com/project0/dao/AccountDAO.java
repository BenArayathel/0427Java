package com.project0.dao;

import java.util.List;

import com.project0.exception.BusinessException;
import com.project0.model.Account;

public interface AccountDAO {
	public Account createAccount(Account account) throws BusinessException; 
	public Account getAccountById(int accountId) throws BusinessException;
	public Account withdraw(int accountId, double amount) throws BusinessException;
	public Account deposit(int accountId, double amount) throws BusinessException;
	public Account approveAccount(Account account) throws BusinessException;
	public void rejectAccount(Account account) throws BusinessException;
	public List<Account> getAccountsByUserName(String userName) throws BusinessException;
	public List<Account> getPendingAccounts() throws BusinessException;
}

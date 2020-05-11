package com.ploutos.service;

import java.util.List;

import com.ploutos.exception.BusinessException;
import com.ploutos.model.Account;
import com.ploutos.model.Login;
import com.ploutos.model.Transaction;

public interface PloutosService {
	public boolean isValidUsername(String username);		// Problematic. Should just throw exceptions.
	public boolean isValidEmployee(String username, String password) throws BusinessException;	// Same as above.
	public Login logIn(String username, String password) throws BusinessException;
	public List<Account> accountListByLogin(Login login) throws BusinessException;
	public Account getAccountByNumber(long accountNumber) throws BusinessException;
	public Account makeAccount(Login login, int balance) throws BusinessException;
	public void makeLoginRequest(String username, String password) throws BusinessException;
	public List<Login> listLoginsActive(); // active = 1
	public List<Login> listLoginsInactive(); // active = 0
//	public List<Login> listLoginsRejected(); // active = -1
	public void approveLoginRequest(Login login) throws BusinessException;
	public void rejectLoginRequest(Login login) throws BusinessException;
	public void withdraw(Account account, int amount) throws BusinessException;
	public void deposit(Account account, int amount) throws BusinessException;
	public Transaction makeTransaction(Account to, Account from, int amount) throws BusinessException; // Pre-Approve Transactions where both accounts are owned by the same person.
	
	
	// Each list should start at 1 (ONE)
	public String accountListString(List<Account> accounts) throws BusinessException;
	public String loginListString(List<Login> logins) throws BusinessException;
	public String transactionListString(List<Transaction> transactions) throws BusinessException;
	public List<Transaction> getTransactionList() throws BusinessException;
	public boolean isValidLogin(Login login) throws BusinessException;

}

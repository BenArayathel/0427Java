package com.ploutos.service;

import java.util.List;

import com.ploutos.exception.BusinessException;
import com.ploutos.model.Account;
import com.ploutos.model.Login;
import com.ploutos.model.Transaction;

public interface PloutosService {
	public boolean isValidUsername(String username);
	public boolean isValidEmployee(String username, String password) throws BusinessException;
	public boolean isValidLogin(Login login) throws BusinessException;
	
	
	public void makeLoginRequest(String username, String password) throws BusinessException;
	public Login getLogin(String username, String password) throws BusinessException;
	public void updateLoginRequest(Login login, int status) throws BusinessException;
	public List<Login> listLoginsActive() throws BusinessException; // active = 1
	public List<Login> listLoginsInactive() throws BusinessException; // active = 0

	
	
	// Each list should start at 1 (ONE)
	public String accountListString(List<Account> accounts) throws BusinessException;
	public String loginListString(List<Login> logins) throws BusinessException;
	public String transactionListString(List<Transaction> transactions) throws BusinessException;
	
	
	public Account makeAccount(Login login, int balance) throws BusinessException;
	public Account getAccountByNumber(long accountNumber) throws BusinessException;
	public List<Account> accountListByLogin(Login login) throws BusinessException;
	public void withdraw(Account account, int amount) throws BusinessException;
	public void deposit(Account account, int amount) throws BusinessException;
	
	
	public Transaction makeTransaction(Account to, Account from, int amount) throws BusinessException; // Pre-Approve Transactions where both accounts are owned by the same person.
	public List<Transaction> getTransactionList() throws BusinessException;
}
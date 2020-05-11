package com.ploutos.dao;

import java.util.List;

import com.ploutos.exception.BusinessException;
import com.ploutos.model.Account;
import com.ploutos.model.Login;
import com.ploutos.model.Transaction;

public interface PloutosDAO { // C R U D
	///////////////////////////////////////////////////////////////////////////
	// Logins ////////////////////////////////////////////////////////////////
	// Create 
	public void insertLogin(Login login) throws BusinessException;
	// Read
	public Login getLogin(String username) throws BusinessException;
	//public Login getLogin(Account account);
	//public Login getLogin(Transaction transaction, boolean isSender);
	public List<Login> getLoginList() throws BusinessException;
	public List<Login> getPendingLoginList() throws BusinessException;
	//public List<Login> getLoginList(Transaction transaction);
	// Update
	public void updateLoginStatus(Login login, int status) throws BusinessException;
	// Delete

	///////////////////////////////////////////////////////////////////////////
	// Bank Accounts /////////////////////////////////////////////////////////
	// Create
	public Account insertAccount(Account account) throws BusinessException;
	// Read
	public Account getAccount(long accountNumber) throws BusinessException;
	//public Account getAccount(Transaction transaction, boolean isSender);
	public List<Account> getAccountList() throws BusinessException;
	public List<Account> getAccountList(Login login) throws BusinessException;
//	public List<Account> getAccountList(Transaction transaction);
	// Update
	public void updateAccountAmount(Account account, int amount) throws BusinessException;
	// Delete
	//public void deleteAccount(Account account);
	
	///////////////////////////////////////////////////////////////////////////
	// Transactions //////////////////////////////////////////////////////////
	// Create
	public Transaction insertTransaction(Transaction transaction) throws BusinessException;
	// Read
	//public Transaction getTransaction(int TransactionID);
	public List<Transaction> getTransactionList() throws BusinessException;
//	public List<Transaction> getTransactionList(Login login);
	// Update
//	public void updateTransactionStatus(Transaction transaction, int status);
//	public void approveTransaction(Transaction transaction);
//	public void rejectTransaction(Transaction transaction);
	// Delete
	// You cannot delete transactions because the IRS will get ANGRY
	
	public boolean isEmployee(String username, String password) throws BusinessException;
	public Login getLogin(String username, String password) throws BusinessException;
	List<Login> getLoginListActive() throws BusinessException;
}

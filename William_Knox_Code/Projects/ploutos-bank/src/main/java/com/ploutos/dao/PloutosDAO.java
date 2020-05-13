package com.ploutos.dao;

import java.util.List;
import java.util.Set;

import com.ploutos.exception.BusinessException;
import com.ploutos.model.Account;
import com.ploutos.model.Login;
import com.ploutos.model.Transaction;

public interface PloutosDAO { // C R U D  ////////////////////////////////////////
	  ///////////////////////////////////////////////////////////////////////////
	 /// Logins ////////////////////////////////////////////////////////////////
	/// Create 
	public void insertLogin(Login login) throws BusinessException; // create a login
	// Read
	public Login getLogin(String username, String password) throws BusinessException; // get a login
	public List<Login> getLoginList(int status) throws BusinessException; // list logins based on status
	public Set<String> getUsernameList() throws BusinessException;
	// Update
	public void updateLoginStatus(Login login, int status) throws BusinessException;
	// Delete
	public void deleteLogin(Login login) throws BusinessException;
	
	  ///////////////////////////////////////////////////////////////////////////
	 /// Bank Accounts /////////////////////////////////////////////////////////
	/// Create
	public Account insertAccount(Account account) throws BusinessException;
	// Read
	public Account getAccount(long accountNumber) throws BusinessException;
	public List<Account> getAccountList(Login login) throws BusinessException;
	// Update
	public void updateAccountAmount(Account account, int amount) throws BusinessException;
	
	  ///////////////////////////////////////////////////////////////////////////
	 /// Bank Accounts /////////////////////////////////////////////////////////
	/// Create
	public Transaction insertTransaction(Transaction transaction) throws BusinessException;
	// Read
	public List<Transaction> getTransactionList() throws BusinessException;
	
	  ///////////////////////////////////////////////////////////////////////////
	 /// Employees /////////////////////////////////////////////////////////////
	public boolean isEmployee(String username, String password) throws BusinessException;
	
	
}

package com.bhank.webapp.dao;

import java.util.List;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Account;
import com.bhank.webapp.model.Transaction;

public interface AccountDAO {
	
	// create
	Account createAccount(Account account) throws BusinessException;

	// update
	Account deposit(Account account, double amount) throws BusinessException;
	Account withdraw(Account account, double amount) throws BusinessException;
	Transaction postMoneyTransfer(Account fromAccount, Account toAccount, double amount) throws BusinessException;
	Transaction acceptMoneyTransfer(String transationId) throws BusinessException;
	Account approveAccount(Account account) throws BusinessException;
	Account declineAccount(Account account) throws BusinessException;
	

	// read
	List<Account> selectAllAccountsByCustomer(String customerId) throws BusinessException;

	Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException;

	Account selectAccountById(String id) throws BusinessException;

	// delete
	void deleteAccount(Account account) throws BusinessException;
}

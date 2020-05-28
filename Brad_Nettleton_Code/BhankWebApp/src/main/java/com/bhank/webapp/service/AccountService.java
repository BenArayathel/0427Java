package com.bhank.webapp.service;

import java.util.List;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Account;

public interface AccountService {

	// create
	Account createAccount(Account account) throws BusinessException;

	// update
	Account deposit(String id, double amount) throws BusinessException;
	Account withdraw(String id, double amount) throws BusinessException;
	Account postMoneyTransfer(Account fromAccount, Account toAccount, double amount) throws BusinessException;
	Account acceptMoneyTransfer(String transactionId) throws BusinessException;
	Account approveAccount(Account account) throws BusinessException;
	Account declineAccount(Account account) throws BusinessException;
	

	// read
	List<Account> selectAllAccountsByCustomer(String customerId) throws BusinessException;

	Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException;

	Account selectAccountById(String id) throws BusinessException;

	// delete
	void deleteAccount(Account account) throws BusinessException;
}

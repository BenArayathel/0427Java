package com.bhank.dao;

import java.util.List;

import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.Customer;

public interface AccountDAO {
	
	// create
	Account createAccount(Account account) throws BusinessException;

	// update
	Account deposit(Account account, double amount) throws BusinessException;
	Account withdraw(Account account, double amount) throws BusinessException;
	Account postMoneyTransfer(Account account, double amount) throws BusinessException;
	Account acceptMoneyTransfer(Account account) throws BusinessException;
	Account approveAccount(Account account) throws BusinessException;
	Account declineAccount(Account account) throws BusinessException;
	

	// read
	List<Account> selectAllAccountsByCustomer(String customerId) throws BusinessException;

	Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException;

	Account selectAccountById(String id) throws BusinessException;

	// delete
	void deleteAccount(Account account) throws BusinessException;
}

package com.bank.dao_interface;

import java.util.List;

import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.models.User;
import com.bank.tools.BankException;

public interface AccountDAOInterface {
	
	//abstract methods for interacting with my bank_account table in my db
	
	public Account createAccount(User user, String accountName, String depositAmount) throws BankException;
	public List<Account> listAccounts() throws BankException;
	public List<Account> listUserAccounts(String username) throws BankException;
	public void deposit(User user, String accountName, String depositAmount) throws BankException;
	public void withdraw(User user, String accountName, String depositAmount) throws BankException;
	public List<Transaction> listAllTransactions() throws BankException;
	
	//for cleaning up tests
	public void deleteTransaction(String account_name) throws BankException;
	public Account listAccountByID(String account_id) throws BankException;
	public Account listAccountByNameAndUserID(String account_name, String user_id) throws BankException;

	

}

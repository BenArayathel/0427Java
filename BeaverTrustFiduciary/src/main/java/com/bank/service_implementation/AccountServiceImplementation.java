package com.bank.service_implementation;

import java.util.List;

import com.bank.dao_implementation.AccountDAOImplementation;
import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.models.User;
import com.bank.service_interface.AccountServiceInterface;
import com.bank.tools.BankException;

public class AccountServiceImplementation implements AccountServiceInterface {
	
	private AccountDAOImplementation adi = new AccountDAOImplementation();

	// CREATE NEW USER ACCOUNT
	@Override
	public Account createAccount(User user, String accountName, String depositAmount) throws BankException {
		Account account = new Account();
		account = adi.createAccount(user, accountName, depositAmount);
		return account;
	}

	@Override
	public List<Account> listAccounts() throws BankException {
		List<Account> accounts = adi.listAccounts();
		return accounts;
	}

	@Override
	public List<Account> listUserAccounts(String username) throws BankException {
		List<Account> accountList = adi.listUserAccounts(username);
		return accountList;
	}

	// DEPOSIT INTO ACCOUNT
	@Override
	public void deposit(User user, String accountName, String depositAmount) {
		try {
			adi.deposit(user, accountName, depositAmount);
		} catch (BankException e) {
			e.printStackTrace();
		}
	}
	
	// WITHDRAW FROM ACCOUNT
	@Override
	public void withdraw(User user, String accountName, String depositAmount) {
		try {
			adi.withdraw(user, accountName, depositAmount);
		} catch (BankException e) {
			e.printStackTrace();
			
		}
	}

	// EMPLOYEE approves pending account application
	@Override
	public void approve(String user_id) throws BankException {
		adi.approve(user_id);
	}

	
	//LIST ALL TRANSACTIONS for EMPLOYEE
	@Override
	public List<Transaction> listAllTransactions() throws BankException {
		List<Transaction> transactionList = adi.listAllTransactions();
		return transactionList;
	}
	
	

}

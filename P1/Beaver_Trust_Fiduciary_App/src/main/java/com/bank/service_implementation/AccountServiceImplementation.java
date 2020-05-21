package com.bank.service_implementation;

import java.util.List;

import com.bank.dao_implementation.AccountDAOImplementation;
import com.bank.main.Main;
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
	public void deposit(User user, String accountName, String depositAmount) throws BankException {
		try {
			adi.deposit(user, accountName, depositAmount);
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Problem with Deposit");
		} catch (NumberFormatException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("That number isn't formatted correctly");
		}
	}
	
	// WITHDRAW FROM ACCOUNT
	@Override
	public void withdraw(User user, String accountName, String depositAmount) throws BankException {
		try {
			adi.withdraw(user, accountName, depositAmount);
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Problem with withdrawal");
		}
	}

	
	//LIST ALL TRANSACTIONS for EMPLOYEE
	@Override
	public List<Transaction> listAllTransactions() throws BankException {
		List<Transaction> transactionList = adi.listAllTransactions();
		return transactionList;
	}
	
	//CHECK THAT TRANSACTIONS ARE IN CORRECT FORMAT
	public boolean validTransactionFormat(String transaction) throws BankException {
		return (transaction.matches("^(([1-9]\\d{0,2}(d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$"));
//		^[0-9]+(\.[0-9]{1,2})?$
	}

	@Override
	public Account listAccountByNameAndUserID(String account_name, String user_id) throws BankException {
		Account account = adi.listAccountByNameAndUserID(account_name, user_id);
		return account;
	}
	
	

}

package com.bank.service_implementation;

import java.util.List;

import com.bank.dao_implementation.AccountDAOImplementation;
import com.bank.models.Account;
import com.bank.service_interface.AccountServiceInterface;
import com.bank.tools.BankException;

public class AccountServiceImplementation implements AccountServiceInterface {
	
	private AccountDAOImplementation adi = new AccountDAOImplementation();

	@Override
	public Account createAccount(Account account) throws BankException {
		adi.createAccount(account);
		return null;
	}

	@Override
	public List<Account> listAccounts() throws BankException {
		adi.listAccounts();
		return null;
	}

	@Override
	public List<Account> listUserAccounts(String username) throws BankException {
		List<Account> accountList = adi.listUserAccounts(username);
		return accountList;
	}

	// DEPOSIT INTO ACCOUNT
	@Override
	public void deposit(String username, String accountName, String depositAmount) {
		try {
			adi.deposit(username, accountName, depositAmount);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// WITHDRAW FROM ACCOUNT
	@Override
	public void withdraw(String username, String accountName, String depositAmount) {
		try {
			adi.withdraw(username, accountName, depositAmount);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

package com.bank.service_implementation;

import java.util.List;

import com.bank.models.Account;
import com.bank.service_interface.AccountServiceInterface;
import com.bank.tools.BankException;

public class AccountServiceImplementation implements AccountServiceInterface {

	@Override
	public Account createAccount(Account account) throws BankException {
		// TODO Auto-generated method stub
		System.out.println("service imp running");
		return null;
	}

	@Override
	public List<Account> listAccounts() throws BankException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

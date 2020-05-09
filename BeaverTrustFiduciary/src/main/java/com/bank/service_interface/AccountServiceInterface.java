package com.bank.service_interface;

import java.util.List;

import com.bank.models.Account;
import com.bank.tools.BankException;


public interface AccountServiceInterface {
		
	public Account createAccount(Account account) throws BankException;
	public List<Account> listAccounts() throws BankException;
	public List<Account> listUserAccounts(String username) throws BankException;


}

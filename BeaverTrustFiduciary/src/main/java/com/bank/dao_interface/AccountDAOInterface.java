package com.bank.dao_interface;

import java.util.List;

import com.bank.models.Account;
import com.bank.tools.BankException;

public interface AccountDAOInterface {
	
	//abstract methods for interacting with my bank_account table in my db
	
	public Account createAccount(Account account) throws BankException;
	public List<Account> listAccounts() throws BankException;
	

}

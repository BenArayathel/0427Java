package com.company.dao;

import java.util.List;

import com.company.model.Account;

public interface AccountDao {
	
	Account addAccount(Account account);
	
	Account getAccount(String accountId);
	
	List<Account> getAllAccounts();
	
	void updateAccount(Account account);
	
	void deleteAccount(String accountId);
	

}

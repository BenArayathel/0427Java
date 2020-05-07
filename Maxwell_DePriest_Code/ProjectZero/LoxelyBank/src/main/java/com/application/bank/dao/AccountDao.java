package com.application.bank.dao;
import java.util.List;

import com.application.bank.models.Account;

public interface AccountDao {
	
	public void insertAccount(Account a);
	
	public Account selectAccount();
	public List<Account> selectAllAccounts();
	
	public void updateAccount();
	
	public void deleteAccount();

}


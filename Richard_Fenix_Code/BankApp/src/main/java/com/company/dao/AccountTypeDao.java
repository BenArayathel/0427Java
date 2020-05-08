package com.company.dao;

import java.util.List;

import com.company.model.AccountType;

public interface AccountTypeDao {

	AccountType addAccountType(AccountType accountType);

	AccountType getAccountType(int id);
	
	List<AccountType> getAllAccountType();
	
	void updateAccountType(AccountType accountType);
	
	void deleteAccountType(int id);
	
}

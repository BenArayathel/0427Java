package com.application.bank.dao;
import java.util.List;

import com.application.bank.exception.BusinessException;
import com.application.bank.models.Account;

public interface AccountDao {
	
	public Account insertAccount(Account a) throws BusinessException;
	
	public Account selectAccount() throws BusinessException;
	public List<Account> selectAllAccounts() throws BusinessException;
	
	public Account updateAccount(Account a) throws BusinessException;
	
	public void deleteAccount() throws BusinessException;

}


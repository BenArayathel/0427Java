package com.application.bank.dao;
import java.util.List;

import com.application.bank.exception.BusinessException;
import com.application.bank.models.Account;

public interface AccountDao {
	
	public Account insertAccount(Account a) throws BusinessException;
	
	public Account selectAccountByEmail(String email) throws BusinessException;
	public List<Account> selectAllAccounts() throws BusinessException;
	
	public void updateAccount(String email, String columnName, String newAtt) throws BusinessException;
	
	public void deleteAccount(String email) throws BusinessException;
	
	public void deleteAllAccounts() throws BusinessException;

}


package com.application.bank.services.impl;

import java.util.Random;

import org.apache.log4j.Logger;

import com.application.bank.dao.impl.AccountDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.Account;
import com.application.bank.services.AccountService;

public class AccountServiceImpl implements AccountService {
	final static Logger loggy = Logger.getLogger(Account.class);
	AccountDaoImpl aDI = new AccountDaoImpl();

	public AccountServiceImpl() {
		
	}

	@Override
	public void createNewAccount(Account acc) throws BusinessException {
		aDI.insertAccount(acc);
		
	}
	

	


}

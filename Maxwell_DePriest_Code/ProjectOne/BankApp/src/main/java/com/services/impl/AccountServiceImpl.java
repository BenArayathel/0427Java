package com.services.impl;

import java.util.Random;

import org.apache.log4j.Logger;

import com.dao.impl.AccountDaoImpl;
import com.exceptions.BusinessException;
import com.models.Account;
import com.services.AccountService;

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

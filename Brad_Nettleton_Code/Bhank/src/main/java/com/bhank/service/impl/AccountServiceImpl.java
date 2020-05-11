package com.bhank.service.impl;

import java.util.List;

import com.bhank.dao.impl.AccountDAOImpl;
import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.service.AccountService;

public class AccountServiceImpl implements AccountService {

	AccountDAOImpl dao = new AccountDAOImpl();
	
	@Override
	public Account createAccount(Account account) throws BusinessException {
		if(account==null) {
			throw new BusinessException("Account object was not created");
		} else if(account.getBalance()<0) {
			throw new BusinessException("Starting balance is less than 0");
		} else {
			dao.createAccount(account);
		}
		return null;
	}

	@Override
	public Account deposit(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdraw(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account postMoneyTransfer(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account acceptMoneyTransfer(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccounts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountById(String id) throws BusinessException {
		Account account = new Account();
		if(id==null) {
			throw new BusinessException("id is null");
		} else if(!isValidId(id)) {
			throw new BusinessException("id is invalid");
		} else {
			account = dao.selectAccountById(id);
		}
		return account;
	}

	private boolean isValidId(String id) {
		boolean b=false;
		if(id.matches("ACCU[a-zA-Z]{2}[0-9]{5}")) {
			b=true;
		}
		return b;
	}

	@Override
	public void deleteAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}

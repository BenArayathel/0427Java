package com.hackbank.business.services.account;

import java.util.List;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.validations.Validation;
import com.hackbank.persistence.dao.account.AccountDAO;
import com.hackbank.persistence.dao.account.AccountDAOImpl;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.PendingApproval;

public class AccountServiceImpl implements AccountService{

	private AccountDAO accountDAO = new AccountDAOImpl();
	private Validation valid = new Validation();
	
	@Override
	public Account createAccount(PendingApproval pApproval) throws BusinessException {
		Account iAccount = null;
		if(pApproval == null) {
			throw new BusinessException();
		}else {
			iAccount = accountDAO.createAccount(pApproval);
		}
		return iAccount;
	}

	@Override
	public String getAccountById(int id) throws BusinessException {
		if (valid.isValidIntId(id)) {
			return accountDAO.getAccountById(id);
		}else {
			throw new BusinessException("This is not a valid id.");
		}
		
	}

	@Override
	public Account updateBalanceAccount(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccountsByCustomer(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}

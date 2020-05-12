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
	public String getPersonAccountById(String id) throws BusinessException {
//		if (valid.isValidIntId(id)) {
//			return accountDAO.getPersonAccountById(id);
//		}else {
//			throw new BusinessException("This is not a valid id.");
//		}
		return accountDAO.getPersonAccountById(id);
	}

	@Override
	public Account updateBalanceAccount(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccountsByCustomer(String id) throws BusinessException {
//		if (valid.isValisPersonId())
		List<Account> listAccount = accountDAO.getAllAccountsByCustomer(id);
		return listAccount;
	}

	@Override
	public Account getAccountById(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}

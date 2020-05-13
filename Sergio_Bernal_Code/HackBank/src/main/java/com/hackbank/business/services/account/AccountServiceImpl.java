package com.hackbank.business.services.account;

import java.text.DecimalFormat;
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
	public List<Account> getAllAccountsByCustomer(String id) throws BusinessException {
		List<Account> listAccount = accountDAO.getAllAccountsByCustomer(id);
		return listAccount;
	}

	@Override
	public Account getAccountById(String id) throws BusinessException {
		return accountDAO.getAccountById(id);
	}
	
	@Override
	public Account depositBalanceAccount(Account account, double balance) throws BusinessException {
		Account iAccount = null;
		try {
			double nBalance = valid.isValidBalance(balance);
			double addBalance = Double.parseDouble(new DecimalFormat("0.00").format(account.getBalance() + nBalance));
			iAccount = accountDAO.updateBalanceAccount(account.getId(), addBalance);
		} catch (BusinessException e) {
			throw new BusinessException("The Bank doesn't allow you to have negative balance, we're sorry.");
		}
		return iAccount;
	}

	@Override
	public Account withdrawBalanceAccount(Account account, double balance) throws BusinessException {
		Account iAccount = null;
		try {
			double nBalance = valid.isValidBalance(balance);
			double subBalance = Double.parseDouble(new DecimalFormat("0.00").format(account.getBalance() - nBalance));
			if (subBalance >= 0) {
				iAccount = accountDAO.updateBalanceAccount(account.getId(), subBalance);
			}else {
				throw new BusinessException("The BANK doesn't allow you to have negative balance, we're sorry.");
			}
		} catch (BusinessException e) {
			throw new BusinessException("The BANK doesn't allow you to have negative balance, we're sorry.");
		}
		return iAccount;
	}

	@Override
	public boolean getAccountByIdAndRoutingNumber(String id, String routingNumber) throws BusinessException {
		return accountDAO.getAccountByIdAndRoutingNumber(id, routingNumber);
	}

}

package com.hackbank.persistence.dao.account;

import java.util.List;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.PendingApproval;

public interface AccountDAO {
	public abstract Account createAccount(PendingApproval pApproval) throws BusinessException;
	public abstract String getAccountById(int id) throws BusinessException;
	public abstract Account updateBalanceAccount(int id) throws BusinessException;
	public abstract List<Account> getAllAccountsByCustomer(String id) throws BusinessException;
}

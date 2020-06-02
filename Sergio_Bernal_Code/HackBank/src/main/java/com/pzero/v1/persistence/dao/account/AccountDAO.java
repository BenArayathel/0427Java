package com.pzero.v1.persistence.dao.account;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.PendingApproval;

public interface AccountDAO {
	
	public abstract Account createAccount(PendingApproval pApproval) throws BusinessException;
	public abstract Account getAccountById(String id) throws BusinessException;
	public abstract boolean getAccountByIdAndRoutingNumber(String id, String routingNumber) throws BusinessException;
	public abstract String getPersonAccountById(String id) throws BusinessException;
	public abstract Account updateBalanceAccount(String id, double balance) throws BusinessException;
	public abstract List<Account> getAllAccountsByCustomer(String id) throws BusinessException;
	
}

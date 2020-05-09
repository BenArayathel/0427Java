package com.application.bank.services;

import com.application.bank.exception.BusinessException;
import com.application.bank.models.Account;

public interface AccountService {
	public void createNewAccount(Account acc) throws BusinessException;

}

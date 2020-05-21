package com.services;

import com.exceptions.BusinessException;
import com.models.Account;

public interface AccountService {
	public void createNewAccount(Account acc) throws BusinessException;

}

package com.banking.service;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

public interface AccountService {

    Account createAccount(Account account) throws BusinessException;
    Account getAccount(Account account) throws BusinessException;
    Account updateBalance(Account account) throws BusinessException;
    void deleteAccount(Account account) throws BusinessException;

}

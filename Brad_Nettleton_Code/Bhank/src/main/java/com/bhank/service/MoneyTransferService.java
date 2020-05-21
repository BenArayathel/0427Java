package com.bhank.service;import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.MoneyTransfer;

public interface MoneyTransferService {

	MoneyTransfer postMoneyTransfer(Account fromAccount, Account toAccount, double amount) throws BusinessException;

	MoneyTransfer selectMoneyTransferByAccount(String id) throws BusinessException;

}

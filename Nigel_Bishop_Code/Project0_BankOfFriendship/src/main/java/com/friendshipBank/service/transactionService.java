package com.friendshipBank.service;

import java.util.List;

import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.transaction;

public interface transactionService 
{
	public transaction createNewBankTransaction(transaction transaction)throws BusinessException;
	public List<transaction> getTransactionByAccountID(String aID) throws BusinessException;


}

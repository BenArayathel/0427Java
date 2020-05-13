package com.friendshipBank.service;

import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.transaction;

public interface transactionService 
{
	public transaction createNewBankTransaction(transaction transaction)throws BusinessException;

}

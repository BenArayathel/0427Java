package com.friendshipBank.dao;

import java.util.List;

import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.transaction;

public interface transactionLogDAO 
{
	public transaction createNewBankTransaction(transaction transaction)throws BusinessException;
	public List<transaction> getAllBankTransaction() throws BusinessException;


}

package com.bhank.webapp.service;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Transaction;

public interface TransactionService {

	Transaction postTransaction(Transaction transaction) throws BusinessException;
	
	Transaction acceptTransaction(Transaction transaction) throws BusinessException;
	Transaction declineTransaction(Transaction transaction) throws BusinessException;
	
	Transaction selectTransactionsByAccount(String id) throws BusinessException;
	Transaction selectAllTransactions() throws BusinessException;
	
}

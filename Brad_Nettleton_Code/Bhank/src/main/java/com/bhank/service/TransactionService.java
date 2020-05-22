package com.bhank.service;import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.Transaction;

public interface TransactionService {

	Transaction postTransaction(Transaction transaction) throws BusinessException;
	
	Transaction acceptTransaction(Transaction transaction) throws BusinessException;
	Transaction declineTransaction(Transaction transaction) throws BusinessException;
	
	Transaction selectTransactionsByAccount(String id) throws BusinessException;
	Transaction selectAllTransactions() throws BusinessException;
	
}

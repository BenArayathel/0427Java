package com.bhank.webapp.dao;

import java.util.List;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Transaction;

public interface TransactionDAO {

	Transaction createTransaction(Transaction transaction) throws BusinessException;
	
	Transaction acceptTransaction(Transaction transaction) throws BusinessException;
	Transaction declineTransaction(Transaction transaction) throws BusinessException;
	
	List<Transaction> selectTransactionsByAccount(String id) throws BusinessException;
	List<Transaction> selectAllTransactions() throws BusinessException;
}

package com.bhank.webapp.dao;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Transaction;

public interface TransactionDAO {

	Transaction postTransaction(Transaction transaction) throws BusinessException;
	
	Transaction acceptTransaction(Transaction tracaction) throws BusinessException;
	Transaction declineTransaction(Transaction trancaction) throws BusinessException;
	
	Transaction selectTransactionsByAccount(String id) throws BusinessException;
	Transaction selectAllTransactions() throws BusinessException;
}

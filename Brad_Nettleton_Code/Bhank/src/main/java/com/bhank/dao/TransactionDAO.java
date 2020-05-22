package com.bhank.dao;

import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.Transaction;

public interface TransactionDAO {

	Transaction postTransaction(Transaction transaction) throws BusinessException;
	
	Transaction acceptTransaction(Transaction tracaction) throws BusinessException;
	Transaction declineTransaction(Transaction trancaction) throws BusinessException;
	
	Transaction selectTransactionsByAccount(String id) throws BusinessException;
	Transaction selectAllTransactions() throws BusinessException;
}

package com.company.dao;

import java.util.List;

import com.company.model.TransactionType;

public interface TransactionTypeDao {
	
	TransactionType addTransactionType(TransactionType transactionType);
	
	TransactionType getTransactionType(int id);
	
	List<TransactionType> getAllTransactionTypes(); 

	void updateTransactionType(TransactionType transactionType);
	
	void deleteTransactionType(int id);
}

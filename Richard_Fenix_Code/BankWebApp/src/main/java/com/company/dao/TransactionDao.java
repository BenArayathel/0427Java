package com.company.dao;

import java.util.List;

import com.company.model.Transaction;

public interface TransactionDao {
	
	Transaction addTransaction(Transaction transaction);
	
	Transaction getTransaction(long id);
	
	List<Transaction> getAllTransactions();
	
	void updateTransaction(Transaction transaction);
	
	void deleteTransaction(long id);
	

}

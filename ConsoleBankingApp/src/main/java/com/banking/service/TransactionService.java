package com.banking.service;

import com.banking.exception.BusinessException;
import com.banking.models.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(Transaction transaction) throws BusinessException;
    List<Transaction> getAllTransactions() throws BusinessException;
    void deleteTransaction(Transaction transaction);
}

package com.bhank.webapp.service.impl;

import java.util.List;

import com.bhank.webapp.dao.impl.TransactionDAOImpl;
import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.main.Main;
import com.bhank.webapp.model.Transaction;
import com.bhank.webapp.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	
	TransactionDAOImpl dao = new TransactionDAOImpl();

	@Override
	public Transaction createTransaction(Transaction transaction) throws BusinessException {
		if(transaction.getFromAccountId() == null || transaction.getToAccountId() == null) {
			Main.logger.error("null account id for fromAccount: "+transaction.getFromAccountId()+" or toAccount: "+transaction.getToAccountId());
			throw new BusinessException("from or to account id is null");
		} else if (transaction.isDeposit() && transaction.getTransferAmount() <= 0) {
			Main.logger.error("Cannot transfer $0 or less");
			throw new BusinessException("transaction is less than or equal to 0, amount=\'"+transaction.getTransferAmount()+"\'");
		} else {
			transaction = dao.createTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public Transaction acceptTransaction(Transaction transaction) throws BusinessException {
		if(transaction == null) {
			throw new BusinessException("Transaction object is null");
		} else {
			dao.acceptTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public Transaction declineTransaction(Transaction transaction) throws BusinessException {
		if(transaction == null) {
			throw new BusinessException("Transaction object is null");
		} else {
			dao.declineTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public List<Transaction> selectTransactionsByAccount(String fromAccountId) throws BusinessException {
		if(fromAccountId==null) {
			throw new BusinessException("Transaction id object is null");
		} else {
			return dao.selectTransactionsByAccount(fromAccountId);
		}
	}

	@Override
	public List<Transaction> selectAllTransactions() throws BusinessException {
		return dao.selectAllTransactions();
	}
}

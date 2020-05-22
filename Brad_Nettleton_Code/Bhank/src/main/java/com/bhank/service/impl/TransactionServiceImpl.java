package com.bhank.service.impl;

import com.bhank.dao.impl.TransactionDAOImpl;
import com.bhank.exception.BusinessException;
import com.bhank.main.Main;
import com.bhank.model.Account;
import com.bhank.model.Transaction;
import com.bhank.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	
	TransactionDAOImpl dao = new TransactionDAOImpl();

	@Override
	public Transaction postTransaction(Transaction transaction) throws BusinessException {
		if(transaction.getFromAccountId() == null || transaction.getToAccountId() == null) {
			Main.logger.error("null account id for fromAccount: "+transaction.getFromAccountId()+" or toAccount: "+transaction.getToAccountId());
			throw new BusinessException("from or to account id is null");
		} else if (transaction.isDeposit() && transaction.getTransferAmount() <= 0) {
			Main.logger.error("Cannot transfer $0 or less");
			throw new BusinessException("transaction is less than or equal to 0, amount=\'"+transaction.getTransferAmount()+"\'");
		} else {
			transaction = dao.postTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public Transaction acceptTransaction(Transaction transaction) {
			throws BusinessException {
		if(transaction == null) {
			throw new BusinessException("Transactdeion object is null");
		} else {
			dao.acceptTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public Transaction declineTransaction(Transaction transaction) {
			throws BusinessException {
		if(transaction == null) {
			throw new BusinessException("Transactdeion object is null");
		} else {
			dao.declineTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public Transaction selectTransactionsByAccount(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction selectAllTransactions() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}

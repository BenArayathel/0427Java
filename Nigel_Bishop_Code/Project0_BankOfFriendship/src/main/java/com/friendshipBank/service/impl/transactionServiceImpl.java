package com.friendshipBank.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.dao.transactionLogDAO;
import com.friendshipBank.dao.impl.transactionLogDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.transaction;
import com.friendshipBank.service.transactionService;

public class transactionServiceImpl implements transactionService{

	private transactionLogDAO transDAO = new transactionLogDAOImpl();
	
	private boolean isValidAccountId(String id) {
		boolean b = false;
		if (id.matches("FBAC[0-9]{5}")) {
			b = true;
		}
		return b;
	}
	
	@Override
	public transaction createNewBankTransaction(transaction transaction) throws BusinessException {
		if (transaction == null) {
			throw new BusinessException("SYSTEM: TRANSACTION OBJECT WAS NOT CREATED");
		} else if (!isValidAccountId(transaction.getAccountID())) {
			throw new BusinessException("SYSTEM: THE ENTERED ACCOUNT ID " + transaction.getAccountID() + " IS INVALID");
		} else {
			transaction = transDAO.createNewBankTransaction(transaction);
		}
		return transaction;
	}

	@Override
	public List<transaction> getTransactionByAccountID(String aID) throws BusinessException
	{
		List<transaction> transList = new ArrayList<>();
		if(isValidAccountId(aID)) {
			transList = transDAO.getTransactionByAccountID(aID);
		}else {
			throw new BusinessException("The entered ACCOUNTID is invalid");
		}
		return transList;
	}

//	@Override
//	public transaction getTransactionByAccountID(String aID) throws BusinessException {
//		transaction transaction = null;
//		if(isValidAccountId(aID)) {
//			transaction = transDAO.getTransactionByAccountID(aID);
//		}else {
//			throw new BusinessException("The entered ACCOUNTID is invalid");
//		}
//		return transaction;
//		
//		List<customer> customerList = new ArrayList<>();
//		if(isValidCity(state)) {
//			customerList = (List<customer>) cusDAO.getCustomerByCity(state) ;
//		}else {
//			throw new BusinessException("The entered STATE " + state + " is invalid");
//		}
//		return customerList;
//	}

}

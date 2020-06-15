package com.friendshipBank.service.impl;


import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dao.impl.bankAccountDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.service.bankAccountService;

public class bankAccountServiceImpl implements bankAccountService{


	private bankAccountDAO bankDAO = new bankAccountDAOImpl();
	
	private boolean isValidCustomerId(String id) {
		boolean b = false;
		if (id.matches("FB[A-Z]{2}[0-9]{9}")) {
			b = true;
		}
		return b;
	}
	
//	FBAC10011
	private boolean isValidAccountId(String id) {
		boolean b = false;
		if (id.matches("FBAC[0-9]{5}")) {
			b = true;
		}
		return b;
	}

	@Override
	public bankAccount createNewBankAccount(bankAccount bankAccount) throws BusinessException {
		if (bankAccount == null) {
			throw new BusinessException("SYSTEM: BANK ACCOUNT OBJECT WAS NOT CREATED");
		} else if (!isValidCustomerId(bankAccount.getCustomerID())) {
			throw new BusinessException("SYSTEM: THE ENTERED CUSTOMERID " + bankAccount.getCustomerID() + " IS INVALID");
		} else {
			bankAccount = bankDAO.createNewBankAccount(bankAccount);			
		}
		return bankAccount;
	}

	@Override
	public bankAccount getAccountInfo(String cId, String aType) throws BusinessException {
		bankAccount bankAccount = null;

		if(isValidCustomerId(cId)) {
			bankAccount = bankDAO.getAccountInfo(cId, aType);
		}else {
			throw new BusinessException("SYSTEM: THE ENTERED CUSTOMERID " + cId + " IS INVALID");
		}
		return bankAccount;
	}
	
	@Override
	public bankAccount getAccountInfoByAccountID(String aId) throws BusinessException {
		bankAccount bankAccount = null;

		if(isValidAccountId(aId)) {
			bankAccount = bankDAO.getAccountInfoByAccountID(aId);
		}else {
			throw new BusinessException("SYSTEM: THE ENTERED ACCOUNT ID " + aId + " IS INVALID");
		}
		return bankAccount;
	}

	@Override
	public void deleteBankAccount(String cId, String aType) throws BusinessException {
		if(isValidCustomerId(cId)) {
			bankDAO.deleteBankAccount(cId, aType);
		}else {
			throw new BusinessException("SYSTEM: THE ENTERED CUSTOMERID " + cId + " IS INVALID");
		}
		
	}

	@Override
	public void updateBankAccountBalance(String cId, String aType, Double aBalance) throws BusinessException {
		if(isValidCustomerId(cId)) {
			bankDAO.updateBankAccountBalance(cId, aType, aBalance);
		}else {
			throw new BusinessException("SYSTEM: THE ENTERED CUSTOMERID " + cId + " IS INVALID");
		}
		
	}

	@Override
	public void updateBankAccountStatus(String cId, String aType, String aStatus) throws BusinessException {
		if(isValidCustomerId(cId)) {
			bankDAO.updateBankAccountStatus(cId, aType, aStatus);
		}else {
			throw new BusinessException("SYSTEM: THE ENTERED CUSTOMERID " + cId + " IS INVALID");
		}
		
	}

	@Override
	public void updateByTransfer(String aId, Double aBalance) throws BusinessException {
		if(isValidAccountId(aId)) {
			bankDAO.updateByTransfer(aId, aBalance);
		}else {
			throw new BusinessException("SYSTEM: THE ENTERED ACCOUNTR ID " + aId + " IS INVALID");
		}
	}




}

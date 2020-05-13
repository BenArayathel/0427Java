package com.friendshipBank.service;

import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;

public interface bankAccountService 
{
	public bankAccount createNewBankAccount(bankAccount bankAccount)throws BusinessException;
	public bankAccount getAccountInfo(String cId, String aType) throws BusinessException;
	public void deleteBankAccount(String cId, String aType) throws BusinessException;
	public void updateBankAccountBalance(String cId, String aType, Double aBalance) throws BusinessException;
	public void updateBankAccountStatus(String cId, String aType, String aStatus) throws BusinessException;
	public void updateByTransfer(String aId, Double aBalance) throws BusinessException;
	public bankAccount getAccountInfoByAccountID(String aId) throws BusinessException;

	
	

}

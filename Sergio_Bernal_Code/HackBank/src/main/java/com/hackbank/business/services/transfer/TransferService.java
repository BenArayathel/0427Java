package com.hackbank.business.services.transfer;

import java.util.List;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.Transfer;

public interface TransferService {

	public abstract boolean createTransfer(Transfer transfer, Account account) throws BusinessException;
	public abstract List<Transfer> transferList(String id) throws BusinessException;
	public abstract boolean acceptTransfer(Transfer transfer) throws BusinessException;
	
}

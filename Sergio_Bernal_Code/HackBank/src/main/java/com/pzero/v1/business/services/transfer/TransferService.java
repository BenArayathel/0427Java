package com.pzero.v1.business.services.transfer;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.Transfer;

public interface TransferService {

	public abstract boolean createTransfer(Transfer transfer, Account account) throws BusinessException;
	public abstract List<Transfer> transferList(String id) throws BusinessException;
	public abstract boolean acceptTransfer(Transfer transfer, Account account) throws BusinessException;
	
}

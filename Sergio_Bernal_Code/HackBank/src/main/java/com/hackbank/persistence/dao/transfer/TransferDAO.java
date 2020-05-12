package com.hackbank.persistence.dao.transfer;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Transfer;

public interface TransferDAO {
	
	public abstract Transfer createTransfer(Transfer transfer) throws BusinessException;
	public abstract boolean acceptTransfer(Transfer transfer) throws BusinessException;

}

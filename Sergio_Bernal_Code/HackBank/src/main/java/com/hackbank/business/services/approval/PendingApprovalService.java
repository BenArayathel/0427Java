package com.hackbank.business.services.approval;

import java.util.List;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.PendingApproval;

public interface PendingApprovalService {
	
	public Account createApproval(PendingApproval pApproval) throws BusinessException;
	public Account updateApproval(PendingApproval pApproval) throws BusinessException;
	public abstract List<PendingApproval> listApproval() throws BusinessException;

}

package com.hackbank.business.services.approval;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.PendingApproval;

public interface PendingApprovalService {
	
	public Account createApproval(PendingApproval pApproval) throws BusinessException;

}

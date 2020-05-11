package com.hackbank.persistence.dao.approval;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.PendingApproval;

public interface PendingApprovalDAO {

	public abstract void createApproval(PendingApproval pApproval) throws BusinessException;
	
}

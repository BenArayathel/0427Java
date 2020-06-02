package com.pzero.v1.business.services.approval;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.PendingApproval;

public interface PendingApprovalService {
	
	public Account createApproval(PendingApproval pApproval) throws BusinessException;
	public Account updateApproval(PendingApproval pApproval) throws BusinessException;
	public abstract List<PendingApproval> listApproval() throws BusinessException;

}

package com.hackbank.persistence.dao.approval;

import java.util.List;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.PendingApproval;

public interface PendingApprovalDAO {

	public abstract void createApproval(PendingApproval pApproval) throws BusinessException;
	public abstract boolean updateApproval(PendingApproval pApproval) throws BusinessException;
	public abstract List<PendingApproval> listApproval() throws BusinessException;
	
}

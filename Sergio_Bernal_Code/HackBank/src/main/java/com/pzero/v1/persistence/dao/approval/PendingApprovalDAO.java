package com.pzero.v1.persistence.dao.approval;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.PendingApproval;

public interface PendingApprovalDAO {

	public abstract boolean createApproval(PendingApproval pApproval) throws BusinessException;
	public abstract boolean updateApproval(PendingApproval pApproval) throws BusinessException;
	public abstract List<PendingApproval> listApproval() throws BusinessException;
	
}

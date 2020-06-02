package com.pzero.v1.business.services.approval;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.account.AccountService;
import com.pzero.v1.business.services.account.AccountServiceImpl;
import com.pzero.v1.persistence.dao.approval.PendingApprovalDAO;
import com.pzero.v1.persistence.dao.approval.PendingApprovalDAOImpl;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.PendingApproval;

public class PendingApprovalSrvImpl implements PendingApprovalService{

	private PendingApprovalDAO pApprovalDAO = new PendingApprovalDAOImpl();
	private AccountService accountSrv = new AccountServiceImpl();
	
	@Override
	public Account createApproval(PendingApproval pApproval) throws BusinessException{
		Account iAccount = null;
		if(pApproval == null) {
			throw new BusinessException("Object Error, please contact SYSADMIN.");
		}else {
			pApprovalDAO.createApproval(pApproval);
			if(pApproval.getStatus().equals("Approve")) {
				iAccount = accountSrv.createAccount(pApproval);
			}
		}
		return iAccount;
	}

	@Override
	public List<PendingApproval> listApproval() throws BusinessException { 
		return pApprovalDAO.listApproval();
	}

	@Override
	public Account updateApproval(PendingApproval pApproval) throws BusinessException {
		Account iAccount = null;
		if(pApproval == null) {
			throw new BusinessException("Object Error, please contact SYSADMIN.");
		}else {
			boolean flag = pApprovalDAO.updateApproval(pApproval);
			if (flag) {
				if(pApproval.getStatus().equals("Approve")) {
					iAccount = accountSrv.createAccount(pApproval);
				}
			}
		}
		return iAccount;
		
	}
	
}

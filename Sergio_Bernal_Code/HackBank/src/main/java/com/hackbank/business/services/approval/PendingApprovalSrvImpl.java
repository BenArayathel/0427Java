package com.hackbank.business.services.approval;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.services.account.AccountService;
import com.hackbank.business.services.account.AccountServiceImpl;
import com.hackbank.persistence.dao.approval.PendingApprovalDAO;
import com.hackbank.persistence.dao.approval.PendingApprovalDAOImpl;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.PendingApproval;

public class PendingApprovalSrvImpl implements PendingApprovalService{

	private PendingApprovalDAO pApprovalDAO = new PendingApprovalDAOImpl();
	private AccountService accountSrv = new AccountServiceImpl();
	
	@Override
	public Account createApproval(PendingApproval pApproval) throws BusinessException{
		Account iAccount = null;
		if(pApproval == null) {
			throw new BusinessException();
		}else {
			pApprovalDAO.createApproval(pApproval);
			if(pApproval.getStatus().equals("Approve")) {
				iAccount = accountSrv.createAccount(pApproval);
			}
		}
		return iAccount;
	}
	
}

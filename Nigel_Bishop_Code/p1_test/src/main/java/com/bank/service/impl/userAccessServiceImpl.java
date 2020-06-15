package com.bank.service.impl;

import com.bank.dao.userAccessDAO;
import com.bank.dao.impl.userAccessDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.userAccess;
import com.bank.service.userAccessService;

public class userAccessServiceImpl implements userAccessService{
	
	private userAccessDAO userDAO = new userAccessDAOImpl();
	
	
//	private boolean isValidUserID(String id) {
//		boolean b = false;
//		if (id.matches("FBUA[A-Z]{4}[0-9]{5}")) {
//			b = true;
//		}
//		return b;
//	}
	
	private boolean isValidUserName(String uname) {
		boolean b = false;
		if (uname.matches("[a-zA-Z0-9]{8,30}")) {
			b = true;
		}
		return b;
	}
	
	private boolean isValidUserPassword(String uPass) {
		boolean b = false;
		if (uPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
			b = true;
		}
		return b;
	}
	
	private boolean isValidCustomerId(String id) {
		boolean b = false;
		if (id.matches("FB[A-Z]{2}[0-9]{9}")) {
			b = true;
		}
		return b;
	}

	@Override
	public userAccess createUserAccess(userAccess userAccess) throws BusinessException {
		if (userAccess == null) {
			throw new BusinessException("USERACCESS Object was not created");
		} else if (!isValidUserName(userAccess.getUserName())) {
			throw new BusinessException("The entered USERNAME " + userAccess.getUserName() + " is invalid");
		} else if (!isValidUserPassword(userAccess.getUserPassword())) {
			throw new BusinessException("The entered PASSWORD is invalid");
		} else {
			userAccess = userDAO.createUserAccess(userAccess);
		}
		return userAccess;
	}

	@Override
	public userAccess loginByUsernameAndPassword(String uName, String uPassword) throws BusinessException {
		userAccess userAccess = null;
		if(isValidUserName(uName) && isValidUserPassword(uPassword)) {
			userAccess = userDAO.loginByUsernameAndPassword(uName, uPassword);
		}else {
			throw new BusinessException("The entered USERNAME or PASSWORD is invalid");
		}
		return userAccess;
	}

	@Override
	public userAccess updateUserPassword(String cId, String newPassword) throws BusinessException {
		userAccess userAccess = null;
		if(isValidCustomerId(cId)) {
			userAccess = userDAO.updateUserPassword(cId, newPassword);
		}else {
			throw new BusinessException("The entered CUSTOMER ID " + cId + " is invalid");
		}
		return userAccess;
	}

	@Override
	public void deleteUserAccess(String cId) throws BusinessException {
		if(isValidCustomerId(cId)) {
			userDAO.deleteUserAccess(cId);
		}else {
			throw new BusinessException("The entered CUSTOMER ID " + cId + " is invalid");
		}
	}

	@Override
	public void updateUserAccessStatus(String cId, String aStatus) throws BusinessException {
		if(isValidCustomerId(cId)) {
			userDAO.updateUserAccessStatus(cId, aStatus);
		}else {
			throw new BusinessException("The entered CUSTOMER ID " + cId + " is invalid");
		}
		
	}

}

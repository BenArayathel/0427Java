package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.userAccess;

public interface userAccessService {
	
	public userAccess createUserAccess(userAccess userAccess)throws BusinessException;
	public userAccess loginByUsernameAndPassword(String uName, String uPassword)throws BusinessException;
	public userAccess updateUserPassword(String cId,String newPassword) throws BusinessException;
	public void deleteUserAccess(String cId) throws BusinessException;
	public void updateUserAccessStatus(String cId, String aStatus) throws BusinessException;



}

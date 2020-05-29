package com.friendshipBank.dao;

import java.util.List;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;

public interface userAccessDAO 
{
	public userAccess createUserAccess(userAccess userAccess)throws BusinessException;
	public userAccess loginByUsernameAndPassword(String uName, String uPassword)throws BusinessException;
	public userAccess updateUserPassword(String cId,String newPassword) throws BusinessException;
	public void deleteUserAccess(String cId) throws BusinessException;
	public void updateUserAccessStatus(String cId, String aStatus) throws BusinessException;
	public List<userAccess> getAllUserLoginAccounts() throws BusinessException;
	public List<userAccess> getAllUserLoginAccountsStatus(String aStatus) throws BusinessException;
}

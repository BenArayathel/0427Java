package com.hackbank.persistence.dao.user;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.User;

public interface UserDAO {
	
	public abstract User createUser(User user) throws BusinessException;
	
}

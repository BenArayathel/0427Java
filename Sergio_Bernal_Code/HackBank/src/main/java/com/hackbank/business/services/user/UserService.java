package com.hackbank.business.services.user;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.User;

public interface UserService {

	public abstract User createUser(User user) throws BusinessException;
	
}

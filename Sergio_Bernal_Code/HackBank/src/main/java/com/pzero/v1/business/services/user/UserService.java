package com.pzero.v1.business.services.user;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.User;

public interface UserService {

	public abstract User createUser(User user) throws BusinessException;
	
}

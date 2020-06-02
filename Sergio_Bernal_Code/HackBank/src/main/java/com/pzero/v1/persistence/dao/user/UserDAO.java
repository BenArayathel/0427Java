package com.pzero.v1.persistence.dao.user;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.User;

public interface UserDAO {
	
	public abstract User createUser(User user) throws BusinessException;
	
}

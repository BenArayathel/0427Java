package com.pzero.v1.business.services.authentication;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.User;

public interface AuthenticationService {

	public abstract User login(String email, String password) throws BusinessException;
	public abstract void logout();
	
}

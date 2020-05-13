package com.hackbank.business.services.authentication;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.User;

public interface AuthenticationService {

	public abstract User login(String email, String password) throws BusinessException;
	public abstract void logout();
	
}

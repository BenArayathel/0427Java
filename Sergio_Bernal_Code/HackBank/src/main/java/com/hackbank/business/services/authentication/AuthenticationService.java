package com.hackbank.business.services.authentication;

import com.hackbank.business.exceptions.BusinessException;

public interface AuthenticationService {

	public abstract String login(String email, String password) throws BusinessException;
	public abstract void logout();
	
}

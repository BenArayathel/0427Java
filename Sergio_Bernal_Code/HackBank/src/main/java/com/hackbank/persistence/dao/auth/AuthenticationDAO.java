package com.hackbank.persistence.dao.auth;

import com.hackbank.business.exceptions.BusinessException;

public interface AuthenticationDAO {

	public abstract String authentication(String email, String pwd) throws BusinessException;
	
}

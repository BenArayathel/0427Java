package com.hackbank.persistence.dao.auth;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.User;

public interface AuthenticationDAO {

	public abstract User authentication(String email, String pwd) throws BusinessException;
	
}

package com.pzero.v1.persistence.dao.auth;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.User;

public interface AuthenticationDAO {

	public abstract User authentication(String email, String pwd) throws BusinessException;
	
}

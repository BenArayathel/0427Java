package com.pzero.v1.business.services.authentication;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.validations.Validation;
import com.pzero.v1.persistence.dao.auth.AuthDAOImplementation;
import com.pzero.v1.persistence.dao.auth.AuthenticationDAO;
import com.pzero.v1.persistence.models.User;

public class AuthenticationSercviceImplementation implements AuthenticationService {

	private static Validation vd = new Validation();
	private static AuthenticationDAO authDao = new AuthDAOImplementation();
	
	@Override
	public User login(String email, String password) throws BusinessException{
		User user = null;
		if (vd.isValidEmail(email) && vd.isValidPassword(password)) {
			user = authDao.authentication(email, password);
		}else {
			throw new BusinessException("\nThe email -> "+email+" or Password *** isn't valid.");
		}
		return user;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}

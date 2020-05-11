package com.hackbank.business.services.authentication;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.validations.Validation;
import com.hackbank.persistence.dao.auth.AuthDAOImplementation;
import com.hackbank.persistence.dao.auth.AuthenticationDAO;

public class AuthenticationSercviceImplementation implements AuthenticationService {

	private static Validation vd = new Validation();
	private static AuthenticationDAO authDao = new AuthDAOImplementation();
	
	@Override
	public String login(String email, String password) throws BusinessException{
		String userType = null;
		if (vd.isValidEmail(email) && vd.isValidPassword(password)) {
			userType = authDao.authentication(email, password);
		}else {
			throw new BusinessException("\nThe email -> "+email+" or Password *** isn't valid.");
		}
		return userType;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}

package com.hackbank.business.services.authentication;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.validations.Validation;
import com.hackbank.persistence.dao.auth.AuthDAOImplementation;
import com.hackbank.persistence.dao.auth.AuthenticationDAO;
import com.hackbank.persistence.models.User;

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

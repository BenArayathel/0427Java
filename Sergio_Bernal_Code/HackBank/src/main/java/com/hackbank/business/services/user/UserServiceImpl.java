package com.hackbank.business.services.user;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.validations.Validation;
import com.hackbank.persistence.dao.user.UserDAO;
import com.hackbank.persistence.dao.user.UserDAOImpl;
import com.hackbank.persistence.models.User;

public class UserServiceImpl implements UserService{
	
	final static Validation valid = new Validation();
	final static UserDAO userDAO = new UserDAOImpl();

	@Override
	public User createUser(User user) throws BusinessException {
		User iUser = null;
		if(user == null) {
			throw new BusinessException("User is not defined. Please contact SYSADMIN");
		}else if(!valid.isValidEmail(user.getEmail())) {
			throw new BusinessException("User email is not valid. E.g. example@example.com");
		}else if(!valid.isValidPassword(user.getPassword())) {
			throw new BusinessException("User passwor is not valid. You need at least 6 characters");
		}else {
			iUser = userDAO.createUser(user);
		}
		return iUser;
	}

}

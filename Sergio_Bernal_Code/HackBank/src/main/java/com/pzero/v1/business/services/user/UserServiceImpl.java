package com.pzero.v1.business.services.user;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.validations.Validation;
import com.pzero.v1.persistence.dao.user.UserDAO;
import com.pzero.v1.persistence.dao.user.UserDAOImpl;
import com.pzero.v1.persistence.models.User;

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

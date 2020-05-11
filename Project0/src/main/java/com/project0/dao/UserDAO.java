package com.project0.dao;

import com.project0.exception.BusinessException;
import com.project0.model.User;

public interface UserDAO {
	public void createUser(User user) throws BusinessException;
	public User getUser(String userName, String password) throws BusinessException;
}

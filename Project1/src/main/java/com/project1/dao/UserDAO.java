package com.project1.dao;

import com.project1.exception.BusinessException;
import com.project1.model.User;

public interface UserDAO {
	public void createUser(User user) throws BusinessException;
	public User getUser(String userName, String password) throws BusinessException;
	public User getUserByName(String userName) throws BusinessException;
	public User makeUserEmployee(String userName) throws BusinessException;
}

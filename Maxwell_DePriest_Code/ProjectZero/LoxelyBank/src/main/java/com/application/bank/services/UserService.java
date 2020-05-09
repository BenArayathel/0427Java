package com.application.bank.services;

import com.application.bank.dao.UserDao;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;

public interface UserService {
	
	public User registerNewUser() throws BusinessException;
	public boolean userLogin(String email, String password) throws BusinessException;
	public User setCurrentUser(String email) throws BusinessException;
	public void signUpForAccount(String email) throws BusinessException;
	
	public User updateProfile(User u) throws BusinessException;
	public void checkCheckingBalance() throws BusinessException;
	public void checkSavingsBalance() throws BusinessException;
	
	public void removeUserProfile(User u) throws BusinessException;
	

}



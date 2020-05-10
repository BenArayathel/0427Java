package com.application.bank.services;

import com.application.bank.dao.UserDao;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;

public interface UserService {
	public User setCurrentUser(String email) throws BusinessException;
	
	public void registerNewUser() throws BusinessException;
	public boolean userLogin(String email, String password) throws BusinessException;
	public void signUpForAccount(String email) throws BusinessException;

	public String checkCheckingBalance(User u) throws BusinessException;
	public String checkSavingsBalance(User u) throws BusinessException;
	
	public void depositMoney(String whichAccount, String amount, User u) throws BusinessException;
	public void withdrawMoney(String whichAccount, String amount, User u) throws BusinessException;
	
	public void removeUserProfile(User u) throws BusinessException;
	

}



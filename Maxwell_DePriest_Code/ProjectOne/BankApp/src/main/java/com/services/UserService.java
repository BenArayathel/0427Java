package com.services;

import com.exceptions.BusinessException;
import com.models.Account;
import com.models.User;

public interface UserService {
	public User setCurrentUser(String email) throws BusinessException;
	public Account setCurrentAccount(String email) throws BusinessException;
	
	public void registerNewUser() throws BusinessException;
	public boolean userLogin(String email, String password) throws BusinessException;
	public void signUpForAccount(String email) throws BusinessException;

	public String checkCheckingBalance(String email) throws BusinessException;
	public String checkSavingsBalance(String email) throws BusinessException;
	
	public void depositMoney(String whichAccount, String amount, String email) throws BusinessException;
	public void withdrawMoney(String whichAccount, String amount, String email) throws BusinessException;
	public void transferFunds(String uEmail, String fromWhichAccount, String receivingAccountNum, String amt) throws BusinessException;
	
	public void removeUserProfile(User u) throws BusinessException;
	

}

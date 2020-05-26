package com.services;

import com.exceptions.BusinessException;
import com.models.Account;
import com.models.User;

public interface UserService {
	public User setCurrentUser(String email) throws BusinessException;
	public Account setCurrentAccount(String email) throws BusinessException;
	
	public boolean createNewUser(User u) throws BusinessException;
	public boolean userLogin(String email, String password) throws BusinessException;
	public void createNewAccount(String email, String startingCheckingAmount) throws BusinessException;

	public String checkCheckingBalance(String email) throws BusinessException;
	public String checkSavingsBalance(String email) throws BusinessException;
	
	public void depositMoney(String whichAccount, String amount, String email) throws BusinessException;
	public void withdrawMoney(String whichAccount, String amount, String email) throws BusinessException;
	public boolean transferFunds(String uEmail, String fromWhichAccount, String receivingAccountNum, String amt) throws BusinessException;
	
	public void removeUserProfile(String email) throws BusinessException;
	

}

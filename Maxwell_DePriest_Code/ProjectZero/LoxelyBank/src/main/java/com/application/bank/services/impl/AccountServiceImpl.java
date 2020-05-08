package com.application.bank.services.impl;

import java.util.Random;

import org.apache.log4j.Logger;

import com.application.bank.models.Account;
import com.application.bank.services.AccountService;

public class AccountServiceImpl implements AccountService {
	final static Logger loggy = Logger.getLogger(Account.class);

	public AccountServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static int generateAccountNumber() {
		Random rand = new Random();
		int randomAccountNumber = rand.nextInt(100000);
		
		return randomAccountNumber;
	}

	


}

package com.bankofben.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Transaction;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeView {
	
	final static Logger loggy = Logger.getLogger(RequestHelper.class);

	public static String getAllBalances(HttpServletRequest request, HttpServletResponse response) throws BusinessException, JsonProcessingException {
		List<Account> accounts = null;
		BankOfBenServices dbs = new BankOfBenServices();
		accounts = dbs.getAllAccounts();
		String respString = new ObjectMapper().writeValueAsString(accounts);
		return respString;
	}

	public static String getCustomerBalances(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		List<Account> accounts = null;
		BankOfBenServices dbs = new BankOfBenServices();
		String username = new Marshal().getRequestBodyNameValuePair("username", request);
		String respString = null;
		try {
			accounts = dbs.getAccountsForCustomerUsername(username);
			respString = new ObjectMapper().writeValueAsString(accounts);
		} catch (BusinessException e) {
			loggy.error(e);
			respString = "{\"ERROR\": \""+e.getMessage()+"\"}";
		}
		return respString;
	}

	public static String getAllTransactions(HttpServletRequest request, HttpServletResponse response) throws BusinessException, JsonProcessingException {
		BankOfBenServices dbs = new BankOfBenServices();
		List<Transaction> transactions = dbs.getTransactions();
		String respString = new ObjectMapper().writeValueAsString(transactions);
		return respString;
	}

}

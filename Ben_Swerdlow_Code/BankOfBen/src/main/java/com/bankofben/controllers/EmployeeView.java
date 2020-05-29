package com.bankofben.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeView {

	public static String getAllBalances(HttpServletRequest request, HttpServletResponse response) throws BusinessException, JsonProcessingException {
//		String respString = "/home.html";
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
		System.out.println(username);
		accounts = dbs.getAccountsForCustomerUsername(username);
		String respString = new ObjectMapper().writeValueAsString(accounts);
		System.out.println(respString);
		return respString;
	}

}

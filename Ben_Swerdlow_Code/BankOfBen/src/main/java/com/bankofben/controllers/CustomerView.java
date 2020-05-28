package com.bankofben.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.services.BankOfBenServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerView {

	public static String getAccountsForCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Account> accounts = null;
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					accounts = dbs.getAccountsForCustomer(customer);
					respString = new ObjectMapper().writeValueAsString(accounts);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return respString;
	}

}

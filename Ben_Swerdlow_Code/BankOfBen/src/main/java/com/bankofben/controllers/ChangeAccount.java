package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChangeAccount {

	public static String makeDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		HttpSession session = request.getSession(false);
		BankOfBenServices dbs = new BankOfBenServices();
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				Marshal marshal = new Marshal();
				String accountNumberString = marshal.getRequestBodyNameValuePair("accountNumber", request);
				long accountNumber = Long.parseLong(accountNumberString);
				if (dbs.doesCustomerOwnAccountNumber(customer.getId(), accountNumber)) {
					Account account = dbs.getAccount(accountNumber, Account.getRoutingNumber());
					String amountString = marshal.getRequestBodyNameValuePair("amount", request);
					Double amount = Double.parseDouble(amountString);
					Account updatedAccount = dbs.updateAccountBalance(account, amount, account);
					respString = new ObjectMapper().writeValueAsString(updatedAccount);
				} else {
					throw new BusinessException("Customer "+customer.getUsername()+" does not own account "+accountNumber);
				}
			}
		}
		return respString;
	}

	public static String makeWithdrawal(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		HttpSession session = request.getSession(false);
		BankOfBenServices dbs = new BankOfBenServices();
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				Marshal marshal = new Marshal();
				String accountNumberString = marshal.getRequestBodyNameValuePair("accountNumber", request);
				long accountNumber = Long.parseLong(accountNumberString);
				if (dbs.doesCustomerOwnAccountNumber(customer.getId(), accountNumber)) {
					Account account = dbs.getAccount(accountNumber, Account.getRoutingNumber());
					String amountString = marshal.getRequestBodyNameValuePair("amount", request);
					Double amount = Double.parseDouble(amountString);
					Account updatedAccount = dbs.updateAccountBalance(account, -amount, account);
					respString = new ObjectMapper().writeValueAsString(updatedAccount);
				} else {
					throw new BusinessException("Customer "+customer.getUsername()+" does not own account "+accountNumber);
				}
			}
		}
		return respString;
	}

}

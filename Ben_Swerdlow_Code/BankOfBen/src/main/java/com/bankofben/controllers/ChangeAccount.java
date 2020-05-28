package com.bankofben.controllers;

import java.io.IOException;
import java.util.Map;

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
				String[] properties = {"accountNumber", "amount"};
				Map<String, String> depositInfo = marshal.getRequestBodyNameValuePairs(properties, request);
				String accountNumberString = depositInfo.get("accountNumber");
				long accountNumber = Long.parseLong(accountNumberString);
				if (dbs.doesCustomerOwnAccountNumber(customer.getId(), accountNumber)) {
//					System.out.println("Account exists!");
					Account account = dbs.getAccount(accountNumber, Account.getRoutingNumber());
					String amountString = depositInfo.get("amount");
					Double amount = Double.parseDouble(amountString);
//					System.out.println("Updating amount!");
					Account updatedAccount = dbs.updateAccountBalance(account, amount, account);
//					System.out.println("Updated amount!");
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
				String[] properties = {"accountNumber", "amount"};
				Map<String, String> depositInfo = marshal.getRequestBodyNameValuePairs(properties, request);
				String accountNumberString = depositInfo.get("accountNumber");
				long accountNumber = Long.parseLong(accountNumberString);
				if (dbs.doesCustomerOwnAccountNumber(customer.getId(), accountNumber)) {
//					System.out.println("Account exists!");
					Account account = dbs.getAccount(accountNumber, Account.getRoutingNumber());
					String amountString = depositInfo.get("amount");
					Double amount = Double.parseDouble(amountString);
//					System.out.println("Updating amount!");
					Account updatedAccount = dbs.updateAccountBalance(account, -amount, account);
//					System.out.println("Updated amount!");
					respString = new ObjectMapper().writeValueAsString(updatedAccount);
				} else {
					throw new BusinessException("Customer "+customer.getUsername()+" does not own account "+accountNumber);
				}
			}
		}
		return respString;
	}

}

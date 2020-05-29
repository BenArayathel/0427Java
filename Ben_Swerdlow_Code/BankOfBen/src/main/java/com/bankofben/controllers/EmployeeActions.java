package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;

public class EmployeeActions {

	public static String approveAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		Marshal marshal = new Marshal();
		String accountNumberString = marshal.getRequestBodyNameValuePair("accountNumber", request);
		Long accountNumber = Long.parseLong(accountNumberString);
		BankOfBenServices dbs = new BankOfBenServices();
		Account account = dbs.getAccount(accountNumber, Account.getRoutingNumber());
		String customerId = account.getCustomerId();
		Customer customer = dbs.getCustomerById(customerId);
		if (customer.isApplicationPending()) {
			dbs.approveNewCustomerAccountApplication(account);
		} else {
			dbs.approveExistingCustomerAccountApplication(account);
		}
		return "{\"result\": \"approved\"}";
	}

	public static String rejectAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		Marshal marshal = new Marshal();
		String accountNumberString = marshal.getRequestBodyNameValuePair("accountNumber", request);
		Long accountNumber = Long.parseLong(accountNumberString);
		BankOfBenServices dbs = new BankOfBenServices();
		Account account = dbs.getAccount(accountNumber, Account.getRoutingNumber());
		String customerId = account.getCustomerId();
		Customer customer = dbs.getCustomerById(customerId);
		if (customer.isApplicationPending()) {
			dbs.rejectNewCustomerAccountApplication(account);
		} else {
			dbs.rejectExistingCustomerAccountApplication(account);
		}
		return "{\"result\": \"rejected\"}";
	}

}

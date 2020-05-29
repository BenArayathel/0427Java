package com.bankofben.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;

public class TransferActions {

	public static String acceptPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		BankOfBenServices dbs = new BankOfBenServices();
		Marshal marshal = new Marshal();
		String paymentId = marshal.getRequestBodyNameValuePair("id", request);
		Payment payment = dbs.getPaymentById(paymentId);
		if (payment!=null) {
			dbs.makePayment(payment);
			respString = "/customer.html";
		}
		return respString;
	}

	public static String haltPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		BankOfBenServices dbs = new BankOfBenServices();
		Marshal marshal = new Marshal();
		String paymentId = marshal.getRequestBodyNameValuePair("id", request);
		Payment payment = dbs.getPaymentById(paymentId);
		if (payment!=null) {
			dbs.haltTransfer(payment);
			respString = "/customer.html";
		}
		return respString;
	}

	public static String acceptRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		BankOfBenServices dbs = new BankOfBenServices();
		Marshal marshal = new Marshal();
		String requestId = marshal.getRequestBodyNameValuePair("id", request);
		Request req = dbs.getRequestById(requestId);
		if (req!=null) {
			dbs.makeRequest(req);
			respString = "/customer.html";
		}
		return respString;
	}

	public static String haltRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		BankOfBenServices dbs = new BankOfBenServices();
		Marshal marshal = new Marshal();
		String requestId = marshal.getRequestBodyNameValuePair("id", request);
		Request req = dbs.getRequestById(requestId);
		if (req!=null) {
			dbs.haltTransfer(req);
			respString = "/customer.html";
		}
		return respString;
	}

	public static String postPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		BankOfBenServices dbs = new BankOfBenServices();
		Marshal marshal = new Marshal();
		String[] properties = {"payingAccountNumber", "recievingAccountNumber", "paymentAmount"};
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				Map<String, String> paymentDetails = marshal.getRequestBodyNameValuePairs(properties, request);
				long payingAccountNumber = Long.parseLong(paymentDetails.get("payingAccountNumber"));
				long recievingAccountNumber = Long.parseLong(paymentDetails.get("recievingAccountNumber"));
				double amount = Double.parseDouble(paymentDetails.get("paymentAmount"));
				Account payingAccount = dbs.getAccount(payingAccountNumber, Account.getRoutingNumber());
				Account recievingAccount = dbs.getAccount(recievingAccountNumber, Account.getRoutingNumber());
				if (payingAccount.isPending() || recievingAccount.isPending()) {
					throw new BusinessException("Cannot operate on an account that is pending.");
				} else {
					dbs.postPayement(customer, payingAccount, recievingAccount, amount);
					respString = "/customer.html";
				}
			}
		}
		return respString;
	}

	public static String postRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = "/home.html";
		BankOfBenServices dbs = new BankOfBenServices();
		Marshal marshal = new Marshal();
		String[] properties = {"requestorAccountNumber", "soughtAccountNumber", "requestAmount"};
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				Map<String, String> requestDetails = marshal.getRequestBodyNameValuePairs(properties, request);
				System.out.println(requestDetails);
				long requestorAccountNumber = Long.parseLong(requestDetails.get("requestorAccountNumber"));
				long soughtAccountNumber = Long.parseLong(requestDetails.get("soughtAccountNumber"));
				double amount = Double.parseDouble(requestDetails.get("requestAmount"));
				Account requestorAccount = dbs.getAccount(requestorAccountNumber, Account.getRoutingNumber());
				Account soughtAccount = dbs.getAccount(soughtAccountNumber, Account.getRoutingNumber());
				if (requestorAccount.isPending() || soughtAccount.isPending()) {
					throw new BusinessException("Cannot operate on an account that is pending.");
				} else {
					dbs.postRequest(customer, requestorAccount, soughtAccount, amount);
					respString = "/customer.html";
				}
			}
		}
		return respString;
	}

}

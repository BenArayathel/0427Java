package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;
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

	public static String rejectPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
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

	public static String rejectRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
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

}

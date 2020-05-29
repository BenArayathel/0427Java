package com.bankofben.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.services.BankOfBenServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerView {
	
	final static Logger loggy = Logger.getLogger(CustomerView.class);

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
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}
	
	public static String getPaymentsPendingInvolvingCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Payment> payments = new ArrayList<>();
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					payments = dbs.getPaymentsPending(customer);
					respString = new ObjectMapper().writeValueAsString(payments);
				} catch (BusinessException e) {
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}

	public static String getPaymentsPendingToCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Payment> payments = new ArrayList<>();
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					boolean selfPayments = true;
					payments = dbs.getPaymentsPendingToCustomer(customer, selfPayments);
					respString = new ObjectMapper().writeValueAsString(payments);
				} catch (BusinessException e) {
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}

	public static String getPaymentsPendingFromCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Payment> payments = new ArrayList<>();
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					boolean selfPayments = false;
					payments = dbs.getPaymentsPendingFromCustomer(customer, selfPayments);
					respString = new ObjectMapper().writeValueAsString(payments);
				} catch (BusinessException e) {
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}

	public static String getRequestsPendingInvolvingCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Request> requests = new ArrayList<>();
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					requests = dbs.getRequestsPending(customer);
					respString = new ObjectMapper().writeValueAsString(requests);
				} catch (BusinessException e) {
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}

	public static String getRequestsPendingToCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Request> requests = new ArrayList<>();
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					boolean selfRequests = true;
					requests = dbs.getRequestsPendingToCustomer(customer, selfRequests);
					respString = new ObjectMapper().writeValueAsString(requests);
				} catch (BusinessException e) {
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}

	public static String getRequestsPendingFromCustomer(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		List<Request> requests = new ArrayList<>();
		BankOfBenServices dbs = new BankOfBenServices();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!=null) {
				try {
					boolean selfRequests = false;
					requests = dbs.getRequestsPendingFromCustomer(customer, selfRequests);
					respString = new ObjectMapper().writeValueAsString(requests);
				} catch (BusinessException e) {
					loggy.error(e);
//					e.printStackTrace();
				}
			}
		}
		return respString;
	}

}

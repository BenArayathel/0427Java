package com.bankofben.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestHelper {
	
	final static Logger loggy = Logger.getLogger(RequestHelper.class);

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		loggy.info("Request Helper processing");
		
		String respString = "/home.html";
		
		String uri = request.getRequestURI();
		
		switch(uri) {
		case "/BankOfBen/api/InitialRegistration":
			System.out.println("Going to initial registration");
			try {
				respString = Register.initialRegistration(request, response); 
				System.out.println(respString);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/GetTempUser":
			System.out.println("Going to get tempUser from current session");
			try {
				respString = SessionInfo.getTempUser(request, response);
				System.out.println(respString);
			} catch (JsonProcessingException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/newUserRegistration":
			try {
				respString = Register.newUserRegistration(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getFirstLastName":
			return SessionInfo.getFirstLastName(request, response);
		case "/BankOfBen/api/accountApplication":
			try {
				respString = Accounts.accountApplication(request, response);
			} catch (BusinessException | IOException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/login":
			try {
				respString = Login.login(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getAccountsForCustomer":
			try {
				respString = CustomerView.getAccountsForCustomer(request, response);
			} catch (JsonProcessingException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/makeDeposit":
			System.out.println("I am in make deposit");
			try {
				respString = ChangeAccount.makeDeposit(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/makeWithdrawal":
			try {
				respString = ChangeAccount.makeWithdrawal(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getPaymentsPendingInvolvingCustomer":
			try {
				respString = CustomerView.getPaymentsPendingInvolvingCustomer(request, response);
			} catch (IOException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getPaymentsPendingToCustomer":
			try {
				respString = CustomerView.getPaymentsPendingToCustomer(request, response);
			} catch (JsonProcessingException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getPaymentsPendingFromCustomer":
			try {
				respString = CustomerView.getPaymentsPendingFromCustomer(request, response);
			} catch (JsonProcessingException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getRequestsPendingInvolvingCustomer":
			try {
				respString = CustomerView.getRequestsPendingInvolvingCustomer(request, response);
			} catch (IOException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getRequestsPendingToCustomer":
			try {
				respString = CustomerView.getRequestsPendingToCustomer(request, response);
			} catch (JsonProcessingException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getRequestsPendingFromCustomer":
			try {
				respString = CustomerView.getRequestsPendingFromCustomer(request, response);
			} catch (JsonProcessingException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/acceptPayment":
			try {
				respString = TransferActions.acceptPayment(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/haltPayment":
			try {
				respString = TransferActions.haltPayment(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/acceptRequest":
			try {
				respString = TransferActions.acceptRequest(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/haltRequest":
			try {
				respString = TransferActions.haltRequest(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/postPayment":
			try {
				respString = TransferActions.postPayment(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/postRequest":
			try {
				respString = TransferActions.postRequest(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getAllBalances":
			try {
				respString = EmployeeView.getAllBalances(request, response);
			} catch (JsonProcessingException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getCustomerBalances":
			try {
				respString = EmployeeView.getCustomerBalances(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/getAllTransactions":
			try {
				respString = EmployeeView.getAllTransactions(request, response);
			} catch (JsonProcessingException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/approveAccount":
			try {
				respString = EmployeeActions.approveAccount(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/rejectAccount":
			try {
				respString = EmployeeActions.rejectAccount(request, response);
			} catch (IOException | BusinessException e) {
				loggy.error(e);
//				e.printStackTrace();
				respString = null;
			}
			break;
		default:
			loggy.info("Didn't recognize option "+uri);
		}

		return respString;
	}
	
	public static String processUpdate(HttpServletRequest request, HttpServletResponse response) {
		String respString = null;
		return respString;
	}

}

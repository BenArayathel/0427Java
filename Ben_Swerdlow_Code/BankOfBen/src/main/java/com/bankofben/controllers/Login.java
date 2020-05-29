package com.bankofben.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;

public class Login {
	
	final static Logger loggy = Logger.getLogger(Login.class);

	public static String login(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = null;
		
		loggy.info("Login");
		String[] properties = {"username", "password"};
		Map<String, String> loginInfo = new Marshal().getRequestBodyNameValuePairs(
				properties, request);

		BankOfBenServices dbs = new BankOfBenServices();
		
		if (dbs.usernameExists(loginInfo.get("username"))) {
			User userCustomerOrEmployee = null;
			try {
				userCustomerOrEmployee = dbs.loginUser(
						loginInfo.get("username"), loginInfo.get("password"));
				if (userCustomerOrEmployee instanceof Customer) {
					// if customer, send to customer page
					Customer customer = (Customer) userCustomerOrEmployee;
					HttpSession session = request.getSession();
					session.setAttribute("customer", customer);
					loggy.info("Customer logged in: "+customer);
					if (customer.isApplicationPending()) {
						// if pending, send to pending
						respString = "/pendingUser.html";
					} else {
						// if not, send to main page
						respString = "/customer.html";
					}
				} else if (userCustomerOrEmployee instanceof Employee) {
					// if employee, send to employee page
					Employee employee = (Employee) userCustomerOrEmployee;
					HttpSession session = request.getSession();
					session.setAttribute("employee", employee);
					loggy.info("Employee logged in: "+employee);
					respString = "/employee.html";
				} else {
					// if neither, it's a customer that's not approved, so send to pending user page
					respString = "/pendingUser.html";
				}
			} catch (BusinessException e) {
				loggy.error(e);
				loggy.info("Invalid password");
				respString = "{\"password\": \""+loginInfo.get("password")+"\"}";
			}
		} else {
			//username does not exist
			loggy.info("Invalid username: "+loginInfo.get("username"));
			respString = "{\"username\": \""+loginInfo.get("username")+"\"}";
		}
		
		return respString;
	}

}

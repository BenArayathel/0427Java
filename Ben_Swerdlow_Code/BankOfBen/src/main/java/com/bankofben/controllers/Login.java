package com.bankofben.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.User;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;

public class Login {

	public static String login(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		String respString = null;
		
		System.out.println("Login");
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
					respString = "/customer.html";
				} else if (userCustomerOrEmployee instanceof Employee) {
					// if employee, send to employee page
					Employee employee = (Employee) userCustomerOrEmployee;
					HttpSession session = request.getSession();
					session.setAttribute("employee", employee);
					respString = "/employee.html";
				} else {
					// if neither, it's a customer that's not approved, so send to pending user page
					respString = "/pendingUser.html";
				}
			} catch (BusinessException e) {
				respString = "{\"password\": \""+loginInfo.get("password")+"\"}";
			}
		} else {
			//username does not exist
			respString = "{\"username\": \""+loginInfo.get("username")+"\"}";
		}
		
		return respString;
	}

}

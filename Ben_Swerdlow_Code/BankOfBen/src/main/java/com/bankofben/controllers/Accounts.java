package com.bankofben.controllers;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Customer;
import com.bankofben.models.User;
import com.bankofben.services.BankOfBenServices;
import com.bankofben.utils.Marshal;

public class Accounts {

	public static String accountApplication(HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException {
		String respString = "/home.html";
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Enumeration<String> attributeNames = session.getAttributeNames();
			User user = null;
			while (attributeNames.hasMoreElements()) {
				String attributeName = (String) attributeNames.nextElement();
				System.out.println(attributeName);
				if (attributeName.equals("user") || attributeName.equals("customer")) {
					user = (User) session.getAttribute(attributeName);
				}
			}
			if (user!=null && user instanceof Customer) {
				Customer customer = (Customer) user;
				double startingBalance = Double.parseDouble(new Marshal().getRequestBodyNameValuePair("startingBalance", request));
				new BankOfBenServices().applyForAccount(customer, startingBalance);
				respString = "/customer.html";
			} else if (user!=null && user instanceof User) {
				double startingBalance = Double.parseDouble(new Marshal().getRequestBodyNameValuePair("startingBalance", request));
				Customer customer = new BankOfBenServices().applyForAccount_newUser(user, startingBalance);
				session.setAttribute("customer", customer);
				session.removeAttribute("user");
				respString = "/userPending.html";
			} else {
				throw new BusinessException("Invalid session user. Please try again.");
			}
		}
		return respString;
	}

}

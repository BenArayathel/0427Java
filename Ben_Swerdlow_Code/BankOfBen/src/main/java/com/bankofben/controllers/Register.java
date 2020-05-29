package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.TempUser;
import com.bankofben.models.User;
import com.bankofben.services.BankOfBenServices;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.bankofben.services.BankOfBenServices;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Register {
	
	final static Logger loggy = Logger.getLogger(Register.class);

	public static String initialRegistration(HttpServletRequest request, HttpServletResponse response) throws BusinessException, JsonParseException, JsonMappingException, IOException {
		loggy.info("Initial Registration");
		ObjectMapper objMapper = new ObjectMapper();
		BankOfBenServices dbs = new BankOfBenServices();
		
		TempUser tempUser = objMapper.readValue(request.getReader(), com.bankofben.models.TempUser.class);
		
		String respString;
		
		if (dbs.usernameExists(tempUser.getUsername())) {
			loggy.error(new BusinessException("Username "+tempUser.getUsername()+" already exists"));
			respString = "{\"username\": \""+tempUser.getUsername()+"\"}";
		} else if (dbs.emailExists(tempUser.getEmail())) {
			loggy.error(new BusinessException("Email "+tempUser.getEmail()+" already exists"));
			respString = "{\"email\": \""+tempUser.getEmail()+"\"}";
		} else {
//			// Set username cookie
//			Cookie usernameCookie = new Cookie("username", tempUser.getUsername());
//			usernameCookie.setMaxAge(900);
//			response.addCookie(usernameCookie);
//			// Set email cookie
//			Cookie emailCookie = new Cookie("email", tempUser.getEmail());
//			emailCookie.setMaxAge(900);
//			response.addCookie(emailCookie);
			
			// Use sessions instead
			HttpSession session = request.getSession();//Session ID generated on server-side
			session.setAttribute("tempUser", tempUser);
			loggy.info("Passed initial registration.");
			respString = "/newUser.html";
		}
		
		return respString;
	}

	public static String newUserRegistration(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException, BusinessException {
		loggy.info("New User registration");
		String respString="/home.html";
		
		HttpSession session = request.getSession(false);
		BankOfBenServices dbs = new BankOfBenServices();
		if (session!=null) {
			ObjectMapper objMapper = new ObjectMapper();
			User user = objMapper.readValue(request.getReader(), com.bankofben.models.User.class);
			if (dbs.ssnExists(user.getSsn())) {
				loggy.error(new BusinessException("Social security number "+user.getSsn()+" already exists"));
				respString = "{\"ssn\": \""+user.getSsn()+"\"}";
			} else {
				session.setAttribute("user", user);
				respString = "/accountApplication.html";
				loggy.info("Passed complete registration and applying for account.");
			}
		}
		
		return respString;
	}

}

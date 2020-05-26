package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.TempUser;
import com.bankofben.models.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.bankofben.services.BankOfBenServices;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Register {

	public static String initialRegistration(HttpServletRequest request, HttpServletResponse response) throws BusinessException, JsonParseException, JsonMappingException, IOException {
		System.out.println("Initial Registration");
		ObjectMapper objMapper = new ObjectMapper();
//		BankOfBenServices dbs = new BankOfBenServices();
		
		TempUser tempUser = objMapper.readValue(request.getReader(), com.bankofben.models.TempUser.class);
		
		String respString;
		
//		if (dbs.usernameExists(tempUser.getUsername())) {
//			respString = "{\"username\": "+tempUser.getUsername()+"}";
//		} else if (dbs.emailExists(tempUser.getEmail())) {
//			respString = "{\"email\": "+tempUser.getEmail()+"}";
		if (tempUser.getUsername().equals("username")) {
			respString = "{\"username\": \""+tempUser.getUsername()+"\"}";
//			respString = objMapper.writeValueAsString(tempUser);
		} else if (tempUser.getEmail().equals("ben@gmail.com")) {
			respString = "{\"email\": \""+tempUser.getEmail()+"\"}";
//			respString = objMapper.writeValueAsString(tempUser);
		} else {
			String userString = objMapper.writeValueAsString(tempUser);
			System.out.println(userString);
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
			
			respString = "/newUser.html";
		}
		
		System.out.println("Returning "+respString);
		return respString;
	}

	public static String newUserRegistration(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("New User registration");
		
		HttpSession session = request.getSession(false);
		if (session==null) {
			return null;
		}
		
		ObjectMapper objMapper = new ObjectMapper();
		String respString;
		
		User user = objMapper.readValue(request.getReader(), com.bankofben.models.User.class);
		session.setAttribute("user", user);
		respString = "/accountApplication.html";
		
		return respString;
	}

}

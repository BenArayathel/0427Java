package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.TempUser;
//import com.bankofben.services.BankOfBenServices;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InitialRegistration {

	public static String register(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException{
		ObjectMapper objMapper = new ObjectMapper();
//		BankOfBenServices dbs = new BankOfBenServices();
		
		TempUser tempUser = objMapper.readValue(request.getReader(), com.bankofben.models.TempUser.class);
		
		String respString;
		
//		if (dbs.usernameExists(tempUser.getUsername())) {
//			respString = "{\"user\": "+tempUser.getUsername()+"}";
//		} else if (dbs.emailExists(tempUser.getEmail())) {
//			respString = "{\"email\": "+tempUser.getEmail()+"}";
		if (tempUser.getUsername().equals("username")) {
//			respString = "{\"user\": \""+tempUser.getUsername()+"\"}";
			respString = objMapper.writeValueAsString(tempUser);
		} else if (tempUser.getEmail().equals("ben@gmail.com")) {
//			respString = "{\"email\": \""+tempUser.getEmail()+"\"}";
			respString = objMapper.writeValueAsString(tempUser);
		} else {
			respString = "/BankOfBen/newUser.html";
		}
		
		System.out.println("Returning "+respString);
		return respString;
	}

}

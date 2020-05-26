package com.bankofben.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Request Helper");
		
		String respString = "/home.html";
		
		String uri = request.getRequestURI();
		
		switch(uri) {
		case "/BankOfBen/api/InitialRegistration":
			System.out.println("Going to initial registration");
			try {
				respString = Register.initialRegistration(request, response); 
				System.out.println(respString);
			} catch (IOException | BusinessException e) {
				// TODO Auto-generated catch block, put in loggy.error
				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/GetTempUser":
			System.out.println("Going to get tempUser from current session");
			try {
				respString = SessionInfo.getTempUser(request, response);
				System.out.println(respString);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respString = null;
			}
			break;
		case "/BankOfBen/api/newUserRegistration":
			try {
				return Register.newUserRegistration(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respString = null;
			}
		case "BankOfBen/api/GetFirstLastName":
			return SessionInfo.getFirstLastName(request, response);
		case "BankOfBen/api/accountApplication":
			try {
				return Accounts.accountApplication(request, response);
			} catch (BusinessException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respString = null;
			}
		default:
			System.out.println("Didn't recognize option "+uri);
		}

		return respString;
	}

}

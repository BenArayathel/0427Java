package com.bankofben.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Request Helper");
		
		String respString = "home.html";
		
		String uri = request.getRequestURI(); 
		System.out.println(uri);
		
		switch(uri) {
		case "/BankOfBen/api/InitialRegistration":
			System.out.println("Going to initial registration");
			try {
				respString = InitialRegistration.register(request, response); 
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
				respString = GetSessionInfo.getTempUser(request, response);
				System.out.println(respString);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respString = null;
			}
			break;
//		case "/BankOfBen/api/CompleteRegistration":
//			return CompleteRegistration.register(request, response);
		default:
			System.out.println("Didn't recognize option");
		}
		
//		if (respString.endsWith(".html")){
//			System.out.println(respString);
//			response.sendRedirect(respString);
//		} else {
//			response.setContentType("application/json");
//			System.out.println(respString);
//			PrintWriter out = response.getWriter();
//			out.write(respString);
////			out.flush();
//		}

		return respString;
	}

}

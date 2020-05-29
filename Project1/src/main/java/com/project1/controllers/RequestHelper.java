package com.project1.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		String respMessage = "index.html";
		
		String uri = request.getRequestURI();
		
		switch(uri) {
		case "/Project1/app/Login":
			try {
				respMessage = Login.login(request, response);
			} catch (IOException e) {
				respMessage = null;
			}
			break;
		case "/Project1/app/Logout":
			try {
				respMessage = Login.logout(request, response);
			} catch (IOException e) {
				respMessage = null;
			}
			break;
		default:
			System.out.println("Did not recognize option " + uri);
		}
		
		return respMessage;
	}

}

package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response) {
			
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String pathInfo = request.getPathInfo();
		String[] path = pathInfo.split("\\/");
		
		try {
			PrintWriter out = response.getWriter();

			switch(path[1]) {
				case "auth":
					AuthController.login(request, response);
					break;
				case "user":
					UserController.user(request, response);
					break;
				case "customer":
					CustomerController.search(request, response);
					break;
				case "account":
					AccountController.account(request, response);
					break;
				case "approval":
					ApprovalController.approval(request, response);
					break;
				case "transfer":
					TransferController.transfer(request, response);
					break;
				case "enrollment":
					EnrollmentController.enrollment(request, response);
					break;
				default:
					out.write("Error getting the authentication.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}

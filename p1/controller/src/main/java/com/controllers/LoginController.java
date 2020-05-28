package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.BankController;
import com.data.JDBC;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.models.User;

public class LoginController {

	/*
	 * Perform business logic 
	 */

	public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(request.getReader());

		
		System.out.println(jsonNode.get("userID").asText());
		
		
		PrintWriter writer = response.getWriter();
		//writer.write("Hello");
		
		
		String username = jsonNode.get("userID").asText();
		String password = jsonNode.get("password").asText();
		
		try {
			JDBC.databaseConnection("accountstable");
			if(JDBC.logIn(Integer.parseInt(username),  Integer.parseInt(password)) ){

				BankController bc = new BankController("customerstable");
				try {
					if(bc.logInOnline(Integer.parseInt(username))){
						//If the user has successfully logged 
//						System.out.println("I have been successfully logged in!");
						
						JSONResponse json = new JSONResponse("200", null, "I have been successfully logged in");
						
						writer.write(new ObjectMapper().writeValueAsString(json));
						request.getSession().setAttribute("loggedusername", username);
					}else {
						JSONResponse json = new JSONResponse("403", null, "Sorry but your account has not yet been approved or has been denied approval by the bank. Please contact your local branch for more information.");
						
						writer.write(new ObjectMapper().writeValueAsString(json));
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				writer.close();
			}else {
				JSONResponse json = new JSONResponse("err", null, "Incorrect userID/PIN combination");
				writer.write(new ObjectMapper().writeValueAsString(json));
				writer.close();
			}
		} catch (SQLException e) {
			System.out.println("There was an err in Login Controller: " + e);
		}
		
		
		
		
		
		
		
		
		
//		User user = new User(un, pw);
//		
//		//user = new ObjectMapper().readValue(request.getReader(), com.models.User.class);
//		System.out.println("User ID: " + user.getUserID());
//		
//		response.setContentType("application/json");
//		response.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}

}

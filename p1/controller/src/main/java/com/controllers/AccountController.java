package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.BankController;
import com.bank.Init;
import com.data.DBConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;

public class AccountController {

	/*
	 * Takes in a string value, converts it to a double,
	 * processes logic and updates account retrieving new balance,
	 * converts back to a string and returns response
	 * parameters({acctType, amount})
	 */
	public static void requestBalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(request.getReader());
		String accountType = jsonNode.get("accountType").asText();
		double acctBalance;
//
//		try {
//		DBConnector.getConnection();
//	} catch (SQLException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
		
		if(request.getSession().getAttribute("loggedusername") != null){
			String accountNum = (String) request.getSession().getAttribute("loggedusername");
			try {
				acctBalance = BankController.checkBalanceOnline(Integer.parseInt(accountNum), accountType);
				JSONResponse json = new JSONResponse("200", null, acctBalance + "");
				writer.write(new ObjectMapper().writeValueAsString(json));

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Session for userID is null");
		}
		writer.close();
	}
	
	public static void deposit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(request.getReader());
		String depositAmount = jsonNode.get("depositAmount").asText();
		String accountType = jsonNode.get("accountType").asText();
		
		if(request.getSession().getAttribute("loggedusername") != null){
			String accountNum = (String) request.getSession().getAttribute("loggedusername");
			try {
				BankController.depositOnline(Integer.parseInt(accountNum), Double.parseDouble(depositAmount), accountType);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Session for userID is null");
		}
	
	}
	
	public static void withdraw(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(request.getReader());
		String withdrawAmount = jsonNode.get("withdrawAmount").asText();
		String accountType = jsonNode.get("accountType").asText();
		if(request.getSession().getAttribute("loggedusername") != null){
			String accountNum = (String) request.getSession().getAttribute("loggedusername");
			try {
				BankController.withdrawOnline(Integer.parseInt(accountNum), Double.parseDouble(withdrawAmount), accountType);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Session for userID is null");
		}
		
	}
	
	public static void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(request.getReader());
		String firstName = jsonNode.get("firstName").asText();
		String lastName = jsonNode.get("lastName").asText();
		String ssn = jsonNode.get("ssn").asText();
		String occupation = jsonNode.get("occupation").asText();
		String income = jsonNode.get("income").asText();
		boolean married = jsonNode.get("married").asBoolean();
		boolean ownsHome = jsonNode.get("ownsHome").asBoolean();
		String pinNum = jsonNode.get("pin").asText();
		String initAmount = jsonNode.get("initAmount").asText();
		
		try {
			Init.createCustomerAccountOnline(firstName, lastName, Integer.parseInt(ssn), occupation, Double.parseDouble(income), married, ownsHome, Integer.parseInt(pinNum), Double.parseDouble(initAmount));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

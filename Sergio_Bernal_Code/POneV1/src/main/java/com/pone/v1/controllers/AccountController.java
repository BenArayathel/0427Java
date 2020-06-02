package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONCustomer;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.account.AccountService;
import com.pzero.v1.business.services.account.AccountServiceImpl;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.Person;

public class AccountController {
	
	final static AccountService sAccount = new AccountServiceImpl();

	public static void account(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String pathInfo = request.getPathInfo();
		String[] path = pathInfo.split("\\/");
		
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse json = new JSONResponse();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JsonNode jsonNode = null;
		
		switch (request.getMethod()) {
		case "GET":
			// get the accounts by person Id /all/id
			// get the detail account by Account id /id
			// get customer by accountId /verified/id
			try {

				if(path[2].equals("all")) {
					List<Account> result = sAccount.getAllAccountsByCustomer(path[3]);
					JSONCustomer jsonCustomer = new JSONCustomer((Person)request.getAttribute("person"), result);
					json.setStatus("ok");
					json.setData(jsonCustomer);
				}else if(path[2].equals("verified")) {
					String personId = sAccount.getPersonAccountById(path[3]);
					json.setStatus("ok");
					json.setData(personId);
				}else {
					Account result = sAccount.getAccountById(path[2]);
					json.setStatus("ok");
					json.setData(result);
				}
				
				out.write(new ObjectMapper().writeValueAsString(json));
				
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		case "POST":
			// TODO create an account
			
			jsonNode = mapper.readTree(request.getReader());
			Account nAccount = new Account();
			
			try {
				
				nAccount.setAccountTypeId(Short.parseShort(jsonNode.get("accountTypeId").asText()));
				nAccount.setBalance(jsonNode.get("balance").asDouble());
				nAccount.setPerson(CustomerController.sPerson.getPersonById(jsonNode.get("personId").asText()));
				
				// rPerson - Response Person
//				Account rAccount = sAccount.createAccount();
//				String json = "{\"status\":\"ok\",\"data\":"+ new ObjectMapper().writeValueAsString(rPerson) +"}";
//				out.write(json);
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		case "PUT":
			// TODO deposit and withdraw: /deposit - /withdraw
			jsonNode = mapper.readTree(request.getReader());
			Account uAccount = new Account();
			Account rAccount = new Account();
			try {
				if (path[2].equals("deposit")) {
					uAccount.setId(jsonNode.get("accountId").asText());
					uAccount.setBalance(jsonNode.get("accountBalance").asDouble());
					rAccount = sAccount.depositBalanceAccount(uAccount, jsonNode.get("balance").asDouble());
				}else if (path[2].equals("withdraw")) {
					uAccount.setId(jsonNode.get("accountId").asText());
					uAccount.setBalance(jsonNode.get("accountBalance").asDouble());
					rAccount = sAccount.withdrawBalanceAccount(uAccount, jsonNode.get("balance").asDouble());
				}
				json.setStatus("ok");
				json.setData(rAccount);
				out.write(new ObjectMapper().writeValueAsString(json));
				
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		default:
			json.setStatus("error");
			json.setData("Invalid Method");
			out.write(new ObjectMapper().writeValueAsString(json));
			break;
		}
		
		out.flush();
		out.close();
	}

}

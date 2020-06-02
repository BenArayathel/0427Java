package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.account.AccountService;
import com.pzero.v1.business.services.account.AccountServiceImpl;
import com.pzero.v1.business.services.transfer.TransferService;
import com.pzero.v1.business.services.transfer.TransferServiceImpl;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.Transfer;

public class TransferController {
	
	final static TransferService sTransfer = new TransferServiceImpl();
	final static AccountService sAccount = new AccountServiceImpl();

	public static void transfer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
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
			// TODO get the transfers by person Id /id
			try {
				
				List<Transfer> result = sTransfer.transferList(path[2]);
				json.setStatus("ok");
				json.setData(result);
				out.write(new ObjectMapper().writeValueAsString(json));
				
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		case "POST":
			// TODO create a transfer
			
			jsonNode = mapper.readTree(request.getReader());
			Transfer nTransfer = new Transfer();
			Account nAccount = new Account();
			
			try {
				
				nTransfer.setIniAcccountNumber(jsonNode.get("initAccount").asText());
				nTransfer.setIniRoutingNumber(jsonNode.get("initRouting").asText());
				nTransfer.setEndAccountNumber(jsonNode.get("endAccount").asText());
				nTransfer.setEndRoutingNumber(jsonNode.get("endRouting").asText());
				nTransfer.setStatus(jsonNode.get("status").asText());
				nTransfer.setAmount(jsonNode.get("amount").asDouble());
				nTransfer.setCreatedAt(new Date());
				
				boolean result = sTransfer.createTransfer(nTransfer, nAccount);
				
				json.setStatus("ok");
				json.setData(result);
				out.write(new ObjectMapper().writeValueAsString(json));
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		case "PUT":
			
			jsonNode = mapper.readTree(request.getReader());
			Transfer uTransfer = new Transfer();
			Account uAccount = new Account();
			
			try {
				
				System.out.println(jsonNode);
				uTransfer.setId(jsonNode.get("id").asInt());
				uTransfer.setStatus(jsonNode.get("status").asText());
				uTransfer.setAmount(jsonNode.get("amount").asDouble());
				uAccount = sAccount.getAccountById(jsonNode.get("accountId").asText());
				boolean result = sTransfer.acceptTransfer(uTransfer, uAccount);
				
				json.setStatus("ok");
				json.setData(result);
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

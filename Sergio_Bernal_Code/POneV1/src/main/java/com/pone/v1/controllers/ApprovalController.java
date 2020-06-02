package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONApproval;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.approval.PendingApprovalService;
import com.pzero.v1.business.services.approval.PendingApprovalSrvImpl;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.AccountType;
import com.pzero.v1.persistence.models.PendingApproval;

public class ApprovalController {
	
	final static PendingApprovalService spApproval = new PendingApprovalSrvImpl();

	public static void approval(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONResponse json = new JSONResponse();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JsonNode jsonNode = null;
		
		System.out.println(request.getMethod());
		
		switch (request.getMethod()) {
		case "GET":
			// TODO get all the approval status pending /all
			try {
				List<PendingApproval> listApproval = spApproval.listApproval();
				List<JSONApproval> result = new ArrayList<>();
				
				for(PendingApproval pa: listApproval) {
					
					JSONApproval jsonApproval = new JSONApproval();
					jsonApproval.setId(pa.getId());
					jsonApproval.setCustomerFullName(pa.getPerson().getName() + " " + pa.getPerson().getLastName());
					jsonApproval.setCustomerId(pa.getPerson().getId());
					jsonApproval.setAccountTypeId(pa.getAccountType().getId());
					jsonApproval.setAccountType(pa.getAccountType().getName());
					jsonApproval.setBalance(pa.getStartBalance());
					jsonApproval.setStatus(pa.getStatus());
					jsonApproval.setCreatedAt(pa.getCreatedAt());
					
					result.add(jsonApproval);
				}
				
				json.setStatus("ok");
				json.setData(result);
				out.write(new ObjectMapper().writeValueAsString(json));
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(null);
				json.setMessage(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			break;
		case "POST":
			// TODO create an approval
			PendingApproval nApproval = new PendingApproval();
			jsonNode = mapper.readTree(request.getReader());
			
			try {
				
				nApproval.setAccountTypeId(Byte.parseByte(jsonNode.get("accountTypeId").asText()));
				nApproval.setAccountType(new AccountType(Byte.parseByte(jsonNode.get("accountTypeId").asText()), jsonNode.get("accountTypeName").asText()));
				nApproval.setStartBalance(jsonNode.get("balance").asDouble());
				nApproval.setCreatedAt(new Date());
				nApproval.setStatus(jsonNode.get("status").asText());
				nApproval.setPerson(CustomerController.sPerson.getPersonById(jsonNode.get("personId").asText()));
				
				// Account rApproval - Response PendingApproval
				Account rApproval = spApproval.createApproval(nApproval);
				json.setStatus("ok");
				json.setData(rApproval);
				out.write(new ObjectMapper().writeValueAsString(json));
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setData(null);
				json.setMessage(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		case "PUT":
			
			PendingApproval uApproval = new PendingApproval();
			jsonNode = mapper.readTree(request.getReader());
			try {
				
				uApproval.setAccountTypeId(Byte.parseByte(jsonNode.get("accountTypeId").asText()));
				uApproval.setAccountType(new AccountType(Byte.parseByte(jsonNode.get("accountTypeId").asText()), jsonNode.get("accountTypeName").asText()));
				uApproval.setStartBalance(jsonNode.get("balance").asDouble());
				uApproval.setStatus(jsonNode.get("status").asText());
				uApproval.setPerson(CustomerController.sPerson.getPersonById(jsonNode.get("personId").asText()));
				uApproval.setId(jsonNode.get("id").asInt());
				
				// Account rApproval - Response PendingApproval
				Account rApproval = spApproval.updateApproval(uApproval);
				json.setStatus("ok");
				json.setData(rApproval);
				out.write(new ObjectMapper().writeValueAsString(json));
			} catch (BusinessException | IndexOutOfBoundsException e) {
				json.setStatus("error");
				json.setData(null);
				json.setMessage(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
			break;
		default:
			json.setStatus("error");
			json.setData(null);
			json.setMessage("Invalid Method");
			out.write(new ObjectMapper().writeValueAsString(json));
			break;
		}
		
		out.flush();
		out.close();
		
	}

}

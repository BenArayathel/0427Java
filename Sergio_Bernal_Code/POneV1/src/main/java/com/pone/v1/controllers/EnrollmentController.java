package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.enrollment.EnrollmentService;
import com.pzero.v1.business.services.enrollment.EnrollmentSrvcImpl;
import com.pzero.v1.persistence.models.BankAccountEnrollment;

public class EnrollmentController {
	
	final static EnrollmentService enrollment = new EnrollmentSrvcImpl();

	public static void enrollment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String pathInfo = request.getPathInfo();
		String[] path = pathInfo.split("\\/");
		
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse json = new JSONResponse();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(path.length == 3) {
			try {
				
				List<BankAccountEnrollment> result = enrollment.getEnrollmentsByCustomer(path[2]);
				json.setStatus("ok");
				json.setData(result);
				out.write(new ObjectMapper().writeValueAsString(json));
				
			} catch (BusinessException e) {
				
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
				
			}
		}else if(path.length == 2){
			JsonNode jsonNode = mapper.readTree(request.getReader());
			BankAccountEnrollment nEnrollment = new BankAccountEnrollment();
			
			try {
				
				nEnrollment.setToName(jsonNode.get("toName").asText());
				nEnrollment.setToLastName(jsonNode.get("toLastName").asText());
				nEnrollment.setAccountNumber(jsonNode.get("accountNumber").asText());
				nEnrollment.setRoutingNumber(jsonNode.get("routingNumber").asText());
				nEnrollment.setCreatedAt(new Date());
				nEnrollment.setPersonId(jsonNode.get("personId").asText());
				
				boolean flag = enrollment.createEnrollment(nEnrollment);
				if(flag) {
					request.getRequestDispatcher("enrollment/"+nEnrollment.getPersonId()).forward(request, response);				
				}else {
					json.setStatus("error");
					json.setData("The Enrollemnt exist already");
					out.write(new ObjectMapper().writeValueAsString(json));
				}
				
			} catch (BusinessException | ServletException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
		}else {
			json.setStatus("error");
			json.setData("Invalid Method");
			out.write(new ObjectMapper().writeValueAsString(json));
		}
		
		out.flush();
		out.close();
		
	}

}

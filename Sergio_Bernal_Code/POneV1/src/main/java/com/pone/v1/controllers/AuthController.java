package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.authentication.AuthenticationSercviceImplementation;
import com.pzero.v1.business.services.authentication.AuthenticationService;
import com.pzero.v1.persistence.models.User;

public class AuthController {

	final static AuthenticationService auth = new AuthenticationSercviceImplementation();
	
	public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
		ObjectMapper om = new ObjectMapper();
		
		JSONResponse json = new JSONResponse();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if (request.getMethod().equals("POST")) {
			
		    JsonNode jsonNode = om.readTree(request.getReader());
		    String email = jsonNode.get("email").asText();
		    String password = jsonNode.get("password").asText();
		    
			try {
				User user = auth.login(email, password);
				json.setStatus("ok");
				json.setData(user);
				out.write(new ObjectMapper().writeValueAsString(json));
			} catch (BusinessException e) {
				json.setStatus("error");
				json.setMessage(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			
		}else {
			json.setStatus("error");
			json.setMessage("Invalid Method");
			out.write(new ObjectMapper().writeValueAsString(json));
		}
		
		out.flush();
		out.close();
		
	}
	
}

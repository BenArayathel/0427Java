package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.user.UserService;
import com.pzero.v1.business.services.user.UserServiceImpl;
import com.pzero.v1.persistence.models.User;

public class UserController {

	final static UserService sUser = new UserServiceImpl();
	
	public static void user(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONResponse json = new JSONResponse();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(request.getReader());
			
		if(request.getMethod().equals("POST")) {
			User nUser = new User();
			
			nUser.setEmail(jsonNode.get("email").asText());
			nUser.setPassword(jsonNode.get("password").asText());
			nUser.setUserType(jsonNode.get("userType").asText());
			nUser.setPersonId(jsonNode.get("personId").asText());
			System.out.println(nUser.toString());
			try {
				User rUser = sUser.createUser(nUser);			
				json.setStatus("ok");
				json.setData(rUser);
				out.write(new ObjectMapper().writeValueAsString(json));
				
			} catch (BusinessException e) {
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

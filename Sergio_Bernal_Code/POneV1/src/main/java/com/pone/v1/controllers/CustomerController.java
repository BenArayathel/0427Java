package com.pone.v1.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pone.v1.jsonmodels.JSONResponse;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.account.AccountService;
import com.pzero.v1.business.services.account.AccountServiceImpl;
import com.pzero.v1.business.services.person.PersonService;
import com.pzero.v1.business.services.person.PersonServiceImplementation;
import com.pzero.v1.business.validations.Validation;
import com.pzero.v1.persistence.models.Person;


public class CustomerController {
	
	final static PersonService sPerson = new PersonServiceImplementation();
	final static AccountService sAccount = new AccountServiceImpl();
	final static Validation valid = new Validation();

	public static void search(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String pathInfo = request.getPathInfo();
		String[] path = pathInfo.split("\\/");
		
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse json = new JSONResponse();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println(request.getMethod());
		
		switch (request.getMethod()) {
		case "GET":
			Person person = null;
			try {
				if(path[2].equals("id")) {
					person = sPerson.getPersonById(path[3]);
				}else {					
					person = sPerson.getPersonIdBySSN(path[2]);
				}
				
				if(person != null) {
					request.setAttribute("person", person);
					request.getRequestDispatcher("/api/account/all/"+person.getId()).forward(request, response);					
				}else {
					json.setStatus("error");
					json.setData("The Customer doesn't Exist.");
					out.write(new ObjectMapper().writeValueAsString(json));
				}
			} catch (BusinessException | ServletException e) {
				json.setStatus("error");
				json.setData(e.getMessage());
				out.write(new ObjectMapper().writeValueAsString(json));
			}
			break;
			
		case "POST":
			
			JsonNode jsonNode = mapper.readTree(request.getReader());
			Person nPerson = new Person();
			
			try {
				
				nPerson.setName(jsonNode.get("name").asText());
				nPerson.setLastName(jsonNode.get("lastName").asText());
				nPerson.setSsn(jsonNode.get("ssn").asText());
				nPerson.setDob(valid.isValidDate(jsonNode.get("dob").asText()));
				nPerson.setPhoneNumber(jsonNode.get("phoneNumber").asText());
				//TODO Change cityd by City or add the whole address
				nPerson.setCityd(jsonNode.get("city").asText());
				
				// rPerson - Response Person
				Person rPerson = sPerson.createPerson(nPerson);

				json.setStatus("ok");
				json.setData(rPerson);
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

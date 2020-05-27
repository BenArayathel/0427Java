package com.company.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.controller.RequestHelper;
import com.company.model.Customer;
import com.company.model.Registration;
import com.company.service.ServiceLayer;
import com.company.servlets.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Servlets
 */
public class Servlets extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//    public Servlets() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		try {
//			// 1. Load driver
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}		
		
		
		String pathOrJsonString = RequestHelper.process(request, response);
		
		if (pathOrJsonString==null) {
			throw new ServletException("Path or object not found from RequestHelper");
		} else if (pathOrJsonString.endsWith(".html")){
			System.out.println("Sending dispatch to " + pathOrJsonString);
			System.out.println(request.getContextPath() + pathOrJsonString);
			response.sendRedirect(request.getContextPath()+pathOrJsonString);
//			response.sendRedirect(pathOrJsonString);
//			response.sendRedirect("/api" + pathOrJsonString);
//			response.sendRedirect("http://localhost:9999" + request.getContextPath()+pathOrJsonString);

				
			return;
		} else if (isJsonString(pathOrJsonString)) {
			response.setContentType("application/json");
			response.getWriter().write(pathOrJsonString);
		} else {
			throw new IOException("attempted to return bad redirect or improper formatted JSON string: " + pathOrJsonString);
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		if (pathInfo == null || pathInfo.isEmpty() || pathInfo.equals("/")) {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return;
		}
		
//		response.sendRedirect("/BankWebApp/customerPage.html");
//		System.out.println("1");
//		response.sendRedirect("/BankWebApp/api/customerPage.html");
//		System.out.println("2");
//		response.sendRedirect("/customerPage.html");
		System.out.println("Entered doPost...");

		
		doGet(request, response);
	
		
		//request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request, response);	
//		RequestDispatcher dis=request.getRequestDispatcher(RequestHelper.process(request,response));
//		dis.forward(request, response);
			
		
		/*
	
		try {
			// 1. load and register JDBC driver for MySQL (
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. then you can connect to oracle db through your usual DAO Implementations
			// response.getWriter().println("Connected to Oracle using thin driver");;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		//doGet(request, response);
		
		System.out.println("Inside doPost Registration");
		
		// Start processing request and convert string response into java object.
		ObjectMapper om = new ObjectMapper();
		Registration registration = om.readValue(request.getReader(),com.company.model.Registration.class);
		
		// check output.
		System.out.println(registration);
		
		System.out.println(registration.getLoginName());
		System.out.println(registration.getLoginPassword());

		// Process the request (now a java object)
		Customer customer = new Customer();
		customer = serviceLayer.validateLogin(registration.getLoginName(), registration.getLoginPassword());
		
		System.out.println("Servelet: " + customer);

		// set response content type to json format.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		
		PrintWriter out = response.getWriter();
		
		if (customer != null) {
			System.out.println("Inside customer is not null and try to redirect...");
			// convert java object to string that can be sent to front end (as a response)
			out.write(new ObjectMapper().writeValueAsString(customer));
			
			out.flush();
			
		}
		
		*/
			
	}

	private boolean isJsonString(String jsonString) {
		boolean valid;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			objectMapper.readTree(jsonString);
			valid = true;
		} catch (IOException e) {
			valid = false;
		}
		
		return valid;
	}
	
		
}

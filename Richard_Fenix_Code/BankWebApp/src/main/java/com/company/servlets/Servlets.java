package com.company.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.model.Customer;
import com.company.model.Registration;
import com.company.service.ServiceLayer;
import com.company.servlets.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Servlets
 */
public class Servlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private final ServiceLayer serviceLayer = new ServiceLayer();
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "bank_test";
	private static final String PASSWORD = "password";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 1. Load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. connect to oracle db
//			Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("connection established successfully...!!");     
		} catch (Exception ex) {
			System.out.println(ex);
		}

		
		
		System.out.println("Inside doGet Registration");
		
		response.setContentType("application/json");


		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		System.out.println(customer);

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
			
	}

		
}

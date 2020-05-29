package com.friendshipBank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendshipBank.dao.userAccessDAO;
import com.friendshipBank.dao.impl.userAccessDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.model.users;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class BankLogin extends HttpServlet{
	
	private static String PENDING = "PENDING";
	private static String CUSTOMER = "CUSTOMER";
	private static String EMPLOYEE = "EMPLOYEE";
	private static String REJECTED = "REJECTED";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		
		PrintWriter pw = res.getWriter();
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
		List<userAccess> customerList = new ArrayList<>();
		userAccessDAO userDAO = new userAccessDAOImpl();
		
		
			System.out.println("In doPost");

			res.setContentType("application/json");
						
			ObjectMapper mapper = new ObjectMapper();
			users p1 = new users();
							
			try 
	    	{					
//				users user = mapper.readValue(req.getReader(), com.friendshipBank.model.users.class);
				p1 = mapper.readValue(req.getReader(), com.friendshipBank.model.users.class);


				System.out.println(p1);
			    System.out.println("username = " + p1.getUserName());
			    System.out.println("password = " + p1.getUserPassword());
			    
				String username = p1.getUserName();
				String password = p1.getUserPassword();
				
				userAccess = userService.loginByUsernameAndPassword(username, password);
  				HttpSession session = req.getSession();
				
				if(userAccess.getUserName().equals(username) && userAccess.getUserPassword().equals(password)) {
					if(userAccess.getAccountStatus().equals(PENDING)) 
					{
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " ACCOUNT STATUS: " + PENDING);
	  
	    				session.setAttribute("userAccess", userAccess);
						pw.write("newCustomerDash.html");		
					}
					else if(userAccess.getAccountStatus().equals(CUSTOMER)) 
					{
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " ACCOUNT STATUS: " + CUSTOMER);
						
	    				session.setAttribute("userAccess", userAccess);
						pw.write("CustomerDashboard.html");							
					}
					else if(userAccess.getAccountStatus().equals(EMPLOYEE)) 
					{
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " ACCOUNT STATUS: " + EMPLOYEE);
						
	    				session.setAttribute("userAccess", userAccess);
						pw.write("EmployeeDashboard.html");	
					}
					else if(userAccess.getAccountStatus().equals(REJECTED)) 
					{
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
						System.out.println("SYSTEM:  " + userAccess.getUserName() + " ACCOUNT STATUS: " + REJECTED);
						System.out.println("SYSTEM:  YOUR ACCOUNT HAS BEEN REJECTED AND IS MARKED FOR DELETION");

					}
				}
				else
				{
					System.out.println("Login Fail...");
					pw.write("BankFailLogin.html");	
				}
	    	}
	    	catch (BusinessException e)
	    	{	
	    		System.out.println(e.getMessage());
	    	}
			
			
			
			
		
	}

}

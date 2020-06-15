package com.friendshipBank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.newUserLogin;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class CreateNewLoginProfile extends HttpServlet{
	
	private static String PENDING = "PENDING";
//	private static String CUSTOMER = "CUSTOMER";
//	private static String EMPLOYEE = "EMPLOYEE";
//	private static String REJECTED = "REJECTED";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		
		customer customer = new customer();
    	
		newUserLogin newUser = new newUserLogin();
		userAccess userAccess = new userAccess();
		userAccessService userService = new userAccessServiceImpl();
		
		HttpSession session = req.getSession(false);
		if(session==null) 
		{
			pw.write("index.html");		
		}else {
			 customer = (customer) session.getAttribute("customer");

		}

			System.out.println("In new Login doPost");

			res.setContentType("application/json");
						
			ObjectMapper mapper = new ObjectMapper();
							
			try 
	    	{					
				newUser = mapper.readValue(req.getReader(), com.friendshipBank.model.newUserLogin.class);

				System.out.println(newUser);
			    System.out.println("username = " + newUser.getUserName());
			    System.out.println("password = " + newUser.getUserPassword());
			    System.out.println("password Con = " + newUser.getUserPassCon());
			    
			    if(newUser.getUserPassword().equals(newUser.getUserPassCon())) {
			    	userAccess.setCustomerID(customer.getCustomerID());
			    	userAccess.setUserName(newUser.getUserName());
			    	userAccess.setUserPassword(newUser.getUserPassword());
			    	userAccess.setAccountStatus(PENDING);
			    }
			    

			    
				
	       		userAccess = userService.createUserAccess(userAccess);
	       		if(userAccess.getUserID() != null) {
	       			System.out.println("\nSYSTEM:  CUSTOMERID: " + userAccess.getCustomerID() + " LOGIN PROFILE HAS SUCCESSFULLY BEEN CREATED");
	       			System.out.println("SYSTEM:  PLEASE MAKE A NOTE OF YOUR LOGIN USERNAME: " + userAccess.getUserName());
	       			System.out.println(userAccess);
	       			session.invalidate();
					pw.write("newRegistrationComp.html");	
	       		};


	    	}
	    	catch (BusinessException e)
	    	{	
	    		System.out.println(e.getMessage());
	    	}
			
			
			
			
		
	}
	
	

}

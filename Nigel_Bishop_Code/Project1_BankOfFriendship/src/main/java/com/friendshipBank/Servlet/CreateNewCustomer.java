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
import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.dao.userAccessDAO;
import com.friendshipBank.dao.impl.userAccessDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.model.users;
//import com.friendshipBank.presentation.newuser.CreateLoginProfile;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class CreateNewCustomer extends HttpServlet{
	
//	private static String PENDING = "PENDING";
//	private static String CUSTOMER = "CUSTOMER";
//	private static String EMPLOYEE = "EMPLOYEE";
//	private static String REJECTED = "REJECTED";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		
		PrintWriter pw = res.getWriter();
//		userAccessService userService = new userAccessServiceImpl();
//		userAccess userAccess = new userAccess();
//		List<userAccess> customerList = new ArrayList<>();
//		userAccessDAO userDAO = new userAccessDAOImpl();
		customer customer = new customer();
    	customerService custService = new customerServiceImpl();

		
		
			System.out.println("In new customer doPost");

			res.setContentType("application/json");
						
			ObjectMapper mapper = new ObjectMapper();
							
			try 
	    	{					
				customer = mapper.readValue(req.getReader(), com.friendshipBank.model.customer.class);

//				System.out.println(customer);		
				
    			customer = custService.createCustomer(customer);
    			
    			if(customer.getCustomerID() != null) {
    				System.out.println("\nSYSTEM:  CUSTOMER: " + customer.getFirstName() + " " + customer.getLastName() + " HAS REGISTERED WITH BANK OF FRIENDSHIP");
    				System.out.println(customer);
    				HttpSession session = req.getSession();
    				session.setAttribute("customer", customer);
					pw.write("newUserLoginAccount.html");		

    			} 

	    	}
	    	catch (BusinessException e)
	    	{	
	    		System.out.println(e.getMessage());
	    	}
			
			
			
			
		
	}

}

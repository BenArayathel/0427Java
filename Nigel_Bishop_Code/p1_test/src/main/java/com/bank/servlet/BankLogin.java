package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.userAccessDAO;
import com.bank.dao.impl.userAccessDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.userAccess;
import com.bank.service.userAccessService;
import com.bank.service.impl.userAccessServiceImpl;





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

		
		try 
    	{
			
			String uname=req.getParameter("userName");
			String upass=req.getParameter("userPassword");

			System.out.println(uname);
			System.out.println(upass);
			userAccess = userService.loginByUsernameAndPassword(uname, upass);

			
			if(userAccess.getUserName().equals(uname) && userAccess.getUserPassword().equals(upass)) {
				if(userAccess.getAccountStatus().equals(PENDING)) 
				{
					pw.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
					pw.println("SYSTEM:  " + PENDING);

				}
				else if(userAccess.getAccountStatus().equals(CUSTOMER)) 
				{
					pw.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
					pw.println("SYSTEM:  " + CUSTOMER);
					
				}
				else if(userAccess.getAccountStatus().equals(EMPLOYEE)) 
				{
					pw.println("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
					pw.println("SYSTEM:  " + EMPLOYEE);
					
				}
				else if(userAccess.getAccountStatus().equals(REJECTED)) 
				{
					pw.println("SYSTEM:  YOUR ACCOUNT HAS BEEN REJECTED AND IS MARKED FOR DELETION");
				}
			}
    	}
    	catch (BusinessException e)
    	{	
    		System.out.println(e.getMessage());
    	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println("In doPost");
////		PrintWriter pw = res.getWriter();
////		res.setContentType("text/html");
//		
////		users user1 = new users();
//		
//		String user=req.getParameter("userName");
//		String pass=req.getParameter("userPassword");
//		
////		user1.setUserName(req.getParameter("userName"));
////		user1.setUserPassword(req.getParameter("userPassword"));
////		System.out.println(user1.getUserName());
////		System.out.println(user1.getUserPassword());
//		
//		if(user.equals("nigel") && pass.equals("password"))
////		if(user1.getUserName().equals("nigel") && user1.getUserPassword().equals("password"))
//		{
//			pw.println("Login Success...");
////			res.sendRedirect("http://localhost:9090/basicServlets/WelcomePage.html");
////			res.sendRedirect("/WelcomePage.html");
//
////			databaseUrl
//		}
//		else
//			pw.println("Login Fail...");
//
////			res.sendRedirect("http://localhost:9090/basicServlets/FailLogin.html");
//		pw.close();

	}
	
}

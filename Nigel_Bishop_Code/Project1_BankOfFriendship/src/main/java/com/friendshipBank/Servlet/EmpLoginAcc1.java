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
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.transaction;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.transactionService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.transactionServiceImpl;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class EmpLoginAcc1 extends HttpServlet{
	
	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		PrintWriter pw = res.getWriter();
		userAccess userAccess = new userAccess();
		userAccessDAO userDAO = new userAccessDAOImpl();
		List<userAccess> userAccessList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		
		
			System.out.println("In Employee Dash doGet");

			res.setContentType("application/json");

			HttpSession session = req.getSession(false);
			if(session==null) 
			{
				pw.write("index.html");	
			}else {
				userAccess = (userAccess) session.getAttribute("userAccess");
			}

			try 
        	{
        		userAccessList = userDAO.getAllUserLoginAccounts();
    			pw.write(mapper.writeValueAsString(userAccessList));

        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }

	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		PrintWriter pw = res.getWriter();
		userAccess userAccess = new userAccess();
		userAccess userAccess2 = new userAccess();

		bankAccount bankAccount1 = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();

		bankAccountService bankService = new bankAccountServiceImpl();
    	transaction transaction = new transaction();
		transactionService transService = new transactionServiceImpl();
		
		
		userAccessDAO userDAO = new userAccessDAOImpl();
		userAccessService userService = new userAccessServiceImpl();
		List<userAccess> userAccessList = new ArrayList<>();

			System.out.println("In Customer Dash doPost");

			res.setContentType("application/json");
						
			ObjectMapper mapper = new ObjectMapper();
			
			HttpSession session = req.getSession(false);
			if(session==null) 
			{
				pw.write("index.html");		
			}else {
				userAccess = (userAccess) session.getAttribute("userAccess");

			}

			try 
        	{
				System.out.println();
				userAccess2 = mapper.readValue(req.getReader(), com.friendshipBank.model.userAccess.class);
        		userService.updateUserAccessStatus(userAccess2.getCustomerID(), userAccess2.getAccountStatus());
        		System.out.println("SYSTEM:  " + userAccess2.getCustomerID() + " PENDING ACCOUNT HAS BEEN APPROVED.. ");
		
			    
        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }
	}

}

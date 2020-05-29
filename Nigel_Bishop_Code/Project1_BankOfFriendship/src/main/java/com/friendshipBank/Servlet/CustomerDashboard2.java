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
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class CustomerDashboard2 extends HttpServlet{

	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		PrintWriter pw = res.getWriter();
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
		bankAccount bankAccount1 = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();

		bankAccountService bankService = new bankAccountServiceImpl();
//		List<userAccess> customerList = new ArrayList<>();
//		userAccessDAO userDAO = new userAccessDAOImpl();
		customer customer = new customer();
    	customerService custService = new customerServiceImpl();

		
		
			System.out.println("In Customer Dash doGet");

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

        		bankAccount2 = bankService.getAccountInfo(userAccess.getCustomerID(), saveAccType);

        		String accStat2 = bankAccount2.getAccountStatus();
       		
        		if(accStat2.equals(accStatus))
        		{
        			System.out.println("ACCOUNT STATUS:   " + bankAccount2.getAccountStatus());	
        			System.out.println("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
        			System.out.println("        PLEASE CONTACT A BANK EMPLOYEE");
        		}
        		else 
        		{
        			//GET BALANCE

        			pw.write(mapper.writeValueAsString(bankAccount2.getBalance()));

        		}
        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }

		
		
	}
	
	
}

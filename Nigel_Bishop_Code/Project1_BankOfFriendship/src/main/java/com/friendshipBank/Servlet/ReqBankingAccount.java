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

public class ReqBankingAccount extends HttpServlet{
	
	private static String checkAccType = "CHECKING";
	private static Double checkblance = 300.00;
	private static String saveAccType = "SAVING";
	private static Double saveblance = 100.00;

	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		
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

		
		
			System.out.println("In new Bank Account doPost");

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
				bankAccount1 = mapper.readValue(req.getReader(), com.friendshipBank.model.bankAccount.class);

				
				if(bankAccount1.getAccountType().equals(checkAccType))
				{
	        		bankAccount2.setCustomerID(userAccess.getCustomerID());
	        		bankAccount2.setAccountType(bankAccount1.getAccountType());
	        		bankAccount2.setBalance(checkblance);
	        		bankAccount2.setAccountStatus(bankAccount1.getAccountStatus());
	        		
	        		bankAccount2 = bankService.createNewBankAccount(bankAccount2);
	        		
		       		if(bankAccount2.getAccountID() != null) {
//		       			String compMessage = "YOUR REQUEST FOR A CHECKING ACCOUNT HAS BEEN COMPLETED, A DEFAULT BALANCE OF $300 HAS BEEN ADDED TO THE ACCOUNT";
//		       			pw.write(compMessage);
		       			System.out.println(bankAccount2);
		       		}
	        		
				}
				else if(bankAccount1.getAccountType().equals(saveAccType))
				{
	        		bankAccount2.setCustomerID(userAccess.getCustomerID());
	        		bankAccount2.setAccountType(bankAccount1.getAccountType());
	        		bankAccount2.setBalance(saveblance);
	        		bankAccount2.setAccountStatus(bankAccount1.getAccountStatus());
	        		
	        		bankAccount2 = bankService.createNewBankAccount(bankAccount2);
	        		
		       		if(bankAccount2.getAccountID() != null) {
//		       			String compMessage = "YOUR REQUEST FOR A SAVING ACCOUNT HAS BEEN COMPLETED, A DEFAULT BALANCE OF $100 HAS BEEN ADDED TO THE ACCOUNT";
//		       			pw.write(compMessage);
		       			System.out.println(bankAccount2);
		       		}
	        		
				}


	    	}
	    	catch (BusinessException e)
	    	{	
	    		System.out.println(e.getMessage());
	    	}
			
			
			
			
		
	}
	

}

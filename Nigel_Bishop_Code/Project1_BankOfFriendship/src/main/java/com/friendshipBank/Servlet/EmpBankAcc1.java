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
import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dao.userAccessDAO;
import com.friendshipBank.dao.impl.bankAccountDAOImpl;
import com.friendshipBank.dao.impl.userAccessDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.transaction;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.transactionService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.transactionServiceImpl;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class EmpBankAcc1 extends HttpServlet{

	
	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";
	
	private static String activeStatus = "ACTIVE";
	private static String rejctedStatus = "REJECTED";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		PrintWriter pw = res.getWriter();
		userAccess userAccess = new userAccess();
		userAccessDAO userDAO = new userAccessDAOImpl();
		List<userAccess> userAccessList = new ArrayList<>();
		List<bankAccount> bankAccountList = new ArrayList<>();
		bankAccountDAO bankDAO = new bankAccountDAOImpl();
		bankAccountService bankService = new bankAccountServiceImpl();
		
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
        		bankAccountList = bankDAO.getAllBankAccounts();
    			pw.write(mapper.writeValueAsString(bankAccountList));

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
				bankAccount1 = mapper.readValue(req.getReader(), com.friendshipBank.model.bankAccount.class);
				
        		bankService.updateBankAccountStatus(bankAccount1.getCustomerID(), bankAccount1.getAccountType(), bankAccount1.getAccountStatus());

        		System.out.println("SYSTEM:  " + bankAccount1.getCustomerID() + " PENDING BANK ACCOUNT HAS BEEN APPROVED.. ");
		
			    
        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }
	}

	
}

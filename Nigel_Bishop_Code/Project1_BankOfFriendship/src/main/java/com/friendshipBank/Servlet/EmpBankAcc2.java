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
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;

public class EmpBankAcc2 extends HttpServlet{
	
	private static String accStatus = "PENDING";

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
				bankAccountList = bankDAO.getAllBankAccountsStatus(accStatus);
    			pw.write(mapper.writeValueAsString(bankAccountList));

        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }

	}

}

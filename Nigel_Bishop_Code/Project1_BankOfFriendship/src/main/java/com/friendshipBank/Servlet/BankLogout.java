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
import com.friendshipBank.model.userAccess;

public class BankLogout extends HttpServlet{
	
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
			
   			session.invalidate();
//   			userAccess.getUserName()
			String logoutmessage = "Thank you " +  userAccess.getUserName() + " for using Bank of Friendship application";
			pw.write(mapper.writeValueAsString(logoutmessage));


//			try 
//        	{
//        		userAccessList = userDAO.getAllUserLoginAccountsStatus(accStatus);
//    			pw.write(mapper.writeValueAsString(userAccessList));
//
//        	}
//            catch (BusinessException e)
//            {
//            	MainDriver.SystemLog.info(e.getMessage());
//            	MainDriver.SystemLog.error(e.getMessage());
//            }

	}

}

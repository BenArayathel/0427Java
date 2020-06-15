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
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class EmpLoginAcc2 extends HttpServlet{

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
        		userAccessList = userDAO.getAllUserLoginAccountsStatus(accStatus);
    			pw.write(mapper.writeValueAsString(userAccessList));

        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }

	}
	
}

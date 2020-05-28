package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.controllers.DeleteController;
import com.controllers.GetController;
import com.controllers.PostController;
import com.controllers.PutController;
import com.controllers.RequestHelper;
import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;
import com.models.CurrentUser;
import com.models.User;
import com.dao.impl.UserDaoImpl;
import com.dao.impl.AccountDaoImpl;
import com.services.impl.UserServiceImpl;

public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger loggy = Logger.getLogger(User.class);
	private static UserServiceImpl uSI = new UserServiceImpl();
	private static UserDaoImpl uDI = new UserDaoImpl();
	private static AccountDaoImpl aDI = new AccountDaoImpl();
    private ObjectMapper mapper = new ObjectMapper();
	
	
    public MasterServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("GET".equalsIgnoreCase(req.getMethod())) {
			
				if (res == null) {
					res.sendError(404, "User could not be found");
				}
				else {
					res = GetController.getUserInfo(req, res);
				}
			}
		else {
			res.sendError(404, "Error. Please use the get action next time");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if ("POST".equalsIgnoreCase(req.getMethod())) 
		{
			res = PostController.postInfo(req, res);
		}
		else {
			res.sendError(404, "Error. Please use the post action next time");
		}
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("PUT".equalsIgnoreCase(req.getMethod())) {
			res = PutController.updateInfo(req, res);
		}
		
		else {
			res.sendError(404, "Count not update information");
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("DELETE".equalsIgnoreCase(req.getMethod())) {
			
			String message = DeleteController.deleteUser(req, res);
			if(res == null) {
				res.sendError(404, "Couldn't delete user");
			}
			
			else {
				res.getWriter().append("User deleted");
			}
		}
	}
	

	
	

}

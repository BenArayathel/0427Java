package com.bhank.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhank.webapp.controllers.RequestHelper;


public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	client -> MasterServlet -> RequestHelper -> controller+s -> service -> ....


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.getRequestDispatcher("string").forward(request,resposne);
			
		request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request,response);

	}

}

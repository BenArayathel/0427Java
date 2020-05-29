package com.project1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.controllers.RequestHelper;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String processedString = RequestHelper.process(request, response);
		System.out.println(processedString);
		
		if (processedString == null) {
			throw new ServletException("Could not follow instruction from RequestHelper");
		} else if (isJson(processedString)) {
			response.setContentType("application/json");
			response.getWriter().write(processedString);
		} else if (processedString.endsWith(".html")) {
			response.sendRedirect(request.getContextPath() + processedString);
		} else if (processedString.contains("/app/")) {
			request.getRequestDispatcher(processedString).forward(request, response);
		} else {
			throw new IOException("Bad redirect or file format: " + processedString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean isJson(String str) {
		boolean isItJson;
		final ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.readTree(str);
			isItJson = true;
		} catch (IOException e) {
			isItJson = false;
		}
		
		return isItJson;
	}

}

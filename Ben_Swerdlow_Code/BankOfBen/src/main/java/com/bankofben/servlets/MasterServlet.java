package com.bankofben.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bankofben.controllers.RequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final static Logger loggy = Logger.getLogger(MasterServlet.class);
       
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
		loggy.info("Get is not used in this application since all information is sensitive.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		loggy.setLevel(Level.INFO);
		loggy.info("Test 123");
		
		String pathOrJsonString = RequestHelper.process(request, response);
		
		if (pathOrJsonString==null) {
			ServletException s = new ServletException("Could not follow instruction from RequestHelper");
			loggy.error(s);
			throw s;
		} else if (isValidJsonString(pathOrJsonString)) {
			response.setContentType("application/json");
			response.getWriter().write(pathOrJsonString);
		} else if (pathOrJsonString.endsWith(".html")) {
			// if pathOrJsonString is a valid .html path, forward the request and response there
			System.out.println("Sending dispatch to "+pathOrJsonString);
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath()+pathOrJsonString);
		} else if (pathOrJsonString.contains("/api/")) {
			System.out.println("Sending request (and response) to "+pathOrJsonString);
			request.getRequestDispatcher(pathOrJsonString).forward(request, response);
		} else {
			IOException e = new IOException("Attempted to return bad redirect or improperly formatted JSON string: "+pathOrJsonString);
			loggy.error(e);
			throw e;
		}
	}

	private boolean isValidJsonString(String possibleJsonString) {
		boolean valid;
		final ObjectMapper objMapper = new ObjectMapper();
		
		try {
			objMapper.readTree(possibleJsonString);
			valid = true;
		} catch (IOException e) {
			valid = false;
		}
		
		return valid;
	}

}

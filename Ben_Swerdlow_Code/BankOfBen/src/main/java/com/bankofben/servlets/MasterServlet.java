package com.bankofben.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String pathOrJsonString = RequestHelper.process(request, response);
		
		if (pathOrJsonString==null) {
			throw new ServletException("Could not follow instruction from RequestHelper");
		} else if (pathOrJsonString.endsWith(".html")) {
			// if pathOrJsonString is a valid .html path, forward the request and response there
			System.out.println("Sending dispatch to "+pathOrJsonString);
//			System.out.println(request.getServletPath());
			System.out.println(request.getContextPath());
//			request.getRequestDispatcher(pathOrJsonString).forward(request, response);
			response.sendRedirect(request.getContextPath()+pathOrJsonString);
		} else if (validJsonString(pathOrJsonString)) {
			response.setContentType("application/json");
			response.getWriter().write(pathOrJsonString);
		} else {
			throw new IOException("Attempted to return bad redirect or improperly formatted JSON string: "+pathOrJsonString);
		}
		
		
		
		
//		String respString = "home.html";
//		
//		String uri = request.getRequestURI(); 
//		System.out.println(uri);
//		
//		switch(uri) {
//		case "/BankOfBen/api/InitialRegistration":
//			System.out.println("In initial registration");
//			try {
//				respString = InitialRegistration.register(request, response); 
//				System.out.println(respString);
//			} catch (IOException | BusinessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			break;
////		case "/BankOfBen/api/CompleteRegistration":
////			return CompleteRegistration.register(request, response);
//		default:
//			System.out.println("Didn't recognize option");
//		}
//		
//		if (respString.endsWith(".html")){
//			System.out.println(respString);
//			response.sendRedirect(respString);
//		} else {
//			response.setContentType("application/json");
//			System.out.println(respString);
//			PrintWriter out = response.getWriter();
//			out.write(respString);
////			out.flush();
//		}
		
		
//		try {
//			response.sendRedirect(InitialRegistration.register(request, response));
//		} catch (IOException | BusinessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request,response);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request,response);
//		} catch (ServletException | IOException | BusinessException e) {
//			loggy.error(e);
//			response.sendRedirect("error.html");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean validJsonString(String possibleJsonString) {
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

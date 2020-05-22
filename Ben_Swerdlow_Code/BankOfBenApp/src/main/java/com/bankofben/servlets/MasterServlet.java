package com.bankofben.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bankofben.controllers.RequestHelper;
import com.bankofben.exceptions.BusinessException;

/**
 * Servlet implementation class MasterServlet
 */

//<servlet>
//  <description></description>
//  <display-name>MasterServlet</display-name>
//  <servlet-name>MasterServlet</servlet-name>
//  <servlet-class>com.bankofben.servlets.MasterServlet</servlet-class>
//</servlet>
//<servlet-mapping>
//  <servlet-name>MasterServlet</servlet-name>
//  <url-pattern>/MasterServlet</url-pattern>
//</servlet-mapping>

@WebServlet(name="MasterServlet", urlPatterns = {"api/*"})
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
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request,response);
		} catch (ServletException | IOException | BusinessException e) {
			loggy.error(e);
			response.sendRedirect("error.html");
		}
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.example.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Servlet Hierarchy
	 * 		Servlet (i) -> GenericServlet (ac) -> HttpServlet (c) -> CustomNameServlet (c)
	 */
	
	/*
	 * Servlet Lifecycle
	 * 	init = initializes the servlet in the servlet container (web container)
	 * 		Called once, either on first request (default) or on startup
	 * 	Service - handles a request to this servlet. Basically forwards request to appropriate method (doGet, doPost, doDelete, etc.)
	 * 		Note: The method is abstract in GenericServlet
	 * 		Note: This method is implemented in HttpServlet
	 * 		- Called on every request to the servlet
	 * 	destroy - destroys the servlet
	 * 		Typically called when the servlet container stops/restarts
	 * 		Usually only called once during shutdown (shouldn't be called while server is running) 
	 * 
	 * 
	 * NOTES FROM web.xml (just in case it gets overwritten:
	 * 
    	<!-- less than 0 means lazy loading (wait until it is needed to initiate the servlet) -->
    	<!-- greater than 0 means eager loading (load immediately when the server starts). 
    		Eager loading will happen lowest value to highest value (0 before 1, 1 before 2, etc.) -->
  		<!-- Servlet Context (Global) -->
  		<!-- It's a parameter that are specified for an entire application, available to all servlets -->
	 * 
	 * 
	 * HOMEWORK:
	 * 	Set login page from home page (see slack)
	 *  create a page that allows you to login with a post method and redirect to the home page and use get to send to some other page
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleServlet() {
        super();
        System.out.println("Servlet being created");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet method is being called");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost method is being called");
		String db = request.getServletContext().getInitParameter("databaseUrl");
		System.out.println(db);
		doGet(request, response);
	}
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Servlet is servicing a request");
    	super.service(req, resp);
    }
    
    @Override
	public void destroy() {
    	System.out.println("Servlet is being destroyed");
    	super.destroy();
    }
    
    @Override
    public void init() throws ServletException {
    	System.out.println("Servlet is being initialized");
    	super.init();
    }
    
//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	
//    }
//    
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	
//    }

}

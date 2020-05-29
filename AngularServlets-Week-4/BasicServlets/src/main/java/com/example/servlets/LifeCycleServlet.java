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
	 *Servlet Hierachy
	 *
	 * Servlet (i) -> GenericServlet (ac) -> HttpServlet (c) -> CustomServlet (c)
	 * 
	 */
	
	/*
	 * Servlet Lifecycle
	 * 
	 * init - intializes the servlet in the servlet container (web container)
	 * 			Called once 
	 * 			- either on first request(default) or on startup
	 * 
	 * Service - Handles a request to this servlet. Basically forwards request to approrate method (doGet, doPost, doDelete, etc..)
	 * 			NOTE: The method is abstract in GenericServlet 
	 * 			NOTE: This method is implemented in HttpServlet
	 * 			-Called on every request to the servlet
	 * 
	 * destroy - destroy the servelt 
	 * 			- Typicalled called when the servlet container stops/restarts
	 * 			-usually only on shutdown and only once
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleServlet() {
        super();
        System.out.println("Servlet being created");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet method is being called");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servlet is servicing a request");
//		super.service(req, resp);
	}
	
	@Override
	public void destroy(){
		System.out.println("Servlet destroyed");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet is being initialized");
		super.init();
		//throw new ServletException();
	}
}

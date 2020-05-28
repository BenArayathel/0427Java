package com.bhank.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Customer;
import com.bhank.webapp.model.Login;
import com.bhank.webapp.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerServiceImpl service = new CustomerServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login servlet doGet");
		response.setContentType("text/plain");
		response.getWriter().write("http://localhost:9090/BhankWebApp/customer_login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login servlet doPost");
		ObjectMapper om = new ObjectMapper();
		Login login = om.readValue(request.getReader(), com.bhank.webapp.model.Login.class);

		System.out.println(login);

		try {
			Customer customer = service.selectCustomerByNameAndPassword(login.getUsername(), login.getPassword());
			System.out.println("login successful");
			Cookie customerId = new Cookie("customerId", customer.getId());
			System.out.println(customerId);
			HttpSession session = request.getSession(true);
			session.setAttribute("currentCustomer", customer);
//			Cookie password = new Cookie(login.getPassword(), request.getParameter("password"));
			response.addCookie(customerId);
//			response.addCookie(password);
//			HttpSession session = request.getSession();
//			session.setAttribute("username", customer.getName());
//			session.setAttribute("id", customer.getId());
//			System.out.println(session);
			response.setContentType("text/plain");
			response.getWriter().write("http://localhost:9090/BhankWebApp/customer_home.html");
		} catch (BusinessException e) {
			System.out.println("login unsuccessful");
			doGet(request, response);
			e.printStackTrace();
		}

//		final String LOGIN_NAME = "bilbo";
//		final String LOGIN_PASSWORD = "ring";
//		
//		if(login.getUsername().equals(LOGIN_NAME) && login.getPassword().equals(LOGIN_PASSWORD)) {
//			System.out.println("login successful");
//			response.setContentType("text/plain");
//			response.getWriter().write("http://localhost:9090/BhankWebApp/customer_home.html");
//		} else {
//			System.out.println("login unsuccessful");
//			doGet(request, response);
//		}
	}

}

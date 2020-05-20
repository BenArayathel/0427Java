package com.forms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlets should return information or redirect
		// server side session HttpSession or localstorage, when the user logs out, delete that local storage
//		Set<String> attrSet = getAttributeNameSet(request);
//		System.out.println(attrSet);
//		String body = getBodyString(request);
//		System.out.println(body);
//		System.out.println(request.getParameter("username"));// Gets name from Postman (client)
//		System.out.println(request.getParameter("password"));
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		
//		
//		User user = new User(username, password);
//		
//		
		ObjectMapper objMapper = new ObjectMapper();
		// This maps from the body to the User
		User user = objMapper.readValue(request.getReader(), com.models.User.class);
		
//		response.getWriter().write(new ObjectMapper().writeValueAsString(user));
//		System.out.println(user);
		if (!user.getUsername().equals("myUsername") && !user.getPassword().equals("P4ssw0rd!")) {
			System.out.println("Bad username and password");
			response.sendRedirect("home.html");
//			response.getWriter();
		} else if (!user.getUsername().equals("myUsername")) {
			System.out.println("Bad username");
			response.sendRedirect("home.html");
//			response.sendRedirect("loginUnsuccessful.html");
		} else if (!user.getPassword().equals("P4ssw0rd!")) {
			System.out.println("Bad password");
			response.sendRedirect("home.html");
			//
		} else {
			System.out.println("Good username and password");
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			response.sendRedirect("loginSuccessful.html");
//			String responseJsonString = objMapper.writeValueAsString(user);
//			System.out.println(responseJsonString);
//			responseJsonString = responseJsonString.substring(0, responseJsonString.length()-1)
//					+ ",\"url\":\"loginSuccessful.html\"}";
//			System.out.println(responseJsonString);
//			response.getWriter().write(responseJsonString);
//			response.sendRedirect("https://www.google.com");
//			response.getWriter().write("loginSuccessful.html");
		}
//		doGet(request, response);
	}
	
//	private String getBodyString(HttpServletRequest request) throws IOException {
//		StringBuilder sb = new StringBuilder();
//		BufferedReader reader = request.getReader();
//		try {
//			String line;
//			while((line=reader.readLine()) != null) {
//				sb.append(line).append('\n');
//			}
//		} finally {
//			reader.close();
//		}
//		return sb.toString();
//	}
	
//	private Set<String> getAttributeNameSet(HttpServletRequest request) {
//		Set<String> attrSet = new HashSet<>();
//		Enumeration<String> attributes = (Enumeration<String>) request.getAttributeNames();
//		while (attributes.hasMoreElements()) {
//			attrSet.add(attributes.nextElement());
//		}
//		return attrSet;
//	}

}

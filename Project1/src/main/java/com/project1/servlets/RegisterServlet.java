package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.impl.Project1ServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project1ServiceImpl p1s = new Project1ServiceImpl();
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		String returnMessage;
		
		ObjectMapper mapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		
		User u = new User(bodyObj.get("username").textValue(), bodyObj.get("password").textValue(), false);
		
		try {
			p1s.createUser(u);
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			returnMessage = "New user created successfully.\n"
							+ "Now redirecting to customer portal.\n"
							+ "If you are a new employee, please wait for approval from another employee.";
			pw.write(returnMessage);
		} catch (BusinessException e) {
			returnMessage = "Error - could not create user";
			pw.write(returnMessage);
		}
	}

}

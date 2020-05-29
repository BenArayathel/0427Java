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
 * Servlet implementation class ApproveEmployeeServlet
 */
public class ApproveEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returnMessage;
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		Project1ServiceImpl p1s = new Project1ServiceImpl();
		
		ObjectMapper mapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
				
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/index.html");
		} else {
			User u = (User) session.getAttribute("user");
			String username = bodyObj.get("username").textValue();
			try {
				User returnUser = p1s.makeUserEmployee(u, username);
				if (returnUser != null)
					returnMessage = "User is now an employee";
				else
					returnMessage = "Could not authorize new employee";
				pw.write(returnMessage);
			} catch (BusinessException e) {
				returnMessage = e.getMessage();
				pw.write(returnMessage);
			}
		}
	}

}

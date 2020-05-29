package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.model.User;
import com.project1.service.impl.Project1ServiceImpl;

/**
 * Servlet implementation class EmployeeViewAccountsServlet
 */
public class EmployeeViewAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeViewAccountsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Project1ServiceImpl p1s = new Project1ServiceImpl();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
				
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("/index.html");
		} else {
			User user = (User) session.getAttribute("user");
			String username = bodyObj.get("username").textValue();
			
			// Prepare response type for JS
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			
			try {
				List<Account> accountList = p1s.getAccountsByUserName(user, username);
				pw.write(mapper.writeValueAsString(accountList));
			} catch (BusinessException e) {
				System.out.println(e);
			}
		}
	}

}

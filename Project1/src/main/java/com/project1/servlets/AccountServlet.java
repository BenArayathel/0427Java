package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project1ServiceImpl p1s = new Project1ServiceImpl();
		response.setContentType("application/json");
				
		String accountName = request.getParameter("userAccounts");
		System.out.println(accountName);
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.html");
			System.out.println("No session");
		} else {
			User user = (User) session.getAttribute("user");
			System.out.println(user + "in single account method");
			
			try {
				int accountId = Integer.parseInt(accountName);
				Account acc = p1s.getAccountById(user, accountId);
				if (acc != null) {
					session.setAttribute("acc", acc);
					response.sendRedirect("account_activities.html");
				}
			} catch (NumberFormatException e) {
				System.out.println(e);
				response.sendRedirect("index.html");
			} catch (BusinessException e) {
				System.out.println(e);
				response.sendRedirect("index.html");
			}
		}
	}

}

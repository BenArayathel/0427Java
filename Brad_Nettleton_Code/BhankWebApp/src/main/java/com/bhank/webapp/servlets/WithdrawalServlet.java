package com.bhank.webapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Account;
import com.bhank.webapp.service.impl.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class WithdrawalServlet
 */
public class WithdrawalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountServiceImpl accountService = new AccountServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonString = request.getReader().readLine();
		jsonString = jsonString.substring(1, jsonString.length() - 1);
		String[] jsonData = jsonString.split(",");
		String accountId = jsonData[0].split(":")[1];
		String transactionAmount = jsonData[1].split(":")[1];
		try {
			Account account = accountService.withdraw(accountId.substring(1, accountId.length() - 1),
					Double.parseDouble(transactionAmount.substring(1, transactionAmount.length() - 1)));
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			ObjectMapper om = new ObjectMapper();
			System.out.println(om.writeValueAsString(account));
			om.writeValue(response.getWriter(), account);
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}
	}

}

package com.bhank.webapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Account;
import com.bhank.webapp.model.Customer;
import com.bhank.webapp.service.impl.AccountServiceImpl;
import com.bhank.webapp.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DepositServlet
 */
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerServiceImpl customerService = new CustomerServiceImpl();
	private AccountServiceImpl accountService = new AccountServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepositServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/index.html");
		} else {
			Customer customer = (Customer) session.getAttribute("currentCustomer");
			try {
				List<Account> accounts = accountService.selectAllAccountsByCustomer(customer.getId());
//				List<Account> accounts = accountService.selectAllAccountsByCustomer(cId);
				List<Account> activeAccounts = null;
				for (Account account : accounts) {
					if (!account.isPending() && account.isApproved()) {
						activeAccounts.add(account);
					}
				}
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				ObjectMapper om = new ObjectMapper();
				writer.write(om.writeValueAsString(activeAccounts));
				System.out.println(om.writeValueAsString(activeAccounts));
				om.writeValue(response.getWriter(), activeAccounts);

			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonString = request.getReader().readLine();
		jsonString = jsonString.substring(1, jsonString.length() - 1);
		String[] jsonData = jsonString.split(",");
		String accountId = jsonData[0].split(":")[1];
		String transactionAmount = jsonData[1].split(":")[1];
		try {
			Account account = accountService.deposit(accountId.substring(1, accountId.length() - 1),
					Double.parseDouble(transactionAmount.substring(1, transactionAmount.length() - 1)));
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			ObjectMapper om = new ObjectMapper();
//			writer.write(om.writeValueAsString(account));
			System.out.println(om.writeValueAsString(account));
			om.writeValue(response.getWriter(), account);

		} catch (NumberFormatException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JSONObject jsonObj = new JSONObject(jsonString);
//		JSONObject allObj = jsonObj.getJSONObject("obj");
//		JSONArray allArray = jsonObj.getJSONArray("all");
	}

}

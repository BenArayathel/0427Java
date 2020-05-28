package com.lunch.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.transaction.dao.TransactionDaoImpl;
import user.cust.account.models.User;

@WebServlet("/custranhelper")
public class EmpViewCustTrans_help_Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EmpViewCustTrans_help_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\nEmpViewCustTrans_Servlet is running.......");
		System.out.println("this is request: " + request.getParameter("var"));
		//  ?var=12345
		User user = new User();
		user.setUser_id(request.getParameter("var"));
	
		TransactionDaoImpl tdao = new TransactionDaoImpl();
		List<String> transactions = new ArrayList<>();
		
		
		//transactions = tdao.viewAllTransactions();		// view all
		transactions = tdao.viewCustTransactions(user);
		
		String t_in_Json = "[";
		
		for (String string : transactions) {
			t_in_Json += "{\"info\":\"" + string + "\"},";
		}
		t_in_Json = t_in_Json.substring(0, t_in_Json.length() - 1);
		
		t_in_Json = t_in_Json.concat("]");
		
		System.out.println(t_in_Json);
	 
	 
	 
	 
	 // https://stackoverflow.com/questions/2010990/how-do-you-return-a-json-object-from-a-java-servlet
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(t_in_Json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

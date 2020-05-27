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

@WebServlet("/viewall")
public class EmpViewAllTrans extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EmpViewAllTrans() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\nEmpViewAllTrans is running.......");
		
		TransactionDaoImpl tdao = new TransactionDaoImpl();
		List<String> transactions = new ArrayList<>();
		transactions = tdao.viewAllTransactions();
		
		String user_in_Json = "[";
		
		for (String string : transactions) {
			user_in_Json += "{\"info\":\"" + string + "\"},";
		}
		user_in_Json = user_in_Json.substring(0, user_in_Json.length() - 1);
		
		user_in_Json = user_in_Json.concat("]");
		
		System.out.println(user_in_Json);
	 
	 
	 
	 
	 // https://stackoverflow.com/questions/2010990/how-do-you-return-a-json-object-from-a-java-servlet
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(user_in_Json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

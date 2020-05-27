package com.lunch.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.TransactionDaoImpl;
import log.Log;
import user.cust.account.models.User;

@WebServlet("/viewcustomertrans")
public class EmpViewCustTrans_Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EmpViewCustTrans_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\nEmpViewCustTrans_Servlet is running.......");
		
		
		
		// ------------------------------------------------------------------
		BankDaoImpl b = new BankDaoImpl();
		//TransactionDaoImpl tDao = new TransactionDaoImpl();
		
		List<User> allUsers = new ArrayList<>();
		// getAllUsers_needingAuth()
		//allUsers = b.getAllUsers();
		allUsers = b.getAllUsers_withAuth();

		Log.logger("\n" + allUsers.size() + " Total Customer(s)");
		Log.logger("Listing... Customers:\n");


//		for (User u : allUsers) {
//			Log.logger("("+(allUsers.indexOf(u)+1)+")"  + u.toString());
//		}
		// ------------------------------------------------------------------		
		
		
		String user_in_Json = "[";
		
		// when i need id
		// ""+(usersNeedApproval.indexOf(u)+1)+""  +
		
		// THIS SUCKER WORKS
//		for (User u : usersNeedApproval) {
//			
//			user_in_Json += "{\"info\":\"" + u.getUserName() + "\"},";
//			
//		}
		
		System.out.println("here is .length(): " + allUsers.size());
		
		for (User u : allUsers) {
			
			System.out.println(allUsers.indexOf(u)+1);
			
			user_in_Json += 
					
					"{ \"index\" : \"" + (allUsers.indexOf(u)+1) + "\","
					+ " \"userName\" : \"" + u.getUserName() + "\","
					+ " \"user_id\" : \"" + u.getUser_id() + "\","
					+ " \"dob\" : \"" + u.getDob() + "\","
					+ " \"balance\" : \"" + u.getBalance() + "\","
					+ " \"a_access\" : \"" + u.getA_access() + "\" },";
					// " \"userName\" : \"" + u.getUserName() + "\","
		}
		
		
		
		
		user_in_Json = user_in_Json.substring(0, user_in_Json.length() - 1);
		
		user_in_Json = user_in_Json.concat("]");

		System.out.println(user_in_Json);
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(user_in_Json);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

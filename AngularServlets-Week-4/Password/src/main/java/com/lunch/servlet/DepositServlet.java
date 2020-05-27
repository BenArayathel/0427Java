package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.cust.account.controller.CustViewBal_Depos_Wthdr_Transf;
import user.cust.account.models.User;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    
    public DepositServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session=request.getSession(false);
		
		if(session == null) {
			//response.sendRedirect("/session_demo");
			System.out.println("\n\nhmmm.........sorry session was null \n\n");
		}else {
			
			User user=(User) session.getAttribute("user");
			System.out.println("Identifying session: User: " + user.toString());
			System.out.println("recorded deposit: " + request.getParameter("deposit"));
			
			CustViewBal_Depos_Wthdr_Transf c = new CustViewBal_Depos_Wthdr_Transf();
			//c.deposit(user, Double.parseDouble(request.getParameter("deposit")));
			
			if (c.deposit(user, Double.parseDouble(request.getParameter("deposit"))) == 1) {
				
				// reset session state
				
				System.out.println("user.getBalance() at Depost servlet\nThis should be updated\n" + user.getBalance());
				session.setAttribute("user", user);
				
				String user_in_Json =
					    "{ \"userName\" : \"" + user.getUserName() + "\","
					    + " \"contactPhone\" : \"" + user.getContactPhone() + "\","
					    + " \"password\" : \"" + user.getPassword() + "\","
					    + " \"user_id\" : \"" + user.getUser_id() + "\","
					    + " \"email\" : \"" + user.getEmail() + "\","
					    + " \"dob\" : \"" + user.getDob() + "\","
					    + " \"soc\" : \"" + user.getSoc() + "\","
					    + " \"balance\" : \"" + user.getBalance() + "\","
					    + " \"a_access\" : \"" + user.getA_access() + "\" }";
				
				session.setAttribute("myCleanString", user_in_Json);
				
				response.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
				
			} else if (c.deposit(user, Double.parseDouble(request.getParameter("deposit"))) == -1) {
				
				response.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");

			} else {
				
				System.out.println("Totally unexpected value from Controller");
				response.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
			}

			//response.sendRedirect("http://localhost:9999/Password/balance2.html");
			//response.sendRedirect("three.html");

		}
	}

}

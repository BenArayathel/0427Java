package com.lunch.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.cust.account.models.User;

@WebServlet("/balance2")
public class CustBalanceServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CustBalanceServlet2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession(false);
		
		if(session == null) {
			//response.sendRedirect("/session_demo");
			System.out.println("\n\nhmmm.........sorry session was null \n\n");
		}else {
			
			User user=(User) session.getAttribute("user");
			System.out.println("User's balance id: " + user.getBalance());
			
			PrintWriter out=response.getWriter();
//			out.print("document.getElementById(\"balance\").value =" + user.getBalance());
			//out.print("<h3>" + user.getBalance() + "</h3>");
			response.getWriter().write(user.getBalance().toString());
			//response.sendRedirect("http://localhost:9999/Password/balance2.html");
			//response.sendRedirect("three.html");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

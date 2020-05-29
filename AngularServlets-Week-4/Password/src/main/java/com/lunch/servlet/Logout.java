package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public Logout() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\nLogout servlet ran");
		
        HttpSession session = request.getSession();

        // Invalidate the session and removes any attribute related to it
        session.invalidate();
		
		response.sendRedirect("http://localhost:9999/Password/userLogin.html");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}

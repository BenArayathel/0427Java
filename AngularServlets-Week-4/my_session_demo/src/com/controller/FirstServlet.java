package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;


@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     

    public FirstServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			User user = new User();
			user.setFname(request.getParameter("fname"));
			user.setLname(request.getParameter("lname"));
			user.setEmail(request.getParameter("email"));
			user.setContact(Long.parseLong(request.getParameter("contact")));
			
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("two.html");
		

	}

}

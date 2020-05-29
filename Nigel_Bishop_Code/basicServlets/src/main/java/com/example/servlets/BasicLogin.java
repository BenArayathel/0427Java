package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.users;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicLogin extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
			System.out.println("In doPost");

			res.setContentType("application/json");
			
			PrintWriter pw = res.getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			
			users user = mapper.readValue(req.getReader(), com.example.model.users.class);
			
			System.out.println(user);
		    System.out.println("username = " + user.getUserName());
		    System.out.println("password = " + user.getUserPassword());
		    
			String username = user.getUserName();
			String password = user.getUserPassword();
								
			if (username.equals("nigel") && (password.equals("password")))  
			{
				System.out.println("Login Success...");
				pw.write("http://localhost:9090/basicServlets/WelcomePage.html");	
//				res.sendRedirect("/WelcomePage.html");
//				pw.write("/welcomepage");
			} 
			else 
			{
				System.out.println("Login Fail...");
				pw.write("http://localhost:9090/basicServlets/FailLogin.html");
//				res.sendRedirect("http://localhost:9090/basicServlets/FailLogin.html");
			}
		
		
	}

}

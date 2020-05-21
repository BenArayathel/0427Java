package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// this line is the connection to my form, via the "action" attribute of the form
// in ben's approach this would be defined in the web.xml file
@WebServlet("/formServlet")
public class FormTest extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// read the form fields
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
		// do some processing?
		
		// instead, of all of the following, how to send to html page?
		
		
		// get res(ponse) writer
		PrintWriter writer = res.getWriter();
		
		// for class assignment, conditional:
		if (username.equals("employee") && (password.equals("employee")))  {
			// build HTML code
			String htmlResponse = "<html>";
			htmlResponse += "<h2>Your username is: " + username + "</h2>";
			htmlResponse += "Your password is: " + password + "</h2>";
			htmlResponse += "<form action=\"homeServlet\">";
			htmlResponse += "<input class=\"form-control\" type=\"submit\" method='get' action='homeServlet'>Go to Home Page</input>";
			htmlResponse += "</form>";
//			htmlResponse += "<a href='bootstrap.html'>Continue to Home Page</a>";
			htmlResponse += "</html>";
			
			// return a response to show what the user entered
			writer.println(htmlResponse);
			
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			//this calls the redirect, can i attach it to a button?
//			redis.forward(req, res);
		} else {
			// return a response to show that it didn't work
//			writer.println("Sorry, those credentials aren't correct.");
			// how could i do a back button?
			String htmlResponse = "<html>";
			htmlResponse += "<a href=\"form.html\">Return Home</a>";
			htmlResponse += "</html>";
			
			writer.println(htmlResponse);
		}
		
	}

}

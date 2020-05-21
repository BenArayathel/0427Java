package com.basic.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basic.models.Planet;

public class SessionServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Planet p = new Planet("Jupiter");
		Planet op = new Planet("Venus");
		
		// Session Code
		
		HttpSession session = req.getSession();
//		HttpSession session = req.getSession(false); // Will return null if no sessions exists
		
		session.setAttribute("planet", p);
		session.setAttribute("Other Planet", op);
 	}
}

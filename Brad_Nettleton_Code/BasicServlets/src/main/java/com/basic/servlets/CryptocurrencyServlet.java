package com.basic.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CryptocurrencyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside cryptoservlet doGet");
		RequestDispatcher view = req.getRequestDispatcher("/crypto.html");
		view.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside cryptoservlet doPost");

		
		
		RequestDispatcher view = req.getRequestDispatcher("/crypto.html");
		view.forward(req, res);
	}
	
}

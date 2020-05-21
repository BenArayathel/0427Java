package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	// Won't be using this, don't want password in URL
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Something happened");
		
		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now, inside of doGet</h1>");
		
		doPost(req,res);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside doPost of HelloServlet!");
		
		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now, inside of doPost</h1>");

	}
}


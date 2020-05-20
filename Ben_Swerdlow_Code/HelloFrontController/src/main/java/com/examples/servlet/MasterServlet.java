package com.examples.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.controller.RequestHelper;

public class MasterServlet extends HttpServlet {
	
	/**
	 * Will handle all requests starting with /api/*
	 */
	
	// client -> MasterServlet -> RequestHelper -> controllers -> service -> dao -> database
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.getRequestDispatcher("string").forward(request, response)// sends a string back so we know where to forward to
		request.getRequestDispatcher(RequestHelper.process(request,response)).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

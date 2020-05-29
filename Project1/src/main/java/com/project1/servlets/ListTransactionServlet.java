package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.exception.BusinessException;
import com.project1.service.impl.Project1ServiceImpl;

/**
 * Servlet implementation class ListTransactionServlet
 */
public class ListTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project1ServiceImpl p1s = new Project1ServiceImpl();
		PrintWriter pw = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<String> transactions = p1s.getTransactionLog();
			pw.write(mapper.writeValueAsString(transactions));
		} catch (BusinessException e) {
			System.out.println(e);
		}
	}

}

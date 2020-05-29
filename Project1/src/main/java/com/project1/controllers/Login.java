package com.project1.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.Project1Service;
import com.project1.service.impl.Project1ServiceImpl;

/**
 * Servlet implementation class Login
 */
public class Login {
       
	public static String login (HttpServletRequest request, HttpServletResponse response) throws IOException {
		Project1Service p1s = new Project1ServiceImpl();	
		ObjectMapper mapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		PrintWriter pw = response.getWriter();
		
		User u = null;
		String username = bodyObj.get("userName").textValue();
		String password = bodyObj.get("password").textValue();
		
		try {
			u = p1s.getUser(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			System.out.println(session.getAttribute("user"));
			if(u.isEmployee()) {
				return "/employee_page.html";
			} else {
				return "/customer_page.html";
			}
		} catch (BusinessException e) {
			return "/index.html";
		}
		
	}

	public static String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		System.out.println("Logging out");
		
		if (session == null) {
			return "/index.html";
		} else {
			session.invalidate();
			return "/index.html";
		}
	}

}

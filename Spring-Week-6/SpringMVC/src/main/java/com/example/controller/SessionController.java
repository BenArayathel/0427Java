package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionController {

	/*
	 * Spring can inject other objects into your controller methods if you need it to.
	 * For example: HttpSession, HttpServletRequest, HttpServletResponse, etc
	 */
	
	@GetMapping(value="/getName")
	public @ResponseBody String getLoggedInName(HttpSession session) {
		System.out.println("in the getName method");
		
		String name = (String) session.getAttribute("loggedName");
		System.out.println(name);
		
		String pass = (String) session.getAttribute("loggedPass");
		System.out.println(pass);
		
		System.out.println("\n\n\n");
		return "Success";
	}
	
	@GetMapping(value="/login")
	public @ResponseBody String login(HttpServletRequest req, @RequestParam("myName") String myName) {
		HttpSession session = req.getSession();
		System.out.println("in the login method");
		
		session.setAttribute("loggedName", myName);
		session.setAttribute("loggedPass", "superSecure");
		
		System.out.println("\n\n\n");
		return "Hola";
	}
	
	@GetMapping(value="/logout")
	public @ResponseBody String logout(HttpSession session) {
		System.out.println("in the logout method");
		session.invalidate();
		
		System.out.println("\n\n\n");
		return "logged out";
	}
}



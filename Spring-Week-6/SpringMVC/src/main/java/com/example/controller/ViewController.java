package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // We don't need a name for our controller
public class ViewController {

	@PostMapping(value="/nextpage")
	public String nextPage() {
		//FORWARD
		return "/oth/other.html";
		
		
		//REDIRECT
//		return "redirect: /SpringMVC/oth/other.html";
//		return "redirect: https://www.google.com/";
	}
}


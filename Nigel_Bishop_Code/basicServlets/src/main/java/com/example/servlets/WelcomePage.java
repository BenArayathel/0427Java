package com.example.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Planet;
import com.example.model.users;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WelcomePage extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
			System.out.println("In welcome doGet");
			res.setContentType("application/json");
			
			PrintWriter pw = res.getWriter();
			
			
//			Planet p1 = new Planet();
//			p1.setName("Earth");
//			p1.setMoonname("Tera");
//			p1.setNumberofmoon(2);
			
			List<Planet> planetList = new ArrayList<>();
			planetList.add(new Planet("Earth","Tera",2));
			planetList.add(new Planet("Mars","Owa",5));
			planetList.add(new Planet("Saturn","Omega",7));

			
			
			
			
			
			ObjectMapper mapper = new ObjectMapper();
			pw.write(mapper.writeValueAsString(planetList));
//			pw.write(mapper.writeValueAsString(p1));

	}
	
	
//	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
//	{
//			System.out.println("In welcome doPost");
//
////			res.setContentType("application/json");
//			res.setContentType("text/html");
//
//			PrintWriter pw = res.getWriter();
//			
//			
//			Planet p1 = new Planet();
//			p1.setName("Earth");
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			String p1JsonString = mapper.writeValueAsString(p1);
//			pw.write(p1JsonString);
//			
//			System.out.println(p1JsonString);
			
//			mapper.writeValue(
//				    new FileOutputStream("./planet.json"), p1);
			
			

//			Car car = new Car("yellow", "renault");
//			mapper.writeValue(new File("C:\\Revature\\planet.json"), p1);
			
////			pw.write(mapper.writeValue(
////				    new FileOutputStream("data/planet.json"), p1));
//			pw.write(mapper.writeValueAsString(p1));
//			pw.write(mapper.writeValueAsString(p1.getName()));

//			String p1JsonString = mapper.writeValueAsString(p1);
//			System.out.println("Completed");
			
			
//			ObjectMapper objectMapper = new ObjectMapper();
//
//			Car car = new Car();
//			car.brand = "BMW";
//			car.doors = 4;
//
//			objectMapper.writeValue(
//			    new FileOutputStream("data/output-2.json"), car);


//			JsonNode carJsonNode = mapper.valueToTree(p1);
//			res.sendRedirect(carJsonNode);
//			pw.write(carJsonNode);
//			System.out.println(carJsonNode);
//			JsonNode body = ;
			
//			Planet plant = mapper.readValue(req.getReader(), com.example.model.Planet.class);

	

		
		
//	}

}

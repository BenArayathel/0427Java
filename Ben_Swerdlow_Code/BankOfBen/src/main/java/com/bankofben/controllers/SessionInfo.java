package com.bankofben.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankofben.models.TempUser;
import com.bankofben.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SessionInfo {

	public static String getTempUser(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String respString = "/home.html";
		ObjectMapper objMapper = new ObjectMapper();
		HttpSession session = request.getSession(false);
		if (session!=null) {
			System.out.println("Session Exists!");
			Enumeration<String> attributeNames = request.getSession().getAttributeNames();
			Boolean hasTempUser = null;
			Boolean hasOtherAttributes = null;
			System.out.println("Checking if only tempUser exists");
			while (attributeNames.hasMoreElements() &&
					(hasOtherAttributes==null || !hasOtherAttributes)) {
				String attributeName = (String) attributeNames.nextElement();
				System.out.println(attributeName);
				if (attributeName.equals("tempUser")) {
					hasTempUser = true;
				} else if (!attributeName.equals("tempUser")) {
					hasOtherAttributes = true;
				}
			}
			if (hasOtherAttributes==null && hasTempUser) {
				hasOtherAttributes = false;
			} else if (hasOtherAttributes && hasTempUser==null) {
				hasTempUser = false;
			}
			System.out.println("Has tempUser attribute: "+hasTempUser);
			System.out.println("Has other attributes besides tempUser: "+hasOtherAttributes);
			System.out.println("Has tempUser attribute only: "+ (hasTempUser && !hasOtherAttributes));
			if (!hasOtherAttributes) {
				// session.getAttribute("name") stores an Object, so need to downcast it
				TempUser tempUser = (TempUser) session.getAttribute("tempUser");
				respString = objMapper.writeValueAsString(tempUser);
			}
		}
		return respString;
	}

	public static String getFirstLastName(HttpServletRequest request, HttpServletResponse response) {
		String respString = "/home.html";
		HttpSession session = request.getSession(false);
		if (session!=null) {
			System.out.println("Session Exists!");
			Enumeration<String> attributeNames = session.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				String attributeName = (String) attributeNames.nextElement();
				System.out.println(attributeName);
				if (attributeName.equals("user") || attributeName.equals("customer") || attributeName.equals("employee")) {
					User user = (User) session.getAttribute(attributeName);
					respString = "{\"firstName\": \""+user.getFirstName()+"\", \"lastName\": \""+user.getLastName()+"\"}";
				}
			}
		}
		return respString;
	}

}

//package controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///*
// *	Controller that handles business logic for register.
// * */
//public class RegisterController {
//
//	public static String register(HttpServletRequest request, HttpServletResponse response) {
//
//		// Route guard, ensures we have appropriate HTTP method.
//		if (!request.getMethod().equals("POST")) {
//			return "/registerPortal.html";
//		}
//
//		return null;
//	}
//}
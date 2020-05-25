//package controller;
//
//import model.Account;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///*
// *	Controller that handles business logic for Login.
// * */
//public class LoginController {
//
//	public static String login(HttpServletRequest request, HttpServletResponse response) {
//
//		// Route guard, ensures we have appropriate HTTP method.
//		if (!request.getMethod().equals("POST")) {
//			return "/loginPortal.html";
//		}
//
//		// Tests login w/ hard-coded username and password.
//		Account account = new Account();
//		account.setUsername(request.getParameter("username"));
//		account.setPassword(request.getParameter("password"));
//		if (account.getUsername().equals("admintk") && account.getPassword().equals("#AWDawd1")) {
//			return "/customerPortal.html";
//		} else {
//			return "/testFail.html";
//		}
//	}
//}
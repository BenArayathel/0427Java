//package controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///*
// *	RequestHelper (helper class) that receives request/response from MasterServlet and simply routes to the appropriate controller.
// * 	Points the request to the right controller.
// * */
//public class RequestHelper {
//	public static String process(HttpServletRequest request, HttpServletResponse response) {
//
//		switch (request.getRequestURI()) {
//			case "/LoginServlet":
//				return LoginController.login(request, response);
//			case "/registerServlet":
//				return RegisterController.register(request, response);
//		}
//
//		return "index.html";
//	}
//}

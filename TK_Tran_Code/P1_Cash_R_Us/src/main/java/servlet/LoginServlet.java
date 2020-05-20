package servlet;

import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doPost() invoked.");

//		response.setContentType("application/json");
		response.setContentType("text/html");

		// TEST RequestDispatcher's forward() method
		Account account = new Account();
		account.setUsername(request.getParameter("username"));
		account.setPassword(request.getParameter("password"));
		if (account.getUsername().equals("admintk") && account.getPassword().equals("#AWDawd1")) {
			request.getRequestDispatcher("/testSuccess.html").forward(request, response);
		} else {
			System.out.println("Wrong username or password.");
			request.getRequestDispatcher("/testFail.html").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doGet() invoked.");

		request.getRequestDispatcher("/loginPortal.html").forward(request, response);
	}
}

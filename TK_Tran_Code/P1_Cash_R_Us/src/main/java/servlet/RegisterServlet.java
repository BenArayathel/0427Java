package servlet;

import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet: doPost() invoked.");

		response.setContentType("application/json");

		// read registerPortal's form data and do additional validation (optional).. [X]
		// assign fields to local Account object.. [X]
		// add Account obj to database..
		// create a session(?)..
		// route back to index.html; users can now log in.. [X]
		Account account = new Account();
		account.setUsername(request.getParameter("username"));
		account.setPassword(request.getParameter("password"));
		account.setName(request.getParameter("name"));
		account.setBalance(Double.parseDouble(request.getParameter("deposit")));
		account.setType("customer");
		// Print for testing purposes..
		System.out.println(account);

		response.sendRedirect("index.html");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet: getGet() invoked.");

		response.setContentType("application/json");
	}
}

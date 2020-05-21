package servlet;

import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doPost() invoked.");

//		response.setContentType("text/html");
		response.setContentType("application/json");

		Account account = new Account();
		account.setUsername(request.getParameter("username"));
		account.setPassword(request.getParameter("password"));
		if (account.getUsername().equals("admintk") && account.getPassword().equals("#AWDawd1")) {
			System.out.println("Login Success: " + account.getUsername() + ", " + account.getPassword() + ".");

			HttpSession session = request.getSession(); // creates a session after user logs in
			session.setAttribute("account", account); // stores user-related info as key/value pairs in the session

			response.sendRedirect("index.html"); // sends to another page

			// Prints for testing purposes..
			PrintWriter writer = response.getWriter(); // used to write directly to HTML page
			writer.println("<h1>Username: " + account.getUsername() + "</h1>");
			writer.println("Password: " + account.getPassword());
			writer.println("Name: " + account.getName());
			writer.println("Balance: " + account.getBalance());
			writer.println("Type: " + account.getType());
			writer.flush();
		} else {
			System.out.println("LoginServlet: Invalid Credentials.");

			response.sendRedirect("loginPortal.html"); // routes back to loginPortal if credentials are wrong
		}

		/*
		 * ObjectMapper reads and writes JSON from POJOs
		 * readValue() parses/deserializes JSON into an obj; writeValue() serializes an obj into JSON.
		 * getWriter provides which objs are serialized to JSON.
		 * writeValueAsString() returns generated JSON as a String.
		 */
//		ObjectMapper om = new ObjectMapper();
//		Account account = new Account();
//		account.setUsername(request.getParameter("username"));
//		account.setPassword(request.getParameter("password"));
//		account = om.readValue(request.getReader(), model.Account.class);
//		System.out.println(account);
//		response.getWriter().write(new ObjectMapper().writeValueAsString(account));

		// ------------------------------------------------------------------------------------------------------------

//		// SESSION EXAMPLE
//		// Only when login is successful do we create a session for the user!
//		// When user closes application, the session is automatically destroyed (or we can set a timer).
//		// In first.html page/servlet:
//		Account account1 = new Account();
//		HttpSession session1 = request.getSession(); // start an HttpSession object once user logs in; this makes our app stateful!
//		session1.setAttribute("account", account1); // setAttribute() store user-related info as key/value pairs
//		response.sendRedirect("second.html"); // redirect to another page
//
//		// In second.html page/servlet:
//		HttpSession session2 = request.getSession(false); // false means DON'T CREATE A NEW SESSION (we want to reuse the first session); returns null if no sessions exist
//		if (session2 == null) { // if there's no session..
//			response.sendRedirect("first.html"); // ..route back to beginning to create one..
//		} else { // ..else read more fields and add them to another obj
//			Account account2 = (Account) session2.getAttribute("account"); // getAttribute() returns the VALUE of the specified KEY
//			account2.setUsername(request.getParameter("username")); // read username field and assign to obj
//			session2.setAttribute("account", account2); // store in session as key/value pair again
//			response.sendRedirect("third.html"); // redirect to another page
//		}
//
//		// In third.html page/servlet:
//		HttpSession session3 = request.getSession(false); // again, don't create a new session but use the original session
//		if (session3 == null) {
//			response.sendRedirect("first.html");
//		} else {
//			Account account3 = (Account) session3.getAttribute("account");
//			account3.setPassword(request.getParameter("password")); // read password field and assign to obj
//			session3.setAttribute("account", account3);
//
//			// FINALLY, print things out
//			PrintWriter pw = response.getWriter();
//			pw.println("Username: " + account3.getUsername());
//			pw.println("Password: " + account3.getPassword());
//			pw.println("Session ID: " + session3.getId());
//			pw.println("Session created on: " + new Date(session3.getCreationTime()));
//			pw.println("Session last accessed time: " + new Date(session3.getLastAccessedTime()));
//			pw.println("Session max inactive time: " + session3.getMaxInactiveInterval());
//			session3.invalidate(); // manually destroys the session
//		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doGet() invoked.");
		response.setContentType("application/json");
	}
}

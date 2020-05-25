package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOImp;
import model.Account;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/*
	read loginPortal's form data and do additional validation..
	pull a DB instance (by username) and assign to local Account obj..
	compare credentials of input against DB instance..
	if match, route to corresponding employee/customer page..
	if not, route back to home page..
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet - doPost()");
		response.setContentType("application/json");

		// Setup
		Service service = new Service();
		DAOImp dao = new DAOImp();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		// LOCAL ACCOUNT - Jackson ObjectMapper maps JS vars to Java obj
		Account localAccount = mapper.readValue(request.getReader(), model.Account.class);
		System.out.println("Local Account: " + localAccount);

		// Assigns localAccount vars for comparison
		String localUsername = localAccount.getUsername();
		String localPassword = localAccount.getPassword();

		// HERE WE GOOO: COMPARES CREDENTIALS OF LOCALACCOUNT AND DBACCOUNT
		try {
			// DATABASE ACCOUNT - Pulls a db account (by input username)
			Account dbAccount = dao.getAccount(localUsername);
			System.out.println("Database Account: " + dbAccount);

			// Assigns dbAccount vars for comparison
			String dbUsername = dbAccount.getUsername();
			String dbPassword = dbAccount.getPassword();

			if (dbUsername.equals(localUsername) && dbPassword.equals(localPassword)) {
				if (dbAccount.getType().equals("admin")) {
					System.out.println("Employee successfully logged in: " + dbAccount + ".");

					// Session
					HttpSession session = request.getSession();
					session.setAttribute("account", dbAccount);

					// Routing
					writer.write("employeePortal.html");
				} else {
					System.out.println("Customer successfully logged in: " + dbAccount + ".");

					// Session
					HttpSession session = request.getSession();
					session.setAttribute("account", dbAccount);

					// Routing
					writer.write("customerPortal.html");
				}
			} else {
				System.out.println("Credentials DO NOT match DB!");

				// Routing
				writer.write("index.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet - doGet()");
		response.setContentType("application/json");
	}
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
//		account = om.readValue(request.getReader(), model.Account.class); // maps account obj based on Account class' modeling schema
//		System.out.println(account);
//		response.getWriter().write(new ObjectMapper().writeValueAsString(account));
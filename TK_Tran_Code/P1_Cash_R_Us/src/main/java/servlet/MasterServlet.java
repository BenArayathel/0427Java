package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
	 MasterServlet acts as a gateway for routing to controllers.
	 Receives request/response from client (HTML) and forwards to RequestHelper (helper class) which routes to the appropriate controller (Servlet).
 */
@WebServlet("/MasterServlet")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In MasterServlet - doPost() invoked.");
		response.setContentType("application/json");

		// Routes to RequestHelper which routes to appropriate controller
//		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request, response);

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
		System.out.println("In MasterServlet - doGet() invoked.");
		response.setContentType("application/json");
	}
}

/*
 * Presentation layer <-> Servlets <-> Service Layer <-> DAO Layer <-> Database
 *
 *	PrintWriter object used to DIRECTLY respond to client.
 *
 *  Servlet Hierarchy
 * 		Servlet(I) -> GenericServlet(AC) -> HttpServlet(C) -> CustomServlet(C)
 *
 *	Servlet Lifecycle
 * 		init() - initializes servlet in the servlet container (web container).
 * 			Called once: either on first request (default), or on startup.
 * 		service() - handles request to this servlet; basically forwards req to appropriate method (doGet, doPost, doDelete, etc..).
 * 			Note: this method is ABSTRACT in GenericServlet and then IMPLEMENTED in HttpServlet.
 * 			Called on every request to the servlet.
 * 		destroy() - destroys the servlet.
 * 			Called once when server container stops/restarts.
 *
 *  3 ways a servlet can respond to incoming requests:
 * 		Direct:
 * 			using PrintWriter obj to respond to the JS that's fetching.
 * 			happens when the servlet directly responds.
 * 		Forward:
 * 			1 req, 1 res, uses RequestDispatcher's .forward() method.
 * 			servlet asks another servlet/resource (internal to your server) to assist w/ the response.
 * 			client is unaware of the new resource/entity.
 * 		Redirect:
 *			2 req, 2 res, uses HttpServletResponse obj's .sendRedirect() method.
 * 			servlet informs client that it must go somewhere else to have the request handled.
 * 			can be linked to an external resource.
 * 			client is aware of the new resource/entity.
 */
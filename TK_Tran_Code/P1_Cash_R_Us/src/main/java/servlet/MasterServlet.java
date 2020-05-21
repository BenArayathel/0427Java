package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *	MasterServlet acts like a gateway for routing to controllers.
 *	Receives request/response from client (HTML) and sends to RequestHelper to route to appropriate controller.
 * */
@WebServlet("/masterServlet")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MasterServlet: doPost() invoked.");

		response.setContentType("application/json");

		// Routes to RequestHelper which routes to appropriate controller
//		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MasterServlet: doGet() invoked.");
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
 * 			using PrintWriter obj.
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
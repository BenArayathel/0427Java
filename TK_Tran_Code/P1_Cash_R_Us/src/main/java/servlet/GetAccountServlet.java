package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOImp;
import exception.BusinessException;
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

@WebServlet("/GetAccountServlet")
public class GetAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// NOT USED
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAccountServlet - doGet()");
		response.setContentType("application/json");

		// Setup
		Service service = new Service();
		DAOImp dao = new DAOImp();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		// Accesses user via their SESSION
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.html");
		} else {
			Account localAccount = (Account) session.getAttribute("account");
			System.out.println("Session Account: " + localAccount);

			// Username used to query DB
			String localUsername = localAccount.getUsername();

			try {
				// Queries DB and populates a local account
				Account dbAccount = dao.getAccount(localUsername);
				System.out.println("DB Account: " + dbAccount);

				// Returns the local account as JSON for JS to populate HTML
				writer.write(mapper.writeValueAsString(dbAccount));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}
}

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

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DepositServlet - doPost()");
		response.setContentType("application/json");

		// Setup
		Service service = new Service();
		DAOImp dao = new DAOImp();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		// Maps JS values to a localAccount
		Account localAccount = mapper.readValue(request.getReader(), model.Account.class);
		System.out.println("Local Account: " + localAccount);

		// Extracts localAccount's balance (really just the deposit amount) for maths
		double depositAmount = localAccount.getBalance();

		// Accesses user via their SESSION
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.html");
		} else {
			Account sessionAccount = (Account) session.getAttribute("account");
			System.out.println("Session Account: " + sessionAccount);
			String sessionUsername = sessionAccount.getUsername();
			try {
				// Uses session's username as argument to query DB and assign a local dbAccount
				Account dbAccount = dao.getAccount(sessionUsername);

				// Extracts dbAccount's balance for maths
				double dbBalance = dbAccount.getBalance();

				// HERE WE GOOO: DO MATHS AND UPDATE DBACCOUNT
				dbBalance += depositAmount;
				dbAccount.setBalance(dbBalance);
				dao.updateAccount(dbAccount);
				System.out.println("dbAccount after deposit: " + dbAccount);

				// Routing
				writer.write("customerPortal.html");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// NOT USED
	}
}

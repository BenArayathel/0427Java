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
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TransferServlet - doPost()");
		response.setContentType("application/json");

		// Setup
		Service service = new Service();
		DAOImp dao = new DAOImp();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		// Maps JS values to a localAccount
		Account localAccount = mapper.readValue(request.getReader(), model.Account.class);
		System.out.println("Local Account: " + localAccount);

		// Extracts localAccount's username (really just the payee)
		String payee = localAccount.getUsername();
		// Extracts localAccount's balance (really just the transfer amount) for maths
		double transferAmount = localAccount.getBalance();

		// Accesses user via their SESSION
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.html");
		} else {
			Account sessionAccount = (Account) session.getAttribute("account");
			System.out.println("Session Account: " + sessionAccount);
			String sessionUsername = sessionAccount.getUsername();
			try {
				// Uses session's username as argument to query DB and assign a local payerAccount
				Account payerAccount = dao.getAccount(sessionUsername); // PAYER
				// Extracts payerAccount's balance for maths
				double payerBalance = payerAccount.getBalance();

				// Extracts payeeAccount
				Account payeeAccount = dao.getAccount(payee);
				// Extracts payeeAccount's balance for maths
				double payeeBalance = payeeAccount.getBalance();


				// HERE WE GOOO: DO MATHS AND UPDATE PAYER AND PAYEE ACCOUNTS
				if ((payerBalance - transferAmount) < 0) {
					JOptionPane.showMessageDialog(null, "Cannot withdraw more than $" + payerBalance + "!");
					writer.write("userWithdraw.html");
				} else {
					// DO MATHS
					payerBalance -= transferAmount; // MINUS TRANSFER AMOUNT
					payeeBalance += transferAmount; // ADD TRANSFER AMOUNT

					// SET NEW BALANCE
					payerAccount.setBalance(payerBalance);
					payeeAccount.setBalance(payeeBalance);

					// UPDATE RESPECTIVE ACCOUNTS
					dao.updateAccount(payerAccount);
					dao.updateAccount(payeeAccount);
					System.out.println("Payer account after transfer: " + payerAccount);
					System.out.println("Payee account after transfer: " + payeeAccount);

					// Routing
					writer.write("customerPortal.html");
				}
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

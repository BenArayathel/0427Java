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
import java.io.IOException;
import java.io.PrintWriter;

/*
	read registerPortal's form data and do additional validation..
	assign fields to local Account object..
	validate whether Account already exists in database..
	add Account to db if it doesn't exist..
	route back to index.html; users can now log in.. (loginServlet will handle session creation)
 */

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In RegisterServlet - doPost()");
		response.setContentType("application/json");

		// Setup
		Service service = new Service();
		DAOImp dao = new DAOImp();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		// LOCAL ACCOUNT - Jackson ObjectMapper maps JS vars to Java obj
		Account localAccount = mapper.readValue(request.getReader(), model.Account.class);
		System.out.println("Local Account: " + localAccount);

		// Assigns JS vars to Java vars
		String usernameInput = localAccount.getUsername();
		String passwordInput = localAccount.getPassword();
		String nameInput = localAccount.getName();
		double balanceInput = localAccount.getBalance();
		String typeInput = localAccount.getType();

		// HERE WE GOOO: CONSTRUCTS THE LOCALACCOUNT TO CREATE A DBACCOUNT
		try {
			// Constructs a local account
			localAccount.setUsername(usernameInput);
			localAccount.setPassword(passwordInput);
			localAccount.setName(nameInput);
			localAccount.setBalance(balanceInput);
			localAccount.setType(typeInput);

			// Adds that account to the DB
			dao.createAccount(localAccount);

			// SANITY CHECK: Pulls db back here and print
			Account dbAccount = dao.getAccount(usernameInput);
			System.out.println("Database Account: " + dbAccount + ".");

			// Routing
			writer.write("index.html");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In RegisterServlet - getGet()");

		response.setContentType("application/json");
	}
}

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
import java.util.List;

@WebServlet("/GetAllAccountsServlet")
public class GetAllAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// NOT USED
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllAccountsServlet - doGet()");
		response.setContentType("application/json");

		// Setup
		Service service = new Service();
		DAOImp dao = new DAOImp();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		try {
			// Queries DB and populates a local accountsList list
			List<Account> accountList = dao.getAllAccounts();
			System.out.println("All DB Accounts: " + accountList);

			// Returns the local accountsList list as JSON for JS to populate HTML
			writer.write(mapper.writeValueAsString(accountList));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}

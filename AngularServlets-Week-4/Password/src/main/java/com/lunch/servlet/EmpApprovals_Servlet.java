package com.lunch.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
import user.cust.account.models.User;

@WebServlet("/empapproval")
public class EmpApprovals_Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public EmpApprovals_Servlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("\nEmpApprovals_Servlet is running.......");

		
		BankDaoImpl b = new BankDaoImpl();
		List<User> usersNeedApproval = new ArrayList<>();
		

		usersNeedApproval = b.getAllUsers_needingAuth();

//		Log.logger(usersNeedApproval.size() + " Customer(s) Need Approval");
//		Log.logger("Select -which- Customer to Approve:\n");
		
		List<String> u_to_str = new ArrayList<>();
		
		String user_in_Json = "[";
		
		// when i need id
		// ""+(usersNeedApproval.indexOf(u)+1)+""  +
		
		// THIS SUCKER WORKS
//		for (User u : usersNeedApproval) {
//			
//			user_in_Json += "{\"info\":\"" + u.getUserName() + "\"},";
//			
//		}
		
		System.out.println("here is .length(): " + usersNeedApproval.size());
		
		for (User u : usersNeedApproval) {
			
			System.out.println(usersNeedApproval.indexOf(u)+1);
			
			user_in_Json += 
					
					"{ \"index\" : \"" + (usersNeedApproval.indexOf(u)+1) + "\","
					+ " \"userName\" : \"" + u.getUserName() + "\","
					+ " \"user_id\" : \"" + u.getUser_id() + "\","
					+ " \"dob\" : \"" + u.getDob() + "\","
					+ " \"balance\" : \"" + u.getBalance() + "\","
					+ " \"a_access\" : \"" + u.getA_access() + "\" },";
					// " \"userName\" : \"" + u.getUserName() + "\","
		}
		
		
		
		
		user_in_Json = user_in_Json.substring(0, user_in_Json.length() - 1);
		
		user_in_Json = user_in_Json.concat("]");

		System.out.println(user_in_Json);
//		for (User u : usersNeedApproval) {
//			Log.logger("("+(usersNeedApproval.indexOf(u)+1)+")"  + u.toString());
//		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(user_in_Json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\nEmpApprovals_Servlet doPost running....");
		// receive the id to be deleted or approved
		
		/**
		 * const data3 = {
		        user_id: '',
		        action: 'approve'
		      }
		      
		      utility
		      user_id = delete or approve ?
		 */
		
		BankDaoImpl b = new BankDaoImpl();
		ObjectMapper mapper2 = new ObjectMapper();
		// only once : req.getReader()
		User user = mapper2.readValue(request.getReader(), user.cust.account.models.User.class);
		
		System.out.println("\nthis is user.getUser_id(): " + user.getUser_id());
		System.out.println("this is user.getUtility(): " + user.getUtility());
		
		if (user.getUtility().equals("approve")) {
			
			System.out.println("Approval running in EmpApprovals_Servlet doPost");
			System.out.println("REMEMBER TO UNCOMMENT CODE FOR FUNCTIONALITY");
			//b.employeeApprove_customerApplicationForAccount(user);
			
		} else if (user.getUtility().equals("delete")) {
			
			System.out.println("Rejection running in EmpApprovals_Servlet doPost");
			System.out.println("REMEMBER TO UNCOMMENT CODE FOR FUNCTIONALITY");
			//b.employeeReject_customerApplicationForAccount(user);

		} else {
			
			System.out.println("\n\nUNEXPECTED VALUE in EmpApprovals_Servlet doPost");
		}
		
		
	}

}








































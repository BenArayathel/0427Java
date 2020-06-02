package com.pone.v1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pone.v1.controllers.RequestHelper;

@WebServlet("/api/*")
public class MasterServlet extends HttpServlet{

	private static final long serialVersionUID = 5954371846506258378L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try(Connection conn = SingletonDBConnection.getConnection()){
//			String sql = "Select * from hb_user";
//			PreparedStatement prepared = conn.prepareStatement(sql);
//			ResultSet result = prepared.executeQuery();
//			while(result.next()) {
//				System.out.println(result.getString(1));
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		RequestHelper.process(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestHelper.process(request, response);
//		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestHelper.process(request, response);
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestHelper.process(request, response);
		// TODO Auto-generated method stub
	}
	
}

package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet() invoked.");

		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now inside doGet()</h1>");

		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost() invoked.");

		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now inside doPost()</h1>");
	}
}

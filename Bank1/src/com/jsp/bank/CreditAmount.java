package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreditAmount")
public class CreditAmount extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tamount=req.getParameter("amount");
		double amount=Double.parseDouble(tamount);
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session=req.getSession();
		Double damount=(Double) session.getAttribute("damount");
		String mb=(String) session.getAttribute("mb");
		String password=(String) session.getAttribute("password");
		if(damount!=null) {
		if(amount>0) {
			double add=damount+amount;
			String update="update bank set amount=? where mobilenumber=? and password=?";
			String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection(url);
				PreparedStatement ps=connection.prepareStatement(update);
				ps.setDouble(1,add);
				ps.setString(2,mb);
				ps.setString(3, password);
				int num=ps.executeUpdate();
				if(num>0) {
					RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
					rd.include(req, resp);
					writer.println("<center><h1>Amount Credited</h1></center>");
				}
				else {
					RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
					rd.include(req, resp);
					writer.println("<center><h1>404 error</h1></center>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		else {
			RequestDispatcher rd=req.getRequestDispatcher("CreditAmount.html");
			rd.include(req,resp);
			writer.println("<center><h1>Invalid amount</h1></center>");
		}
		}
		else {
			RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
			rd.include(req,resp);
			writer.println("<center><h1>Session time out</h1></center>");
		}
	}
}



package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ReceiverMobileNumber")
public class ReceiverMobileNumber extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rmb=req.getParameter("rmb");
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session=req.getSession();
		session.setAttribute("rmb",rmb);
		System.out.println(rmb);
		String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		String select="select * from bank where mobilenumber=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1,rmb);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				double rdamount=rs.getDouble(3);
				System.out.println(rdamount);
				session.setAttribute("rdamount",rdamount);
				session.setAttribute("rmb", rmb);
				RequestDispatcher rd=req.getRequestDispatcher("ReceiverAmount.html");
				rd.forward(req, resp);
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("ReceiverMobileNumber.html");
				rd.include(req, resp);
				writer.println("<center><h1>Invalid Mobile NUmber</h1></center>");
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

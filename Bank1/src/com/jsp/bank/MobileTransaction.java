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
@WebServlet("/MobileTransaction")
public class MobileTransaction extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mb=req.getParameter("mb");
		String password=req.getParameter("password");
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session=req.getSession();
		
				String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
				String select="select * from bank where mobilenumber=? and password=?";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection=DriverManager.getConnection(url);
					PreparedStatement ps=connection.prepareStatement(select);
					ps.setString(1,mb);
					ps.setString(2,password);
					ResultSet rs=ps.executeQuery();
					//resp.setContentType("text/html");
					//HttpSession session=req.getSession();
					if(rs.next()) {
						double sdamount=rs.getDouble(3);
						System.out.println(sdamount);
						session.setAttribute("sdamount",sdamount);
						session.setAttribute("mb",mb);
						System.out.println(mb);
						RequestDispatcher rd=req.getRequestDispatcher("ReceiverMobileNumber.html");
						rd.forward(req, resp);
						
					}
					else {
						RequestDispatcher rd=req.getRequestDispatcher("MobileTransaction.html");
						rd.include(req, resp);
						writer.println("<center.<h1>Invalid Details</h1></center>");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}

package org.jsp.httpservlets;

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

@WebServlet("/EmailidValidation")
public class EmailidValidation extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail=req.getParameter("mail");
		System.out.println(mail);
		HttpSession session=req.getSession();
		String abc="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		//String select="select*from userinformation where emailid=?;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(abc);
			PreparedStatement ps=connection.prepareStatement("select * from userinformation where emailid=?");
			ps.setString(1, mail);
			ResultSet rs=ps.executeQuery();
			PrintWriter writer=resp.getWriter();
			resp.setContentType("text/html");
			if(rs.next()) {
				session.setAttribute("emailid", mail);
				session.setMaxInactiveInterval(10);
				RequestDispatcher rd=req.getRequestDispatcher("Password.html");
				rd.include(req, resp);
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("emailid.html");
				rd.include(req, resp);
				writer.println("<center><h2>Invalid EMAIL ID</h2></center>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}


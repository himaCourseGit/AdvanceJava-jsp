package org.jsp.httpservlets;

import java.io.IOException;
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
@WebServlet("/Login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail=req.getParameter("mail");
		String password=req.getParameter("password");
		HttpSession session=req.getSession();
		try {
		    Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/teca41?user=root&password=12345");
			PreparedStatement ps=connection.prepareStatement("insert into form (email,password) values(?,?");
			ps.setString(1,mail);
			ps.setString(2,password);
			int n=ps.executeUpdate();
			if(n!=0)
			{
				session.setAttribute("mail", mail);
				session.setAttribute("password", password);
				session.setMaxInactiveInterval(20);
				RequestDispatcher rd=req.getRequestDispatcher("registration.html");
				rd.include(req, resp);
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("Login.html");
				rd.include(req, resp);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

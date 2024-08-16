package org.jsp.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/ForgotPassword")
public class ForgotPassword extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail=req.getParameter("mail");
		String password=req.getParameter("password");
		String cpassword=req.getParameter("cpassword");
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		if(password.equals(cpassword)) {
			String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
			String update="update userinformation set password=? where emailid=?";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection(url);
				PreparedStatement ps=connection.prepareStatement(update);
				ps.setString(1, password);
				ps.setString(2, mail);
				int num=ps.executeUpdate();
				if(num!=0) {
					RequestDispatcher rs=req.getRequestDispatcher("Login.html");
					rs.include(req, resp);
				}else {
					RequestDispatcher rs=req.getRequestDispatcher("ForgotPassword.html");
					rs.include(req,resp);
					writer.println("<center><h1>Invalid details</h1></center>");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else {
				RequestDispatcher rs=req.getRequestDispatcher("ForgotPassword.html");
				rs.include(req, resp);
				writer.println("<center></h1>Invalid ConformPassword</h1></center>");


	}
}
}

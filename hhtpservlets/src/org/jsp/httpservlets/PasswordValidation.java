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
@WebServlet("/PasswordValidation")
public class PasswordValidation extends HttpServlet {

	
		@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String password=req.getParameter("password");
		System.out.println(password);
		HttpSession session=req.getSession();
		String mail=(String)session.getAttribute("emailid");
		System.out.println(mail);
		//System.out.println(mail);
		PrintWriter writer=resp.getWriter();
		
		if(mail!=null) {
			
			String abc="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
			String select="select * from userinformation where emailid=? and password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(abc);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, mail);
			ps.setString(2, password);
		    ResultSet rs=ps.executeQuery();
		    resp.setContentType("text/html");
		 
			if(rs.next()) {
				//RequestDispatcher rd=req.getRequestDispatcher("emailid.html");
				//rd.include(req, resp);
			writer.println("<center></h1>Login successfull</h1></center>");
			
		} 
		else {
			RequestDispatcher rd=req.getRequestDispatcher("Password.html");
			rd.include(req, resp);
			writer.println("<center><h1>Invalid password</h1></center>");
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else {
			RequestDispatcher rd=req.getRequestDispatcher("emailid.html");
			rd.include(req, resp);
			writer.println("<center><h1>Session time out</h1></center>");
		}
		}
}

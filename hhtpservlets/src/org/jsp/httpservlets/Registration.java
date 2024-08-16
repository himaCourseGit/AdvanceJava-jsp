package org.jsp.httpservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Registration")
public class Registration extends HttpServlet{
private Connection connection;

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String name=req.getParameter("name");
	String age=req.getParameter("age");
	String mb=req.getParameter("mobileno");
	String course=req.getParameter("course");
	HttpSession session=req.getSession();
	String mail=(String) session.getAttribute("mail");
	String password=(String) session.getAttribute("password");
	PrintWriter writer=resp.getWriter();
	resp.setContentType("text/html");
	System.out.println(mail);
	try {
		Class.forName("com.mysql.jdbc.Driver");
		 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/teca41?user=root&password=12345");
		PreparedStatement ps1=connection.prepareStatement("update form set name=?,age=?,mobilenumber=?,course=? where emauil=? and password=?");
		ps1.setString(1, name);
		ps1.setString(2,age);
		ps1.setString(3, mb);
		ps1.setString(4, course);
		ps1.setString(5,mail);
		ps1.setString(6, password);
		int n=ps1.executeUpdate();
		System.out.println(n);
		if(n!=0)
		{
			writer.println("<center><h1>Registration Successfull</h1></center>");
			
		}
		else {
			writer.println("<center><h1>Registration UnSuccessfull!</h1></center>");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}

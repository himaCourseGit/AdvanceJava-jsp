package org.jsp.jdbc;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Registrationform")
public class Registrationform extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		System.out.println(name);
		String mb = req.getParameter("mb");
		System.out.println();
		String mail = req.getParameter("mail");
		System.out.println(mail);
		String password = req.getParameter("password");
		System.out.println(password);
		String gender = req.getParameter("gender");
		System.out.println(gender);
		String course = req.getParameter("course");
		System.out.println(course);
		String terms=req.getParameter("check");
		System.out.println(terms);

		String url = "jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		String insert = "insert into userinformation(name, mobilenumber, emailid, password, gender, course)"
				+ "values(?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setString(1, name);
			ps.setString(2, mb);
			ps.setString(3, mail);
			ps.setString(4, password);
			ps.setString(5, gender);
			ps.setString(6, course);

			PrintWriter writer = resp.getWriter();
			resp.setContentType("text/html");
			if (terms != null) {
				int num = ps.executeUpdate();
				if (num != 0) {
					RequestDispatcher rd = req.getRequestDispatcher("Login.html");
					rd.include(req, resp);
					// writer.println("<center><h1>Registration successfull!!!</h1></center>");
				} else {
					RequestDispatcher rd = req.getRequestDispatcher("Registrationform.html");
					rd.include(req, resp);
					writer.println("<center><h1>No data Inserted</h1></center>");
				}
			}

			else {
				RequestDispatcher rd = req.getRequestDispatcher("Registrationform.html");
				rd.include(req, resp);
				writer.println("<center><h1>Accept the terms and conditions</h1></center>");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

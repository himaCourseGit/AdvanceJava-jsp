package org.jsp.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Login")
	public class Login extends GenericServlet{

		  @Override
		  public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		    String mail=req.getParameter("id");
		    String password=req.getParameter("password");
		    
		    
		    String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";

		    String select1="select * from userinformation where emailid=?";
		    String select2="select * from userinformation where password=?";
		    String select="select * from userinformation where emailid=? and password=?";
		    
		     try {
		    	    Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(url);
					PreparedStatement ps = connection.prepareStatement(select1);
					PreparedStatement ps1 = connection.prepareStatement(select2);
					PreparedStatement ps2 = connection.prepareStatement(select);
					
					
					ps.setString(1, mail);
					ps1.setString(1,password);
					
					ps2.setString(1, mail);
					ps2.setString(2,password);
					
					ResultSet rs = ps.executeQuery();
					ResultSet rs1 = ps1.executeQuery();
					ResultSet rs2 = ps2.executeQuery();
					PrintWriter writer=resp.getWriter();
				    resp.setContentType("text/html");
					
					if(rs.next()) {
						if(rs1.next()) {
							
							if(rs2.next()) {
								writer.println("<center><h1>Login successfully</center></h1>");
							}
						else {
							 writer.println("<center><h1>Invalid email and password</center></h1>");
						}
					}
						else {
					         writer.println("<center><h1>Invalid password</center></h1>");
						}
					}
					else {
				         writer.println("<center><h1>Invalid emailId</center></h1>");
					}
				}
						 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         }
	}
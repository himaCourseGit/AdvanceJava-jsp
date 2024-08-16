package org.jsp.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tid=req.getParameter("id");
		int id=Integer.parseInt(tid);
		String name=req.getParameter("name");
		String salary=req.getParameter("salary");
		String deptno=req.getParameter("deptno");
		int dept =Integer.parseInt(deptno);
		System.out.println("Id of the employee:"+id);
		System.out.println("Name of the employee:"+name);
		System.out.println("Salary of the Employee:"+salary);
		System.out.println("Deptno of the Employee:"+deptno);
		//Responds from Server
		PrintWriter write=resp.getWriter();
		resp.setContentType("text/html");
		write.println("<center><h1 style=color:green>ID:"+id+"</h1></center>");
		write.println("<center><h1 style=color:orange>Name:"+name+"</h1></center>");
		write.println("<center><h1 style=color:blue>Salary:"+salary+"</h1></center>");
		write.println("<center><h1 style=color:red>Deptno:"+deptno+"</h1></center>");
		String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		String insert="insert into employee1 values(?,?,?,?)";
		try {
			Connection connect=DriverManager.getConnection(url);
			PreparedStatement ps=connect.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, salary);
			ps.setString(4, deptno);
			int n=ps.executeUpdate();
			if(n!=0)
			{
				write.println("<center><h1>Data Inserted</center></h1>");
			}
			else {
				write.println("<center><h1>NO Data Inserted</center></h1>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

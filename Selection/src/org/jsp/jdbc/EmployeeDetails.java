package org.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EmployeeDetails {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306?user=root&password=12345";
	String select="select * from teca41.employee where empdeptno=20";
	try {
		Connection connection=DriverManager.getConnection(url);
		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery(select);
		if(rs.last()) {
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("id of the employee:"+rs.getInt(1));
				System.out.println("name of the employee:"+rs.getString(2));
				System.out.println("Salary of the employee:"+rs.getDouble(3));
				System.out.println("deptno of the employee:"+rs.getInt(4));
				System.out.println("*=*=*=*");
			}
		}
	}
			catch(SQLException e) {
				e.printStackTrace();
			}
	
}
}

package org.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String update="update teca41.employee set empsalary=90000 where empdeptno=40";
		try {
			Connection connection=DriverManager.getConnection(url);
			Statement st=connection.createStatement();
			int num=st.executeUpdate(update);
	if(num>0)
		{
			System.out.println("executed"+num);
			System.out.println("updated");	
		}
	else {
		System.out.println("not executed"+num);
		System.out.println("not updated");
	}
}
catch(SQLException e)
{
	e.printStackTrace();
}
}
}

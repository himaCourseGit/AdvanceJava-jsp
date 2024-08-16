package org.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EstablishingConnection {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306?user=root&password=12345";
	String insert="insert into teca41.employee values(20,'bindu',60000,40)";
	
	
	try {
		Connection connection=DriverManager.getConnection(url);
		System.out.println("connection established");
		Statement st=connection.createStatement();
		System.out.println("platform created");
		int num=st.executeUpdate(insert);
		if(num!=0) {
			System.out.println(num);
			System.out.println("Data inserted");
		}
		else {
			System.out.println("data not inserted");
		}
	}
	catch(SQLException e) {
		//to do auto-generated catch block
		e.printStackTrace();	 
	}
}
}

package org.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SignIn {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
	String insert="insert into bank(name, mobilenumber, password)values(?,?,?)";
	try {
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement ps=connection.prepareStatement(insert);
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Name");
		String name=sc.next();
		System.out.println("Enter your mobilenumber");
		String mb=sc.next();
		System.out.println("Enter your password");
		String password=sc.next();
		ps.setString(1,name);
		ps.setString(2, mb);
	ps.setString(3,password);
		int num=ps.executeUpdate();
		if(num!=0) {
			System.out.println("your account opened successfully...!in teca41 Bank");
		}
		else {
			System.out.println("Enter valid details");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

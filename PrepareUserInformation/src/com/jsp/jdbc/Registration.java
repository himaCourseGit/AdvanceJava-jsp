package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
	String insert=
			"Insert into userinformation(name,mobilenumber,emailid,password) values(?,?,?,?)";
	try {
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement ps=connection.prepareStatement(insert);
		//take run time values
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Name:");
		String name=sc.next();
		System.out.println("Enter your mobilenumber:");
		String mb=sc.next();
		System.out.println("enter your emailid:");
		String emailid=sc.next();
		System.out.println("enter your password:");
		String password=sc.next();
		//set the values to the placeholder
		ps.setString(1, name);
		ps.setString(2, mb);
		ps.setString(3, emailid);
		ps.setString(4, password);
		int num=ps.executeUpdate();
		if(num!=0) {
			System.out.println("registrationsuccessfull");
		}
		else {
			System.out.println("enter valid details");
		}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

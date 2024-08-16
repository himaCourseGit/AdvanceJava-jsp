package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class checkingotp {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
	String select="select * from teca41.userinformation where emailid=? and password=?";
try {
	Connection connection=DriverManager.getConnection(url);
	PreparedStatement ps=connection.prepareStatement(select);
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the emailid:");
	String mail=sc.next();
	System.out.println("Enter the password:");
	String pass=sc.next();
	ps.setString(1, mail);
	ps.setString(2, pass);
	ResultSet rs=ps.executeQuery();
	if(rs.next())
	{
		Random r=new Random();
		int otp=r.nextInt(10000);
	    if(otp<1000) {
	    	otp=otp+1000;
	    }
	    System.out.println("your otp is:"+otp);
	    System.out.println("Enter the otp:");
	    int otp2=sc.nextInt();
	    if(otp2==otp)
	    {
	    	System.out.println("Login successfully.....");
	    }
	    else
	    {
	    	System.out.println("invalid data...");
	    }
	}
}
	catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
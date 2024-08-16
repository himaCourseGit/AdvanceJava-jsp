package org.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PalindromeEmployeeDetails {
public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
	String select="select * from employee";
	try {
	Connection connection=DriverManager.getConnection(url);
	Statement statement=connection.createStatement();
	ResultSet rs=statement.executeQuery(select);
	if(rs.last())
	{
		rs.beforeFirst();
		while(rs.next())
       {
         int id=rs.getInt(1);
         int temp=id;
           int rev=0;
          int last=0;
//203
          while(temp!=0)
          {
	      last=temp%10;
	      rev=rev*10+last;
	     temp/=10;
         }
        if(rev==id)
          {
	         System.out.println("Name of the employee:"+rs.getString(2));
	               System.out.println("Idof the employee:"+rs.getInt(1));
           }
	    } 
	}
	else
	{
		System.out.println("No data found");
	}
	}
      catch (SQLException e) 
      {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}
}
        

		
	

package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

interface Bank{
	void credit();
	void debit();
	void changePassword();
	void mobiletransaction();
	void checkbalance();	
}
public class BankDaoImplementation implements Bank{
	String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
	private Connection connection;
	private double sub;

	@Override
	public void credit() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your mobile number");
		String mb=sc.next();
		System.out.println("enter the password");
		String Password=sc.next();
		String select="select * from bank where mobilenumber=? and password=?";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, mb);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println("enter amount");
				double amount=sc.nextDouble();
				if(amount>0) {
					double damount=rs.getDouble(3);
					if(damount>=amount) {
						double sub=damount+amount;					
						String update="update bank set amount =? where mobilenumber=?";
						PreparedStatement ps1=connection.prepareStatement(update);
						ps1.setDouble(1,sub);
						ps1.setString(2,mb);
						int num=ps1.executeUpdate();
						if (num!=0) {
							System.out.println("Amount credited successfully!");
							
						}
						else {
							System.out.println("404 error");
						}
				}
						else {
							System.out.println("Invalid amount");
						}
			}
						else {
							System.out.println("invalid Details");
						}
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void debit() {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your mobile number");
		String mb=sc.next();
		System.out.println("enter the password");
		String Password=sc.next();
		String select="select * from bank where mobilenumber=? and password=?";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, mb);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println("enter amount");
				double amount=sc.nextDouble();
				if(amount>0) {
					double damount=rs.getDouble(3);
					if(damount>=amount) {
						double sub=damount-amount;					
						String update="update bank set amount =? where mobilenumber=?";
						PreparedStatement ps1=connection.prepareStatement(update);
						ps1.setDouble(1,sub);
						ps1.setString(2,mb);
						int num=ps1.executeUpdate();
						if (num!=0) {
							System.out.println("Amount debited successfully!");
							
						}
						else {
							System.out.println("404 error");
						}
				}
						else {
							System.out.println("Invalid amount");
						}
			}
						else {
							System.out.println("invalid Details");
						}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

	@Override
	public void changePassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mobiletransaction() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your mobilenumber");
		String mb=sc.next();
		System.out.println("Enter your Password");
		String password=sc.next();
		double sdamount;
		String sname;
		String smb;
		String select="select * from bank where mobilenumber=? and password=?";
		try {
			 connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, mb);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				sdamount=rs.getDouble(3);
				sname=rs.getString(3);
				smb=rs.getString(4);
				System.out.println("Enter receivers mobile number");
				String rmb=sc.next();
				String select1="select * from bank where mobilenumber=?";
				PreparedStatement ps1=connection.prepareStatement(select1);
				ps1.setString(1, rmb);
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next()) {
					System.out.println("Enter the Amount");
					double ramount=sc.nextDouble();
					if (sdamount>=ramount) {
						double rdamount=rs1.getDouble(3);
						double add=rdamount+ramount;
						double sub=sdamount-ramount;
						String updates="update bank set amount=? where mobilenumber=?";
						PreparedStatement pss=connection.prepareStatement(updates);
						pss.setDouble(1, sub);
						pss.setString(2, mb);
						int num=pss.executeUpdate();
						if(num!=0) {
							String updater="update bank set amount=? where mobilenumber=?";
							PreparedStatement psr=connection.prepareStatement(updater);
							psr.setDouble(1, add);
							psr.setString(2,rmb);
							int num1=psr.executeUpdate();
							if(num!=0) {
								System.out.println("Received from "+sname);
								System.out.println("mobile number :");
								for(int i=0;i<smb.length();i++)
								{
									if(i>2&&i<8) {
										System.out.println("*");
									}else {
										System.out.println(smb.charAt(i));
									}
								}
							}
				else {
					System.out.println();
					System.out.println("amount"+ramount+"/-");
				}	
							}
							else {
								System.out.println("404 error");
							}
						}
						else {
							System.out.println("insufficient balance");
						}
					}
					else {
						System.out.println("no data found");
					}
				}
				else {
				System.out.println("open new account");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void checkbalance() {
		// TODO Auto-generated method stub
		
	}

}

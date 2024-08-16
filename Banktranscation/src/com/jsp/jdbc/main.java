package com.jsp.jdbc;

import java.util.Scanner;

public class main {
public static void main(String[] args) {
	Bank bank=new BankDaoImplementation();
	boolean st=true;
	Scanner sc=new Scanner(System.in);
	while(st) {
		System.out.println("Enter \n 1 for debit \n 2 for Credit\n 3 for mobiletransaction");
		int a=sc.nextInt();
		switch(a)
		{
		case 1:bank.debit();
		break;
		case 2:bank.credit();
		break;
		case 3:bank.mobiletransaction();
		break;
		
		default :System.out.println("Enter valid number");
		}
		System.out.println("Do you want to continue \n yes \n no ");
		String response=sc.next();
		if(response.equalsIgnoreCase("yes")) {
			st=true;
		}
		else {
			st=false;
			System.out.println("Thank you visit again.....!!!!");
		}
	}
}
}
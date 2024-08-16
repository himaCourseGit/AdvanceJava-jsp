package org.jsp.Matrimony;

public class User1 {
TeluguMatrimony telugumatrimony;

public User1(TeluguMatrimony telugumatrimony) {
	super();
	this.telugumatrimony=telugumatrimony;
}
public static void main(String[] args) {
	User1 u=new User1(new Engineer());
	
	System.out.println(u.telugumatrimony.name());
	System.out.println(u.telugumatrimony.age());
	System.out.println(u.telugumatrimony.stream());
	System.out.println("********");
	
	User1 u2=new User1(new Doctor());
	
	System.out.println(u2.telugumatrimony.name());
	System.out.println(u2.telugumatrimony.age());
	System.out.println(u2.telugumatrimony.stream());
}
}

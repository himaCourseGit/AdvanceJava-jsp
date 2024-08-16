package org.jsp.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Address")
public class Address extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String city=req.getParameter("city");
		String tpin=req.getParameter("pin");
		int pin=Integer.parseInt(tpin);
		 HttpSession session=req.getSession();
		 String name=(String)session.getAttribute("name");
		 Integer age=(Integer)session.getAttribute("age");
		 Double salary=(Double)session.getAttribute("salary");
		 String mail=(String)session.getAttribute("mail");
		 
		 PrintWriter writer=resp.getWriter();
		 resp.setContentType("text/html");
		 if(name!=null) {
		 writer.println("<center><h1>"+name+"</h1></center>");
		 writer.println("<center><h1>"+age+"</h1></center>");
		 writer.println("<center><h1>"+salary+"</h1></center>");
		 writer.println("<center><h1>"+mail+"</h1></center>");
		 }else {
			 writer.println("<center><h1>Session time out</h1></center>");
		 }
		 
}

}

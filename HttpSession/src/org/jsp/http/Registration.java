package org.jsp.http;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Registration")
public class Registration extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String tage=req.getParameter("age");
		int age=Integer.parseInt(tage);
		String tsal=req.getParameter("salary");
		double salary =Double.parseDouble(tsal);
		String mail=req.getParameter("mail");
		HttpSession session=req.getSession();
		
		session.setAttribute("name", name);
		session.setAttribute("age", age);
		session.setAttribute("salary", salary);
		session.setAttribute("mail", mail);
		session.setMaxInactiveInterval(10);
		
		RequestDispatcher rd=req.getRequestDispatcher("Address.html");
				 rd.include(req,resp);
		
	}

}

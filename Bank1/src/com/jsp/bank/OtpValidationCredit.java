package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/OtpValidationCredit")
public class OtpValidationCredit  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String totp=req.getParameter("otp");
	
		int uotp=Integer.parseInt(totp);
		
		HttpSession session =req.getSession();
		Integer otp=(Integer) session.getAttribute("otp");
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		if(otp!=null) {
			if(otp==uotp) {
				RequestDispatcher rd=req.getRequestDispatcher("CreditAmount.html");
				rd.include(req, resp);
				//writer.println("<center><h1>Invalid details</h1></center>");
			}
			else {
				writer.println("<center><h1>"+otp+"</h1></center>");
				RequestDispatcher rd=req.getRequestDispatcher("OtpValidationCredit.html");
				rd.include(req, resp);
				writer.println("<center><h1>Invalid OTP</h1></center>");
				
			}
		}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("Credit.html");
				rd.include(req, resp);
				writer.println("<center><h1>Session time out</h1></center>");
			}
		}
	}



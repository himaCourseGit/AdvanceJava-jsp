package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/AmountTransaction")
public class AmountTransaction extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tamount=req.getParameter("amount");
		double ramount=Double.parseDouble(tamount);
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session=req.getSession();
		String rmb=(String)session.getAttribute("rmb");
		System.out.println(rmb);
		String smb=(String)session.getAttribute("smb");
		System.out.println(smb);
		Double sdamount=(Double) session.getAttribute("sdamount1");
		Double rdamount=(Double) session.getAttribute("rdamount1");
		System.out.println(sdamount);
		System.out.println(rdamount);
		if(ramount>0)
		{
			if(sdamount>=ramount)
			{
				double sub=sdamount-ramount;
				double add=rdamount+ramount;
				String updater="update bank set amount=? where mobilenumber=?";
				String updates="update bank  set amount=? where mobilenumber=?";
				String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
				try {
					Connection connection=DriverManager.getConnection(url);
					PreparedStatement ps=connection.prepareStatement(updates);
					ps.setDouble(1, sub);
					ps.setString(2, smb);
					int n=ps.executeUpdate();
					if(n>0)
					{
						PreparedStatement ps1=connection.prepareStatement(updater);
						ps1.setDouble(1,add);
						ps1.setString(2,rmb);
						int n1=ps1.executeUpdate();
						if(n1>0)
						{
							RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
							rd.include(req, resp);
							writer.println("<center><h1>***Amount received***</h1></center>");
							writer.println("<center><h1>*** Tranaction successful***</h1></center>");
						}
						else {
							RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
							rd.include(req, resp);
							writer.println("<center><h1>***Sorry we are Processing your Amount***</h1></center>");
						}
					}
					else {
						RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
						rd.include(req, resp);
						writer.println("<center><h1>404</h1></center>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				writer.println("<center><h1>Invalid</h1></center>");
			}
		}
		else {
			writer.println("<center><h1>Insufficient Balance</h1></center>");
		}
	}

}

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table {
	border: 5px solid black;
}

td {
	background-color: Crimson;
	color: lime;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="CheckBalance.jsp">
			<input placeholder="Enter your MobileNumber" name="mb"
				style="width: 200px; height: 30px;"> <br> <input
				placeholder="Enter your pin" name="password"
				style="width: 200px; height: 30px;"> <br> <input
				type="submit" value="LOGIN" style="width: 200px; height: 30px";>
		</form>
	</center>
	<%
	String mb = request.getParameter("mb");
	String password = request.getParameter("password");

	String url = "jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
	String select = "select * from bank where mobilenumber=?and password=?";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url);
		PreparedStatement ps = connection.prepareStatement(select);
		ps.setString(1, mb);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (mb != null) {
			if (rs.next()) {
		out.println("<center><table><tr><th>Id</th><th>Name</th><th>Amount" + "<th></tr>");
		out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getDouble(3)
				+ "</td><tr></table></center>");
			} else {
		out.println("<h1>invalid details</h1>");
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
</body>
</html>
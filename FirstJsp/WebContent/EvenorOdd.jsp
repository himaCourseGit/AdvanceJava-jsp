<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! int a=2; %>
<% int a=3;
if(a%2==0)
{
	out.println("<center><h1>Even</h1></center>");
}
else{
out.println("<center><h1>odd</h1></center>");
}
%>
</body>
</html>
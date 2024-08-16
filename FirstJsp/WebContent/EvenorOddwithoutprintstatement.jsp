<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!int a=2; %>
<%
int a=3;
if(this.a%2==0){
	%>
	<h1><%="Even"%></h1>
	<%
}else{
	%>
	<h1><%="odd" %></h1>
	<%
	}
	%>
	<%! int c=10;
	int b=20;
	public int add(){
		return c+b;
	}
	%>
	<%=add() %>
	<%request.getParameter(""); %>
</body>
</html>
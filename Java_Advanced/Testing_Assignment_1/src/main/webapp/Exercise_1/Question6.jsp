<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@page errorPage="error.jsp"%>
	<%
	String num1 = "s";
	String num2 = "n2";

	int a = Integer.parseInt(num1);
	int b = Integer.parseInt(num2);
	out.print("Divison of number is : " + a / b);
	%>
</body>
</html>
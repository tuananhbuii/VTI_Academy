<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String so1 = request.getParameter("txtSo1");
	float a = Float.parseFloat(so1);
	String so2 = request.getParameter("txtSo2");
	float b = Float.parseFloat(so2);
	String action1 = request.getParameter("cong");
	float cong = a + b;
	out.print(a + "+" + b + "=" + cong);
	String action2 = request.getParameter("tru");
	float tru = a - b;
	out.print(a + "-" + b + "=" + tru);
	String action3 = request.getParameter("nhan");
	float nhan = a * b;
	out.print(a + "*" + b + "=" + nhan);
	String action4 = request.getParameter("chia");
	float chia = a / b;
	out.print(a + "/" + b + "=" + chia);
	%>
</body>
</html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Yash9921");
PreparedStatement ps=c.prepareStatement("select * from account");
ResultSet s=ps.executeQuery();
%>
<table cellpadding="20px" align="center" border="2">
	<th>Id</th>
	<th>name</th>
	<th>Age</th>
	<th>AccountNo</th>
	<th>Pin</th>
	<th>Balance</th>
	<th>Address</th>
<% while(s.next()) { %>
	<tr>
	<td><%=s.getInt(1) %></td>
	<td><%=s.getString(2) %></td>
	<td><%=s.getInt(3) %></td>
	<td><%=s.getLong(4) %></td>
	<td><%=s.getInt(5)%></td>
	<td><%=s.getDouble(6) %></td>
	<td><%=s.getString(7) %></td>
	</tr>
<%} %>
</table>
</body>
</html>
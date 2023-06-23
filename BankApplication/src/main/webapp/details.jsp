<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
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

	<%  long accountno =(Long)session.getAttribute("accountno");
		int pin=(Integer)session.getAttribute("pin");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Yash9921");
		PreparedStatement ps=c.prepareStatement("select * from account where AccountNo=? and Pin=?");
		ps.setLong(1, accountno);
		ps.setInt(2, pin);
		ResultSet rs=ps.executeQuery();
	%>
	
	
	<%if(rs.next()){ %>
	<table cellpadding="20px" align="center" border="2">
	<th>Id</th>
	<th>name</th>
	<th>Age</th>
	<th>AccountNo</th>
	<th>Pin</th>
	<th>Balance</th>
	<th>Address</th>
	<tr>
	<td><%=rs.getInt(1) %></td>
	<td><%=rs.getString(2) %></td>
	<td><%=rs.getInt(3) %></td>
	<td><%=rs.getLong(4) %></td>
	<td><%= rs.getInt(5)%></td>
	<td><%=rs.getDouble(6) %></td>
	<td><%=rs.getString(7) %></td>
	</tr>
	<%} else {%>
	<h1 align="center">Invalid Id</h1>>
	<% } %>
	</table>
</body>
</html>
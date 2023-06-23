package com.bankApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/accLogin")
public class AccLog extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String accountno=req.getParameter("accountno");
		 String pin=req.getParameter("pin");
		 long accNo=Long.parseLong(accountno);
		 int pin1=Integer.parseInt(pin);
		 /* to access through out the application*/
		 HttpSession hs=req.getSession();
		 hs.setAttribute("accountno", accNo);
		 hs.setAttribute("pin", pin1);
		 
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Yash9921");
			PreparedStatement p=c.prepareStatement("select * from account where AccountNo=? AND  Pin=?");
			p.setLong(1, accNo);
			p.setInt(2, pin1);
			ResultSet rs=p.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rs1=req.getRequestDispatcher("Accounttransaction.html");
				rs1.forward(req, resp);
			}
			
			else
			{
				PrintWriter p1=resp.getWriter();
				p1.write("<h2 align='center'>Invalid Data </h2>");
				RequestDispatcher rs1=req.getRequestDispatcher("AccountLogin.html");
				rs1.include(req, resp);
				resp.setContentType("text/html");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

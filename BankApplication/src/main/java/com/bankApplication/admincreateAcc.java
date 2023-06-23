package com.bankApplication;

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

@WebServlet("/Adminaccount")
public class admincreateAcc extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int id1 = Integer.parseInt(id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Yash9921");
			PreparedStatement ps = c.prepareStatement("insert into admin values(?,?,?)");
			ps.setInt(1, id1);
			ps.setString(2, email);
			ps.setString(3, password);
			if (ps.execute()) {
				PrintWriter pw = resp.getWriter();
				pw.write("<h1 align='center'>Admin Account Is Not Created</h1>");
			} else {
				PrintWriter p = resp.getWriter();
				p.write("<h1 align='center'>Admin Account Is Created</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

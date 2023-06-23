package com.bankApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveaccount")
public class SaveAccount extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String pin = req.getParameter("pin");
		String address = req.getParameter("address");
		int id1 = Integer.parseInt(id);
		int age1 = Integer.parseInt(age);
		int pin1 = Integer.parseInt(pin);

		Random r = new Random();
		long accountNumber = r.nextLong(100000000000l);
		double balance = 5000;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Yash9921");
			PreparedStatement ps = c.prepareStatement(
					"insert into account(id,Name,Age,AccountNo,Pin,Balanace,Address)" + "values(?,?,?,?,?,?,?)");
			ps.setInt(1, id1);
			ps.setString(2, name);
			ps.setInt(3, age1);
			ps.setLong(4, accountNumber);
			ps.setInt(5, pin1);
			ps.setDouble(6, balance);
			ps.setString(7, address);
			ps.execute();
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter p = resp.getWriter();
		p.print("<h1 align='center'> Account create successfully</h1>");
		RequestDispatcher rd = req.getRequestDispatcher("login.html");
		rd.include(req, resp);

	}
}

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

@WebServlet("/withdrow")
public class WithdrowM extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// from front end
		String amount = req.getParameter("amount");
		double balance = Double.parseDouble(amount);

		// this is for the data from login page
		HttpSession hs = req.getSession();
		Long accountNo = (Long) hs.getAttribute("accountno");// issue rise here because of data type
		int pin = (Integer) hs.getAttribute("pin");
		System.out.println(accountNo);
		System.out.println(pin);
		// jdbc data
		try {
			getClass().forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Yash9921");
			PreparedStatement ps = c.prepareStatement("select  Balanace from account where  AccountNo=? AND  Pin=?");
			ps.setLong(1, accountNo);
			ps.setInt(2, pin);
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				double balance1 = r.getDouble(1);
				if (balance <= balance1) {
					double total = balance1 - balance;
					PreparedStatement ps1 = c
							.prepareStatement("update account set Balanace=? where  AccountNo=? And pin=?");
					ps1.setDouble(1, total);
					ps1.setLong(2, accountNo);
					ps1.setInt(3, pin);
					ps1.execute();
					PrintWriter p1 = resp.getWriter();
					p1.print("<h1 align='center'> Thankyou for your Payment </h1>");
					RequestDispatcher rd = req.getRequestDispatcher("login.html");
					rd.include(req, resp);
					resp.setContentType("text/html");
				} else {
					PrintWriter p = resp.getWriter();
					p.write("Amount is more than balance");
					RequestDispatcher rd = req.getRequestDispatcher("login.html");
					rd.include(req, resp);
					resp.setContentType("text/html");
				}
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}

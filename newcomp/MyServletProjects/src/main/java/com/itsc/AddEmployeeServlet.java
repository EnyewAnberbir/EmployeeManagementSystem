package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	private static final String query ="insert into employees(name, designation, salary)values(?, ?, ?)";
	@Override
	protected void doGet(

	HttpServletRequest req,
	HttpServletResponse resp) throws ServletException,IOException{

	//get PrintWriter
	PrintWriter pw = resp.getWriter();
	//set content type
	resp.setContentType("text/html");
	//get the book info
	String name = req.getParameter("name");
	String designation = req.getParameter("designation");
	float salary = Float.parseFloat(req.getParameter("salary"));
	//load the jdbc driver  for the driver I use the point of reference for the point I would
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	System.out.println("MySQL JDBC Driver loaded successfully");
	} catch (ClassNotFoundException cnf) {
	cnf.printStackTrace();
	System.out.println("Error loading MySQL JDBC Driver");
	}
	//generate the connection
	try {
	Connection conn =

	DriverManager.getConnection(
	"jdbc:mysql://localhost:3306/employeedata",
	"root",
	"root");
	System.out.println("Connected to the database");

	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, name);
	ps.setString(2, designation);
	ps.setFloat(3, salary);
	int count = ps.executeUpdate();
	if(count == 1) {
	pw.println("<h2> Employee registered successfully.</h2");
	}
	else {
	pw.println("<h2> Employee Not registered successfully.</h2");
	}
	} catch (SQLException se) {
	se.printStackTrace();
	System.out.println("Error connecting to the database");
	pw.println("<h1>" + se.getMessage() + "</h1>");
	} catch (Exception e) {
		e.printStackTrace();
		pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		pw.println("<a href='Home.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='viewemployee'>View Employee</a>");
	}
	@Override
	protected void doPost(

	HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException {

	doGet(req, resp);
	}
}

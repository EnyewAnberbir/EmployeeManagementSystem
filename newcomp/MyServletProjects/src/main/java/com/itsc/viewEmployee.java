package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewemployee")
public class viewEmployee extends HttpServlet {
	private static final String query = "select id, name, designation, salary from employees";
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
//	float salary = Float.parseFloat(req.getParameter("salary"));
	String salaryParameter = req.getParameter("salary");

	if (salaryParameter != null) {
	    try {
	        float salary = Float.parseFloat(req.getParameter("salary"));
	        // Continue processing with the salary value
	    } catch (NumberFormatException e) {
	        // Handle the case where the parameter is not a valid float
	        // Log or throw an exception, or provide a default value, depending on your requirements
	    }
	} else {
	    // Handle the case where the parameter is null
	    // Log, throw an exception, or provide a default value, depending on your requirements
	}

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
	ResultSet rs = ps.executeQuery();
	pw.println("<table>");
	pw.println("<tr>");
	pw.println("<th>Employee Id</th>");
	pw.println("<th>Employee Name</th>");
	pw.println("<th>Employee Designation</th>");
	pw.println("<th>Employee Salary</th>");
	pw.println("<th>Edit</th>");
	pw.println("<th>Delete</th>");
	pw.println("</tr>");
	while(rs.next()) {
	pw.println("<tr>");
	pw.println("<td>" + rs.getInt(1) + "</td>");
	pw.println("<td>" + rs.getString(2) + "</td>");
	pw.println("<td>" + rs.getString(3) + "</td>");
	pw.println("<td>" + rs.getString(4) + "</td>");
	pw.println("<td><a href ='edit?id=" + rs.getInt(1) +
			"'>edit</a></td>");
	pw.println("<td><a href ='delete?id=" + rs.getInt(1) +
			"'>delete</a></td>");
	pw.println("</tr>");
	}
	pw.println("</table>");
	}catch (Exception e) {
		e.printStackTrace();
		pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		pw.println("<a href='Home.html'>Home</a>");
	
	}
	@Override
	protected void doPost(

	HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException {

	doGet(req, resp);
	}
}

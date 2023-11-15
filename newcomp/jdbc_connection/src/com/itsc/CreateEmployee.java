package com.itsc;
import java.sql.*;
public class CreateEmployee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "root";
		String databaseName = "employeedata";
		try {
		// Establish a connection to the MySQL server
		Connection connection = DriverManager.getConnection
		(url, username, password);
		// Create a statement
		Statement statement = connection.createStatement();
		// Execute the SQL query to create the new database
		String createDatabaseSQL = "CREATE DATABASE " + databaseName;
		statement.executeUpdate(createDatabaseSQL);
		System.out.println("Database '" + databaseName + "' createdsuccessfully.");
		// close resources
		statement.close();
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	}



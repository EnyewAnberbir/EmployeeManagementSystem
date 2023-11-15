package com.itsc;
import java.sql.*;
public class CreateDatabase {
	public static void main(String[] args) {
		 String url = "jdbc:mysql://localhost:3306/bookregister";
	        String username = "root";
	        String password = "root";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish a connection to the MySQL server
	            Connection connection = DriverManager.getConnection(url, username, password);

	            // Create a statement
	            Statement statement = connection.createStatement();

	            // Create the 'books' table if it does not exist
	            String createTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
	                    "id INT AUTO_INCREMENT PRIMARY KEY," +
	                    "bookname VARCHAR(255) NOT NULL," +
	                    "bookedition VARCHAR(255) NOT NULL," +
	                    "bookprice FLOAT NOT NULL" +
	                    ")";
	            statement.executeUpdate(createTableSQL);

	            System.out.println("Table 'books' created successfully.");

	            // Close resources
	            statement.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
			
}


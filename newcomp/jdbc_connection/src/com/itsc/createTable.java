package com.itsc;
import java.sql.*;
public class createTable {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/employeedata";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the MySQL server
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Create the 'employees' table if it does not exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "designation VARCHAR(255) NOT NULL," +
                    "salary FLOAT NOT NULL" +
                    ")";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'employees' created successfully in the employeedata database.");

            // Close resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}

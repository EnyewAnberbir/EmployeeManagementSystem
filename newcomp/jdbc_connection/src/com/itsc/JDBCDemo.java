package com.itsc;

import java.sql.*;
public class JDBCDemo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                // Your database code here
                System.out.println("Connected to the database!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
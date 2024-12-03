/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author truon
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FoodStoreDB";
    private static final String USERNAME = "sa"; 
    private static final String PASSWORD = "123"; 

    // Static block to load the JDBC driver
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("SQL Server JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading SQL Server JDBC Driver");
            e.printStackTrace();
        }
    }

    // Method to get the database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
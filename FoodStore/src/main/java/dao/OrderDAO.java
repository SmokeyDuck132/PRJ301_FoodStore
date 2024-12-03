/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Order;
import model.OrderDetail;
import util.DatabaseConnection;
import java.sql.*;
import java.util.List;
import util.DatabaseConnection;

import model.Order;
import util.DatabaseConnection;

import java.sql.*;

/**
 *
 * @author truon
 */
public class OrderDAO {

    // Create a new order and return the generated order ID
    public int createOrder(Order order) {
        String sql = "INSERT INTO Orders (UserID, OrderDate, TotalAmount, FullName, Location, PhoneNumber) " +
                     "VALUES (?, GETDATE(), ?, ?, ?, ?)";
        int orderId = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, order.getUserId());
            stmt.setDouble(2, order.getTotalAmount());
            stmt.setString(3, order.getFullName());
            stmt.setString(4, order.getLocation());
            stmt.setString(5, order.getPhoneNumber());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            // Retrieve the generated order ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                    order.setOrderId(orderId);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately (e.g., logging)
        }

        return orderId;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.OrderDetail;
import model.Product;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
public class OrderDetailDAO {

    // Create a new order detail
    public void createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderDetail.getOrderId());
            stmt.setInt(2, orderDetail.getProductId());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getPrice());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately (e.g., logging)
        }
    }

    // Get order details by order ID
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT od.OrderDetailID, od.OrderID, od.ProductID, od.Quantity, od.Price, " +
                     "p.Name AS ProductName " +
                     "FROM OrderDetails od " +
                     "JOIN Products p ON od.ProductID = p.ProductID " +
                     "WHERE od.OrderID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(rs.getInt("OrderDetailID"));
                    orderDetail.setOrderId(rs.getInt("OrderID"));
                    orderDetail.setProductId(rs.getInt("ProductID"));
                    orderDetail.setQuantity(rs.getInt("Quantity"));
                    orderDetail.setPrice(rs.getDouble("Price"));

                    // Set product details
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductID"));
                    product.setName(rs.getString("ProductName"));
                    orderDetail.setProduct(product);

                    orderDetails.add(orderDetail);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately (e.g., logging)
        }

        return orderDetails;
    }
}
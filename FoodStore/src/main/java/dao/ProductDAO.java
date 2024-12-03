/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Product;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
public class ProductDAO {

    public List<Product> getProductsByCategory(String category) throws SQLException {
        String query = "SELECT * FROM Products WHERE Category = ?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                product.setCategory(rs.getString("Category"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setStockQuantity(rs.getInt("StockQuantity"));
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> searchProducts(String keyword) throws SQLException {
        String query = "SELECT * FROM Products WHERE Name LIKE ? OR Category LIKE ?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                product.setCategory(rs.getString("Category"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setStockQuantity(rs.getInt("StockQuantity"));
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> getTopPurchasedProducts() throws SQLException {
        String query = "SELECT TOP 5 P.ProductID, P.Name, SUM(OD.Quantity) as TotalSold " +
                       "FROM OrderDetails OD JOIN Products P ON OD.ProductID = P.ProductID " +
                       "GROUP BY P.ProductID, P.Name ORDER BY TotalSold DESC";

        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                products.add(product);
            }
        }
        return products;
    }
    
    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET Name = ?, Category = ?, Description = ?, Price = ?, StockQuantity = ? " +
                     "WHERE ProductID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setInt(6, product.getProductId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }

    // Additional methods as needed
}

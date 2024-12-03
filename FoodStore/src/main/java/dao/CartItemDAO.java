/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.CartItem;
import model.Product;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
public class CartItemDAO {

    // Get cart items by user ID
    public List<CartItem> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT ci.CartItemID, ci.UserID, ci.ProductID, ci.Quantity, " +
                     "p.Name, p.Category, p.Description, p.Price, p.StockQuantity " +
                     "FROM CartItems ci " +
                     "JOIN Products p ON ci.ProductID = p.ProductID " +
                     "WHERE ci.UserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setCartItemId(rs.getInt("CartItemID"));
                    cartItem.setUserId(rs.getInt("UserID"));
                    cartItem.setQuantity(rs.getInt("Quantity"));

                    // Set product details
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductID"));
                    product.setName(rs.getString("Name"));
                    product.setCategory(rs.getString("Category"));
                    product.setDescription(rs.getString("Description"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setStockQuantity(rs.getInt("StockQuantity"));

                    cartItem.setProduct(product);

                    cartItems.add(cartItem);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately (e.g., logging)
        }

        return cartItems;
    }

    // Clear cart items by user ID
    public void clearCartByUserId(int userId) {
        String sql = "DELETE FROM CartItems WHERE UserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }
}

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
public class CartDAO {

    public boolean addCartItem(int userId, int productId, int quantity) throws SQLException {
        String query = "INSERT INTO CartItems (UserID, ProductID, Quantity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public List<CartItem> getCartItems(int userId) throws SQLException {
        String query = "SELECT CI.CartItemID, CI.Quantity, P.* " +
                       "FROM CartItems CI JOIN Products P ON CI.ProductID = P.ProductID " +
                       "WHERE CI.UserID = ?";

        List<CartItem> cartItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setCartItemId(rs.getInt("CartItemID"));
                cartItem.setUserId(userId);
                cartItem.setQuantity(rs.getInt("Quantity"));

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
        return cartItems;
    }

    public boolean clearCart(int userId) throws SQLException {
        String query = "DELETE FROM CartItems WHERE UserID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    // Additional methods as needed
}

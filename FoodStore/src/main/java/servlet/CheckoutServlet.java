/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import dao.CartItemDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import model.CartItem;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author truon
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private CartItemDAO cartItemDAO;
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private ProductDAO productDAO;

    public void init() {
        cartItemDAO = new CartItemDAO();
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the current user from the session
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        if (user == null) {
            // User is not logged in; redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Retrieve user details from the form
        String fullName = request.getParameter("fullName");
        String location = request.getParameter("location");
        String phoneNumber = request.getParameter("phoneNumber");

        // Get the user's cart items
        List<CartItem> cartItems = cartItemDAO.getCartItemsByUserId(user.getUserId());

        if (cartItems == null || cartItems.isEmpty()) {
            // Cart is empty; redirect to cart page with a message
            request.setAttribute("message", "Your cart is empty.");
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
            return;
        }

        // Calculate total amount
        double totalAmount = 0.0;
        for (CartItem item : cartItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        // Create a new order
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setFullName(fullName);
        order.setLocation(location);
        order.setPhoneNumber(phoneNumber);
        order.setTotalAmount(totalAmount);

        // Save the order and get the generated order ID
        int orderId = orderDAO.createOrder(order);

        // Save order details and update product stock
        for (CartItem item : cartItems) {
            Product product = item.getProduct();

            // Create order detail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(product.getProductId());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrice(product.getPrice());

            // Save order detail
            orderDetailDAO.createOrderDetail(orderDetail);

            // Update product stock
            int newStock = product.getStockQuantity() - item.getQuantity();
            product.setStockQuantity(newStock);
            productDAO.updateProduct(product);
        }

        // Clear the user's cart
        cartItemDAO.clearCartByUserId(user.getUserId());

        // Forward to order confirmation page
        request.setAttribute("order", order);
        request.setAttribute("orderDetails", orderDetailDAO.getOrderDetailsByOrderId(orderId));
        request.getRequestDispatcher("/orderConfirmation.jsp").forward(request, response);
    }
}
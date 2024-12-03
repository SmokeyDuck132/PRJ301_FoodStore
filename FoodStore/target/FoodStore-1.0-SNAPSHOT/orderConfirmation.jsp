<%-- 
    Document   : orderConfirmation
    Created on : Nov 4, 2024, 6:29:52 PM
    Author     : truon
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<main>
    <h2>Order Confirmation</h2>
    <p>Thank you for your purchase, ${order.fullName}!</p>
    <p>Your order ID is: ${order.orderId}</p>
    <h3>Order Details:</h3>
    <table>
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        <c:forEach var="detail" items="${orderDetails}">
            <tr>
                <td>${detail.productName}</td>
                <td>${detail.quantity}</td>
                <td>$${detail.price}</td>
            </tr>
        </c:forEach>
    </table>
    <p>Total Amount: $${order.totalAmount}</p>
</main>

<%@ include file="footer.jsp" %>

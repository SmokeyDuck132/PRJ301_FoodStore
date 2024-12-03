<%-- 
    Document   : confirmation
    Created on : Nov 4, 2024, 5:41:14 PM
    Author     : truon
--%>

<%@ include file="header.jsp" %>
<!-- confirmation.jsp -->
<main>
    <h2>Order Confirmation</h2>
    <p>Thank you for your purchase, ${user.fullName}!</p>
    <p>Your order ID is <strong>${order.orderId}</strong>.</p>
    <p>Order Date: ${order.orderDate}</p>
    <p>Total Amount: $${order.totalAmount}</p>
    <h3>Order Details:</h3>
    <ul>
        <c:forEach var="detail" items="${order.orderDetails}">
            <li>${detail.product.name} x ${detail.quantity} @ $${detail.price} each</li>
        </c:forEach>
    </ul>
    <p>You will receive an email confirmation shortly.</p>
</main>
<%@ include file="footer.jsp" %>

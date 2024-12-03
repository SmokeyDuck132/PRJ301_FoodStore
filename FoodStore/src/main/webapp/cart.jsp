<%-- 
    Document   : cart
    Created on : Nov 4, 2024, 5:40:51 PM
    Author     : truon
--%>

<%@ include file="header.jsp" %>
<!-- cart.jsp -->
<main>
    <h2>Your Shopping Cart</h2>
    <c:if test="${not empty cartItems}">
        <form action="${pageContext.request.contextPath}/UpdateCartServlet" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0"/>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td>$${item.product.price}</td>
                            <td>
                                <input type="number" name="quantity_${item.cartItemId}" value="${item.quantity}" min="1" max="${item.product.stockQuantity}">
                            </td>
                            <td>$${item.product.price * item.quantity}</td>
                        </tr>
                        <c:set var="total" value="${total + (item.product.price * item.quantity)}"/>
                    </c:forEach>
                </tbody>
            </table>
            <p>Total: $${total}</p>
            <button type="submit">Update Cart</button>
            <a href="${pageContext.request.contextPath}/CheckoutServlet" class="button">Proceed to Checkout</a>
        </form>
    </c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty.</p>
    </c:if>
</main>
<%@ include file="footer.jsp" %>

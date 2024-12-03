<%-- 
    Document   : checkout
    Created on : Nov 4, 2024, 5:41:03 PM
    Author     : truon
--%>

<%@ include file="header.jsp" %>
<!-- checkout.jsp -->
<main>
    <h2>Checkout</h2>
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/CheckoutServlet" method="post">
        <label>Full Name:</label>
        <input type="text" name="fullName" value="${user.fullName}" required>
        <label>Location:</label>
        <input type="text" name="location" value="${user.location}" required>
        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" value="${user.phoneNumber}" required>
        <button type="submit">Place Order</button>
    </form>
</main>
<%@ include file="footer.jsp" %>

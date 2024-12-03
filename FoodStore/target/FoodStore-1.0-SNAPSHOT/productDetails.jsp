<%-- 
    Document   : productDetails
    Created on : Nov 4, 2024, 5:40:39 PM
    Author     : truon
--%>

<%@ include file="header.jsp" %>
<!-- productDetails.jsp -->
<main>
    <h2>${product.name}</h2>
    <p>${product.description}</p>
    <p>Category: ${product.category}</p>
    <p>Price: $${product.price}</p>
    <form action="${pageContext.request.contextPath}/AddToCartServlet" method="post">
        <input type="hidden" name="productId" value="${product.productId}">
        <label>Quantity:</label>
        <input type="number" name="quantity" value="1" min="1" max="${product.stockQuantity}" required>
        <button type="submit">Add to Cart</button>
    </form>
    <c:if test="${not empty successMessage}">
        <div class="success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>
</main>
<%@ include file="footer.jsp" %>

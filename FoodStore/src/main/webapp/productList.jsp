<%-- 
    Document   : productList
    Created on : Nov 4, 2024, 5:40:27 PM
    Author     : truon
--%>

<%@ include file="header.jsp" %>
<!-- productList.jsp -->
<main>
    <h2>Products</h2>
    <c:if test="${not empty products}">
        <ul class="product-list">
            <c:forEach var="product" items="${products}">
                <li>
                    <h4>${product.name}</h4>
                    <p>${product.description}</p>
                    <p>Price: $${product.price}</p>
                    <form action="${pageContext.request.contextPath}/ProductDetailsServlet" method="get">
                        <input type="hidden" name="productId" value="${product.productId}">
                        <button type="submit">View Details</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty products}">
        <p>No products found.</p>
    </c:if>
</main>
<%@ include file="footer.jsp" %>

<%-- 
    Document   : index.jsp
    Created on : Nov 4, 2024, 5:39:46 PM
    Author     : truon
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ include file="header.jsp" %>

<main>
    <h2>Welcome to FoodStore</h2>
    <h3>Top Purchased Products</h3>

    <c:choose>
        <c:when test="${not empty topProducts}">
            <ul class="product-list">
                <c:forEach var="product" items="${topProducts}">
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
        </c:when>
        <c:otherwise>
            <p>No top products available at the moment.</p>
        </c:otherwise>
    </c:choose>
</main>

<%@ include file="footer.jsp" %>

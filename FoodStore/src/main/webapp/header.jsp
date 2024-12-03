<%-- 
    Document   : header
    Created on : Nov 4, 2024, 5:39:15 PM
    Author     : truon
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- header.jsp -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FoodStore</title>
    <!-- Include CSS files -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <header>
        <h1><a href="${pageContext.request.contextPath}/index.jsp">FoodStore</a></h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/ProductListServlet?category=food">Food</a></li>
                <li><a href="${pageContext.request.contextPath}/ProductListServlet?category=drink">Drink</a></li>
                <li><a href="${pageContext.request.contextPath}/ProductListServlet?category=home">Home Products</a></li>
                <li><a href="${pageContext.request.contextPath}/ViewCartServlet">Cart</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                        <li><a href="${pageContext.request.contextPath}/register.jsp">Register</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <!-- Search Form -->
        <form action="${pageContext.request.contextPath}/SearchServlet" method="get">
            <input type="text" name="keyword" placeholder="Search products...">
            <button type="submit">Search</button>
        </form>
    </header>

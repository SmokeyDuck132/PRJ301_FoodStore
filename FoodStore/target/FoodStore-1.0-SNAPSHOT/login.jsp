<%-- 
    Document   : login.jsp
    Created on : Nov 4, 2024, 5:40:04 PM
    Author     : truon
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!-- login.jsp -->
<main>
    <h2>Login</h2>
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post" accept-charset="UTF-8">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/register.jsp">Register here</a>.</p>
</main>
<%@ include file="footer.jsp" %>
<%-- 
    Document   : register
    Created on : Nov 4, 2024, 5:40:15 PM
    Author     : truon
--%>

<%@ include file="header.jsp" %>
<!-- register.jsp -->
<main>
    <h2>Register</h2>
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="success">${successMessage}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
        <label>Full Name:</label>
        <input type="text" name="fullName" required>
        <label>Email:</label>
        <input type="email" name="email" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword" required>
        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a>.</p>
</main>
<%@ include file="footer.jsp" %>

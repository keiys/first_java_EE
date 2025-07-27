<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 14.07.2025
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
    <div class="logo">WELCOME</div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            response.getWriter().println(errorMessage);
        }
    %>

    <form method="post" action="/login-page">
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="text" class="form-control" name="email" placeholder="email">
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" name="password" placeholder="password">
        </div>
        <button type="submit" class="btn-login">LOG IN</button>
    </form>

    <div class="forgot-link">
        <a href="registration-page.jsp">Registration</a> |
        <a href="reset-password-page.jsp">Reset password</a>
    </div>
</div>
</body>
</html>

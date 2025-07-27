<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 17.07.2025
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="login-box">
    <div class="logo">CHANGE PASSWORD</div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            response.getWriter().println(errorMessage);
        }
    %>

    <form method="post" action="/change-password">
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" placeholder="password">
        </div>
        <div class="mb-3">
            <label class="form-label">Confirm Password</label>
            <input type="password" name="confirmPassword" placeholder="confirmPassword">
        </div>
        <button type="submit" class="btn-login">CHANGE PASSWORD</button>
    </form>
</div>
</body>
</html>

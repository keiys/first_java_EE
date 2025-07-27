<%--
  Created by IntelliJ IDEA.
  User: DiGi School
  Date: 6/16/2025
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
    <div class="logo">REGISTRATION</div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            response.getWriter().println(errorMessage);
        }
    %>

    <form method="post" action="/registration">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" class="form-control" name="name" placeholder="name">
        </div>
        <div class="mb-3">
            <label class="form-label">Surname</label>
            <input type="text" class="form-control" name="surname" placeholder="surname">
        </div>
        <div class="mb-3">
            <label class="form-label">Year</label>
            <input type="text" class="form-control" name="year" placeholder="year">
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="text" class="form-control" name="email" placeholder="email">
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" name="password" placeholder="password">
        </div>
        <button type="submit" class="btn-login">SIGN UP</button>
    </form>

    <div class="forgot-link">
        <a href="login-page.jsp">Back to login</a>
    </div>
</div>
</body>
</html>

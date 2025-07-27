<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.07.2025
  Time: 21:34
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
    <div class="logo">WRITE OLD PASSWORD</div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            response.getWriter().println(errorMessage);
        }
    %>

    <form method="get" action="/old-password">
        <div class="mb-3">
            <input type="password" name="password" placeholder="password">
        </div>
        <button type="submit" class="btn-login">SUBMIT</button>
    </form>
</div>
</body>
</html>

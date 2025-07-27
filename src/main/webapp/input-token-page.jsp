<%--
  Created by IntelliJ IDEA.
  User: DiGi School
  Date: 6/25/2025
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="login-box">
    <div class="logo">WRITE TOKEN</div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            response.getWriter().println(errorMessage);
        }
    %>

    <form method="post" action="/input-token">
        <%
            String email = (String) session.getAttribute("email");
            if (email != null) {
        %>
        <p style="color: white;"><%= email %></p><br>
        <%
            }
        %>
        <div class="mb-3">
            <input type="text" name="resetToken">
        </div>
        <button type="submit" class="btn-login">CHANGE PASSWORD</button>
    </form>
    <form method="get" action="/input-token">
        <button type="submit" class="btn-login">SEND AGAIN</button>
    </form>
</div>
</body>
</html>

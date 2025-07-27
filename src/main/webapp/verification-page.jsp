<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 03.07.2025
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification Page</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="login-box">
    <div class="logo">VERIFY YOUR EMAIL</div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            response.getWriter().println(errorMessage);
        }
    %>

    <form method="post" action="/verify-email">
        <%
            String email = (String) session.getAttribute("email");
            if (email != null) {
        %>
        <p style="color: white;"><%= email %></p><br>
        <%
            }
        %>
        <div class="mb-3">
            <label class="form-label">Verification Code</label>
            <input type="text" name="verifyCode">
        </div>
        <button type="submit" class="btn-login">SUBMIT</button>
    </form>
    <form method="get" action="/verify-email">
        <button type="submit" class="btn-login">SEND AGAIN</button>
    </form>
</div>

</body>
</html>

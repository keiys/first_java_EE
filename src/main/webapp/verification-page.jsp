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
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        response.getWriter().println(errorMessage);
    }
%>

<form method="post" action="/verify-email">
<h1>Verify Your Email</h1><br><br>
    <%
        String email = (String) session.getAttribute("email");
        if (email != null){
            response.getWriter().println(email);
        }
    %>
    <input type="text" name="verifyCode"><br><br>
    <input type="submit">
</form>
<form method="get" action="/verify-email">
    <input type="submit" value="Send again">
</form>
</body>
</html>

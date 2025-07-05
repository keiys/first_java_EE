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
</head>
<body>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        response.getWriter().println(errorMessage);
    }
%>

<form method="post" action="/registration">
    <h1>Registration Page</h1><br>
    name: <input type="text" name="name" placeholder="name"><br><br>
    surname: <input type="text" name="surname" placeholder="surname"><br><br>
    year: <input type="text" name="year" placeholder="year"><br><br>
    email: <input type="text" name="email" placeholder="email"><br><br>
    password: <input type="text" name="password" placeholder="password"><br><br>
    <input type="submit">
</form>
</body>
</html>

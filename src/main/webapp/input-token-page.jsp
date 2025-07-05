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
</head>
<body>
<form method="post" action="/reset-password">
    <H1>Update password</H1><br>
    <input type="text" name="resetToken"><br><br>
    <input type="submit">
</form>
<form method="put" action="">
    <input type="submit" value="Send again">
</form>
</body>
</html>

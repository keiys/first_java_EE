<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 17.07.2025
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Address</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
    <div class="logo">ADDRESS</div>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        response.getWriter().println(errorMessage);
    }
%>

<form method="post" action="/add-address">

    <div class="mb-3">
        <label class="form-label">Country</label>
        <input type="text" name="country" placeholder="country">
    </div>
    <div class="mb-3">
        <label class="form-label">State/region/province</label>
        <input type="text" name="state-region-province" placeholder="state/region/province">
    </div>
    <div class="mb-3">
        <label class="form-label">City</label>
        <input type="text" name="city" placeholder="city">
    </div>
    <div class="mb-3">
        <label class="form-label">Street</label>
        <input type="text" name="address" placeholder="address">
    </div>
    <button type="submit" class="btn-login">ADD ADDRESS</button>
</form>
</div>
</body>
</html>

<%@ page import="com.digi.model.User" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.digi.model.Address" %>
<%@ page import="com.digi.service.impl.AddressServiceImpl" %>
<%@ page import="com.digi.repository.impl.AddressRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 17.07.2025
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="css/home.css">

</head>
<body>
<h1>HOME PAGE</h1>
<div class="container">
    <div class="profile-img">
        <img src="images/userImage.png" alt="User Photo">
    </div>
    <div class="profile-info">
        <%

            User user = (User) session.getAttribute("user");
            String firstName = user.getName();
            String lastName = user.getSurname();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            Integer age = currentYear - user.getYear();

            AddressRepositoryImpl repository = new AddressRepositoryImpl();
            Address address = repository.getAddressById(user.getId());
            String country = address.getCountry();
            String city = address.getCity();
            String region = address.getRegion();
            String street = address.getStreet();
        %>

        <% if (firstName != null) { %>
        <p><strong>Name:</strong> <%= firstName %> <%= lastName %></p>
        <% } %>
        <p><strong>Age:</strong> <%= age %> y.o</p>
        <% if (country != null) { %>
        <p><strong>Country:</strong> <%= country %></p>
        <% } %>
        <% if (city != null) { %>
        <p><strong>City:</strong> <%= city %></p>
        <% } %>
        <% if (region != null) { %>
        <p><strong>State:</strong> <%= region %></p>
        <% } %>
        <% if (street != null) { %>
        <p><strong>Address:</strong> <%= street %></p>
        <% } %>
        <div class="buttons">
            <form action="/home-page" method="get">
                <button type="submit" class="btn-action">Change Password</button>
            </form>
            <form action="/home-page" method="post">
                <button type="submit" class="btn-action">Add Address</button>
            </form>
            <form action="/edit-profile" method="get">
                <button type="submit" class="btn-action">Edit Profile</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>

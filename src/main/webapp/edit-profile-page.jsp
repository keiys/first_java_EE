<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/edit-profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="edit-container">
    <div class="edit-box">
        <div class="edit-title">EDIT PROFILE</div>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-msg"><%= errorMessage %></div>
        <%
            }
        %>

        <form method="post" action="/edit-profile">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" class="form-control" name="name" placeholder="name">
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Surname</label>
                    <input type="text" class="form-control" name="surname" placeholder="surname">
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Year</label>
                    <input type="text" class="form-control" name="year" placeholder="year">
                </div>
            </div><br>

            <div class="divider"></div><br>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Country</label>
                    <input type="text" class="form-control" name="country" placeholder="country">
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">State / Region / Province</label>
                    <input type="text" class="form-control" name="state-region-province" placeholder="state/region/province">
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">City</label>
                    <input type="text" class="form-control" name="city" placeholder="city">
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Street</label>
                    <input type="text" class="form-control" name="address" placeholder="address">
                </div>
            </div>

            <button type="submit" class="btn-login">EDIT</button>
        </form>
    </div>
</div>

</body>
</html>

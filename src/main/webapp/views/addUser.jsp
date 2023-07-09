<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h1>Add User</h1>
<form action="/admin/addUser" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Add User</button>
</form>
</body>
</html>

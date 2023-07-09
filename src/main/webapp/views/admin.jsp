<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin role</title>
</head>
<body>
<h1>Admin role</h1>
<hr>
<h2>Users</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Enabled</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.enabled}</td>
            <td><a href="${pageContext.request.contextPath}/admin/delete/${user.username}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<h3><a href="${pageContext.request.contextPath}/admin/add">Add user</a> </h3>
<c:if test="${not empty message}">
    <div class="message">${message}</div>
</c:if>

</body>
</html>
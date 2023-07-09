<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<head>
    <title>Hello</title>
</head>
<body>
<h1>Chỉ những ai đăng nhập mới truy cập được trang này!</h1>
<h2>Hello ${username}</h2>
<%--<a href="/logout">Logout</a>--%>
<%--<br>--%>
<form action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Sign out</button>
</form>

<%--<c:if test="${role == 'admin'}">--%>
<%--    <h4>You are admin</h4>--%>
<%--</c:if>--%>

<a href="/user">User page</a>
<br>

<sec:authorize access="hasRole('ADMIN')">
    <a href="/admin">Admin page</a>
</sec:authorize>

</body>
</html>
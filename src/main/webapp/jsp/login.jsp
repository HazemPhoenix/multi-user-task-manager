<%--
  Created by IntelliJ IDEA.
  User: hazem
  Date: 12/3/2025
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div>
        <label for="email">Email: </label>
        <input type="email" name="email" id="email">
    </div>
    <div>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password">
    </div>
    <button type="submit">Login</button>
</form>
</body>
</html>

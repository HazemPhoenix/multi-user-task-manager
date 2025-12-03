<%--
  Created by IntelliJ IDEA.
  User: hazem
  Date: 12/3/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Add a Task</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/tasks/newTask">
    <div>
        <label for="name">Task title: </label>
        <input type="text" name="name" id="name">
    </div>
    <div>
        <label for="isDone">Completed? </label>
        <input type="checkbox" name="isDone" id="isDone">
    </div>
    <button type="submit">Add Task</button>
</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hazem
  Date: 12/3/2025
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Task</title>
</head>
<body>
<jsp:useBean id="task" class="com.hazem.model.Task" />
<jsp:setProperty name="task" property="*"/>
<c:set var="isDone" value="" />
<form method="post" action="${pageContext.request.contextPath}/tasks/${task.id}">
    <input type="hidden" name="_method" value="PUT" />
    <input type="hidden" name="id" value="${task.id}" />
    <div>
        <label for="name">Task title: </label>
        <input type="text" name="name" id="name" value="${task.name}">
    </div>
    <div>
        <label for="isDone">Completed? </label>
        <c:choose>
            <c:when test="${pageContext.request.getParameter('isDone')}">
                <input type="checkbox" name="isDone" id="isDone" checked>
            </c:when>
            <c:otherwise>
                <input type="checkbox" name="isDone" id="isDone">
            </c:otherwise>
        </c:choose>
    </div>
    <button type="submit">Update</button>
</form>
</body>
</html>

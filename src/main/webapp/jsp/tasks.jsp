<%@ page import="com.hazem.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hazem.model.User" %><%--
  Created by IntelliJ IDEA.
  User: hazem
  Date: 12/3/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tasks</title>
    <style>
        #task-container {
            display: flex;
            flex-direction: column;
            width: 50%;
            margin: 1em auto;
        }
        .task-card {
            box-shadow: 0 0 6px 5px rgba(0, 0, 0, 0.1);
            padding: 0 1em;
            border-radius: 0.5em;
        }

        a {
            color: lightgray;
            background-color: #292929;
            text-decoration: none;
            padding: 0.5em 1em;
            border: 1px solid lightgray;
            border-radius: 0.5em;
            transition: background-color 300ms;
        }
        a:hover {
            background-color: black;
        }
        a:visited {
            color: lightgray;
        }

    </style>
</head>
<body>
<div style="display: flex; justify-content: center; align-content: center">
    <a href="${pageContext.request.contextPath}/jsp/add_task.jsp">Add Task</a>
</div>
<div id="task-container">
    <c:forEach items="${tasks}" var="task">
        <div class="task-card">
            <h2 style="font-weight: bold; text-align: center">${task.getName()}</h2>

            <c:choose>
                <c:when test="${task.isDone()}">
                    <p>Status: <span style="color: green;">Complete</span></p>
                </c:when>
                <c:otherwise>
                    <p>Status: <span style="color: red;">Pending</span></p>
                </c:otherwise>
            </c:choose>
            <form method="post" action="${pageContext.request.contextPath}/tasks/${task.getId()}">
                <input type="hidden" name="_method" value="DELETE"/>
                <button type="submit">Delete</button>
            </form>
        </div>
        <hr>
    </c:forEach>
</div>

</body>
</html>

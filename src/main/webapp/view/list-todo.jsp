<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.TodoModel" %>
<html>
<head>
    <title>Todo List</title>
    <%--    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">--%>
    <style>
        <%@ include file="../assets/css/styles.css"%>
    </style>
</head>
<body>
<h1>Todo List</h1>
<hr/>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Completed</th>
        <th>Actions</th>
    </tr>
    <%
        List<TodoModel> todos = (List<TodoModel>) request.getAttribute("todos");
        if (todos != null) {
            int i = 1;
            for (TodoModel todo : todos) {
    %>
    <tr>
        <td><%= i %>
        </td>
        <td><%= todo.getTitle() %>
        </td>
        <td><%= todo.getDescription() %>
        </td>
        <td><%= todo.getCompleted() ? "Yes" : "No" %>
        </td>
        <td>
            <a href="DeleteTodoServlet?id=<%= todo.getId() %>">Delete</a>
        </td>
    </tr>
    <% i++;
    }
    } else {
    %>
    <tr>
        <td colspan="5">No todos found.</td>
    </tr>
    <%
        }
    %>
</table>
<hr/>
<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
</body>
</html>
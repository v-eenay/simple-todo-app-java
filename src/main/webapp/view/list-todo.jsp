<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.TodoModel" %>
<html>
<head>
    <title>Todo List</title>
    <%--    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">--%>
    <style>
        <%@ include file="../assets/css/styles.css"%>
        
        /* Additional retro styling */
        body {
            font-family: 'Courier New', monospace;
            background-color: #f5f5dc;
            color: #333;
            max-width: 800px;
            margin: 0 auto;
            padding: 40px 20px;
        }
        
        h1 {
            text-align: center;
            color: #333;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
            font-weight: bold;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            border: 2px solid #333;
            margin: 20px 0;
        }
        
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #333;
        }
        
        th {
            background-color: #d3d3d3;
            font-weight: bold;
        }
        
        tr:nth-child(even) {
            background-color: #eee8cd;
        }
        
        a {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 5px;
            background-color: #d3d3d3;
            color: #333;
            border: 1px solid #333;
            text-decoration: none;
            font-weight: bold;
        }
        
        a:hover {
            background-color: #333;
            color: #f5f5dc;
        }
        
        .actions-cell {
            text-align: center;
        }
        
        .home-link {
            text-align: center;
            margin-top: 20px;
        }
        
        .home-link a {
            padding: 8px 16px;
        }
    </style>
</head>
<body>
<h1>Todo List</h1>
<table>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Description</th>
        <th>Completed</th>
        <th>Actions</th>
    </tr>
    <%
        List<TodoModel> todos = (List<TodoModel>) request.getAttribute("todos");
        if (todos != null && !todos.isEmpty()) {
            int i = 1;
            for (TodoModel todo : todos) {
    %>
    <tr>
        <td><%= i %></td>
        <td><%= todo.getTitle() %></td>
        <td><%= todo.getDescription() %></td>
        <td><%= todo.getCompleted() ? "Yes" : "No" %></td>
        <td class="actions-cell">
            <a href="EditTodoServlet?id=<%= todo.getId() %>">Edit</a>
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
<div class="home-link">
    <a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a>
</div>
</body>
</html>
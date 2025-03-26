<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.TodoModel" %>
<html>
<head>
    <title>Edit Todo</title>
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
        
        form {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #eee8cd;
            border: 2px solid #333;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        
        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #333;
            background-color: #f5f5dc;
            font-family: 'Courier New', monospace;
        }
        
        .checkbox-group {
            margin-bottom: 20px;
        }
        
        input[type="submit"] {
            background-color: #d3d3d3;
            color: #333;
            border: 1px solid #333;
            padding: 10px 20px;
            cursor: pointer;
            font-weight: bold;
            font-family: 'Courier New', monospace;
            display: block;
            margin: 0 auto;
        }
        
        input[type="submit"]:hover {
            background-color: #333;
            color: #f5f5dc;
        }
        
        .home-link {
            text-align: center;
            margin-top: 20px;
        }
        
        .home-link a {
            display: inline-block;
            padding: 8px 16px;
            background-color: #d3d3d3;
            color: #333;
            border: 1px solid #333;
            text-decoration: none;
            font-weight: bold;
        }
        
        .home-link a:hover {
            background-color: #333;
            color: #f5f5dc;
        }
    </style>
</head>
<body>
<h1>Edit Todo</h1>
<%
    TodoModel todo = (TodoModel) request.getAttribute("todo");
    if (todo != null) {
%>
<form action="${pageContext.request.contextPath}/EditTodoServlet" method="post">
    <input type="hidden" name="id" value="<%= todo.getId() %>">
    
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="<%= todo.getTitle() %>" required>
    
    <label for="description">Description:</label>
    <textarea id="description" name="description" required><%= todo.getDescription() %></textarea>
    
    <div class="checkbox-group">
        <label for="completed">Completed:</label>
        <input type="checkbox" id="completed" name="completed" <%= todo.getCompleted() ? "checked" : "" %>>
    </div>
    
    <input type="submit" value="Update Todo">
</form>
<%
    } else {
%>
<p>Todo not found.</p>
<%
    }
%>
<div class="home-link">
    <a href="${pageContext.request.contextPath}/ListTodoServlet">Back to Todo List</a>
</div>
</body>
</html> 
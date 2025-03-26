<%@ page import="model.UserModel" %>
<%@ page import="model.TodoModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Welcome, <%= ((model.UserModel) session.getAttribute("user")).getUsername() %>!</h2>
        <form action="${pageContext.request.contextPath}/logout" method="post" class="d-inline">
            <button type="submit" class="btn btn-danger">Logout</button>
        </form>
    </div>

    <div class="list-group">
        <%
            List<TodoModel> todos = (List<TodoModel>) request.getAttribute("todos");
            if (todos != null && !todos.isEmpty()) {
                for (TodoModel todoObj : todos) {
        %>
        <div class="list-group-item">
            <div>
                <h5 class="mb-1"><%= todoObj.getTitle() %></h5>
                <p class="mb-1"><%= todoObj.getDescription() %></p>
                <small class="text-muted">
                    Status: <%= todoObj.isCompleted() ? "Completed" : "Pending" %>
                </small>
            </div>
            <div>
                <a href="edit-todo?id=<%= todoObj.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="delete-todo?id=<%= todoObj.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p class="text-muted">No tasks found.</p>
        <% } %>
    </div>

    <div class="d-flex justify-content-between mt-3">
        <a href="${pageContext.request.contextPath}/view/add-todo.jsp" class="btn btn-primary">Add New Task</a>
        <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-secondary">Back to Home</a>
    </div>
</div>
</body>
</html>
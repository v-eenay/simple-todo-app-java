<%@ page import="model.UserModel" %>
<%@ page import="model.TodoModel" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CategoryDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Todo List</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>✨ Welcome, <%= ((model.UserModel) session.getAttribute("user")).getUsername() %>! 🌟</h2>
        <div class="d-flex gap-2">
            <button class="theme-toggle" onclick="toggleTheme()">
                <span class="theme-toggle-icon">🌙</span>
            </button>
            <form action="${pageContext.request.contextPath}/logout" method="get" class="d-inline">
                <button type="submit" class="btn btn-danger">
                    <i class="bi bi-box-arrow-right me-2"></i> Logout
                </button>
            </form>
        </div>
    </div>

    <div class="list-group">
        <%
            List<TodoModel> todos = (List<TodoModel>) request.getAttribute("todos");
            if (todos != null && !todos.isEmpty()) {
                for (TodoModel todoObj : todos) {
        %>
        <div class="list-group-item <%= todoObj.isCompleted() ? "completed-task" : "" %>" style="background-color: <%= CategoryDAO.getCategoryByUserId(todoObj.getCategoryId()).getColour() %>20;">
            <div>
                <h5 class="mb-1">
                    <%= todoObj.isCompleted() ? "✅" : "📝" %> <%= todoObj.getTitle() %>
                </h5>
                <p class="mb-1"><%= todoObj.getDescription() %></p>
                <small class="task-date">
                    Category: <span class="badge" style="background-color: <%= CategoryDAO.getCategoryByUserId(todoObj.getCategoryId()).getColour() %>"><%= CategoryDAO.getCategoryByUserId(todoObj.getCategoryId()).getCategoryName() %></span><br>
                    Status: <%= todoObj.isCompleted() ? "Completed 🎉" : "Pending ⏳" %>
                </small>
            </div>
            <div class="task-actions">
                <a href="toggle-todo?id=<%=todoObj.getId()%>&userid=<%=((model.UserModel) session.getAttribute("user")).getId()%>&completed=<%=todoObj.isCompleted()%>"
                   class="btn btn-success btn-sm me-2">
                    <i class="bi <%= todoObj.isCompleted() ? "bi-arrow-counterclockwise" : "bi-check-circle" %>"></i> <%= todoObj.isCompleted() ? "Mark Pending 🔄" : "Mark Done ✨" %>
                </a>
                <a href="edit-todo?id=<%= todoObj.getId() %>" class="btn btn-warning btn-sm me-2">
                    <i class="bi bi-pencil-square me-1"></i> Edit
                </a>
                <a href="delete-todo?id=<%= todoObj.getId() %>" class="btn btn-danger btn-sm">
                    <i class="bi bi-trash me-1"></i> Delete
                </a>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="empty-state">
            <h3>🌱 No tasks yet!</h3>
            <p>Time to plant some productivity seeds! Add your first task below. 🚀</p>
        </div>
        <% } %>
    </div>

    <div class="d-flex justify-content-between mt-4">
        <div>
            <a href="${pageContext.request.contextPath}/view/add-todo.jsp" class="btn btn-outline-primary me-2">
                <i class="bi bi-plus-circle me-2"></i> ✨ Add New Adventure
            </a>
            <a href="${pageContext.request.contextPath}/manage-categories" class="btn btn-outline-info">
                <i class="bi bi-tags me-2"></i> 🎨 Manage Categories
            </a>
        </div>
        <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-outline-secondary">
            <i class="bi bi-house-door me-2"></i> 🏠 Back to Base
        </a>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>

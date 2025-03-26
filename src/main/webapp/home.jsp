<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="view/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo Application</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
</head>
<body>
<div class="container">
    <button class="theme-toggle" onclick="toggleTheme()">
        <span class="theme-toggle-icon">🌙</span>
    </button>
    <div class="text-center mb-5">
        <h1>✨ Todo Application ✨</h1>
        <p class="lead">Your fun and friendly task management companion! 🚀</p>
    </div>
    <div class="card mb-4">
        <div class="card-body text-center">
            <h3>What would you like to do today? 🤔</h3>
            <div class="d-flex justify-content-center gap-3 mt-4">
                <a href="${pageContext.request.contextPath}/view/add-todo.jsp" class="btn btn-outline-primary">
                    <i class="bi bi-plus-circle me-2"></i>Create New Todo
                </a>
                <a href="${pageContext.request.contextPath}/ListTodoServlet" class="btn btn-outline-primary">
                    <i class="bi bi-list-check me-2"></i>View All Todos
                </a>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <h4>✨ Features</h4>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">🎨 Beautiful Dark/Light Theme</li>
                <li class="list-group-item">📝 Easy Task Management</li>
                <li class="list-group-item">🔔 Fun Notifications</li>
                <li class="list-group-item">🎯 Task Progress Tracking</li>
                <li class="list-group-item">🚀 Smooth Animations</li>
            </ul>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
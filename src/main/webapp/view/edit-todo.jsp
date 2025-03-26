<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ page import="model.TodoModel" %>
<!DOCTYPE html>
<html data-bs-theme="light">
<head>
    <title>Edit Todo</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
</head>
<body>
<div class="container mt-5">
    <div class="position-fixed top-0 end-0 p-3">
        <button class="btn btn-outline-primary" onclick="toggleTheme()">
            <i class="bi bi-moon-stars theme-toggle-icon"></i>
        </button>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h3>Edit Todo</h3>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/edit-todo" method="post">
                        <% TodoModel todo = (TodoModel) request.getAttribute("todo"); %>
                        <input type="hidden" name="id" value="<%= todo.getId() %>">
                        <div class="mb-3">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="title" name="title"
                                   value="<%= todo.getTitle() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description"
                                      rows="3"><%= todo.getDescription() %></textarea>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="completed"
                                   name="completed" <%= todo.isCompleted() ? "checked" : "" %>>
                            <label class="form-check-label" for="completed">Completed</label>
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/todos" class="btn btn-outline-secondary">
    <i class="bi bi-arrow-left me-2"></i>🔙 Back to My Tasks
</a>
                            <button type="submit" class="btn btn-primary w-100">
    <i class="bi bi-check2-circle me-2"></i>✨ Make it Awesome! 🎨
</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ page import="model.TodoModel" %>
<%@ page import="model.UserModel" %>
<%@ page import="model.CategoryModel" %>
<%@ page import="model.CategoryDAO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Todo</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <style>
        /* Custom styles for edit todo form */
        .form-control {
            border: 1px solid var(--border-color);
            border-radius: 0;
            padding: 0.5rem;
            margin-bottom: 1rem;
        }
        
        .form-label {
            font-weight: bold;
            text-transform: uppercase;
        }
        
        .card {
            border: 1px solid var(--border-color);
            border-radius: 0;
            margin-top: 2rem;
        }
        
        .card-header {
            background-color: var(--bg-color);
            border-bottom: 1px solid var(--border-color);
            padding: 1rem;
        }
        
        .card-header h3 {
            margin: 0;
            text-transform: uppercase;
        }
        
        .card-body {
            padding: 1.5rem;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>EDIT TASK</h2>
        <button class="theme-toggle" onclick="toggleTheme()">
            <span class="theme-toggle-icon">🌙</span>
        </button>
    </div>
    <div class="card">
        <div class="card-header">
            <h3>TASK DETAILS</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/edit-todo" method="post">
                <% TodoModel todo = (TodoModel) request.getAttribute("todo"); %>
                <input type="hidden" name="id" value="<%= todo.getId() %>">
                <div class="mb-3">
                    <label for="title" class="form-label">TITLE</label>
                    <input type="text" class="form-control" id="title" name="title"
                           value="<%= todo.getTitle() %>" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">DESCRIPTION</label>
                    <textarea class="form-control" id="description" name="description"
                              rows="3"><%= todo.getDescription() %></textarea>
                </div>
                <div class="mb-3">
                    <label for="categoryId" class="form-label">CATEGORY</label>
                    <select class="form-control" id="categoryId" name="categoryId" required>
                        <% 
                            List<CategoryModel> categories = CategoryDAO.getCategoryByUserId(((UserModel)session.getAttribute("user")).getId());
                            for(CategoryModel category : categories) {
                        %>
                            <option value="<%= category.getId() %>" <%= todo.getCategoryId() == category.getId() ? "selected" : "" %>><%= category.getCategoryName().toUpperCase() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="completed"
                           name="completed" <%= todo.isCompleted() ? "checked" : "" %>>
                    <label class="form-check-label" for="completed">COMPLETED</label>
                </div>
                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/list-todo" class="btn btn-secondary">
                        CANCEL
                    </a>
                    <button type="submit" class="btn btn-primary">
                        UPDATE TASK
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
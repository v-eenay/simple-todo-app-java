<%@ page import="model.UserModel" %>
<%@ page import="model.CategoryModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Categories</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <style>
        .color-preview {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            display: inline-block;
            margin-right: 10px;
            border: 2px solid var(--border-color);
        }
        .category-list {
            margin-top: 2rem;
        }
        .category-item {
            display: flex;
            align-items: center;
            padding: 1rem;
            margin-bottom: 1rem;
            border-radius: 8px;
            background: var(--bg-secondary);
            border: 1px solid var(--border-color);
        }
        .category-name {
            flex-grow: 1;
            margin-left: 1rem;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>🎨 Manage Categories</h2>
        <div class="d-flex gap-2">
            <button class="theme-toggle" onclick="toggleTheme()">
                <span class="theme-toggle-icon">🌙</span>
            </button>
        </div>
    </div>

    <div class="card p-4 mb-4">
        <h4>Add New Category</h4>
        <form action="${pageContext.request.contextPath}/add-category" method="post" class="needs-validation" novalidate>
            <div class="mb-3">
                <label for="categoryName" class="form-label">Category Name</label>
                <input type="text" class="form-control" id="categoryName" name="categoryName" required>
            </div>
            <div class="mb-3">
                <label for="colour" class="form-label">Category Color</label>
                <input type="color" class="form-control form-control-color" id="colour" name="colour" value="#E2E8F0" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Category</button>
        </form>
    </div>

    <div class="category-list">
        <h4>Your Categories</h4>
        <%
            List<CategoryModel> categories = (List<CategoryModel>) request.getAttribute("categories");
            if (categories != null && !categories.isEmpty()) {
                for (CategoryModel category : categories) {
        %>
        <div class="category-item">
            <div class="color-preview" style="background-color: <%= category.getColour() %>"></div>
            <div class="category-name"><%= category.getCategoryName() %></div>
            <a href="delete-category?id=<%= category.getId() %>" class="btn btn-danger btn-sm" 
               onclick="return confirm('Are you sure you want to delete this category? Associated todos will be moved to Default category.')">
                <i class="bi bi-trash"></i>
            </a>
        </div>
        <%
                }
            } else {
        %>
        <div class="empty-state">
            <p>No categories yet! Start by adding one above. 🎨</p>
        </div>
        <% } %>
    </div>

    <div class="d-flex justify-content-between mt-4">
        <a href="${pageContext.request.contextPath}/list-todo" class="btn btn-outline-primary">
            <i class="bi bi-arrow-left me-2"></i> Back to Todos
        </a>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
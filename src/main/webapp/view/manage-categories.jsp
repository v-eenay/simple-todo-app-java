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
        /* Custom styles for category management */
        :root {
            --bg-color: #FFFFFF;
            --border-color: #000000;
        }
        
        .color-preview {
            width: 20px;
            height: 20px;
            display: inline-block;
            margin-right: 10px;
            border: 1px solid var(--border-color);
        }
        
        .category-list {
            margin-top: 2rem;
        }
        
        .category-item {
            display: flex;
            align-items: center;
            padding: 1rem;
            margin-bottom: 1rem;
            background: var(--bg-color);
            border: 1px solid var(--border-color);
        }
        
        .category-name {
            flex-grow: 1;
            margin-left: 1rem;
            font-weight: bold;
            text-transform: uppercase;
        }
        
        .card {
            border: 1px solid var(--border-color);
            border-radius: 0;
            padding: 1.5rem;
            margin-bottom: 2rem;
            background: var(--bg-color);
        }
        
        .form-control {
            border: 1px solid var(--border-color);
            border-radius: 0;
            padding: 0.5rem;
            margin-bottom: 1rem;
            background: var(--bg-color);
        }
        
        .form-label {
            font-weight: bold;
            text-transform: uppercase;
        }
        
        h2, h4 {
            text-transform: uppercase;
            margin-bottom: 1.5rem;
            color: var(--border-color);
        }
        
        body {
            background: var(--bg-color);
            color: var(--border-color);
        }
        
        .btn {
            border-radius: 0;
        }
        
        .btn-primary {
            background: var(--border-color);
            color: var(--bg-color);
            border: 1px solid var(--border-color);
        }
        
        .btn-secondary {
            background: var(--bg-color);
            color: var(--border-color);
            border: 1px solid var(--border-color);
        }
        
        .btn-danger {
            background: var(--border-color);
            color: var(--bg-color);
            border: 1px solid var(--border-color);
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>MANAGE CATEGORIES</h2>
        <div class="d-flex gap-2">
            <button class="theme-toggle" onclick="toggleTheme()">
                <span class="theme-toggle-icon">🌙</span>
            </button>
        </div>
    </div>

    <div class="card">
        <h4>ADD NEW CATEGORY</h4>
        <form action="${pageContext.request.contextPath}/add-category" method="post" class="needs-validation" novalidate>
            <div class="mb-3">
                <label for="categoryName" class="form-label">CATEGORY NAME</label>
                <input type="text" class="form-control" id="categoryName" name="categoryName" required>
            </div>
            <div class="mb-3">
                <label for="categoryColor" class="form-label">COLOR</label>
                <input type="color" class="form-control form-control-color" id="categoryColor" name="colour" value="#000000">
            </div>
            <button type="submit" class="btn btn-primary">ADD CATEGORY</button>
        </form>
    </div>

    <div class="category-list">
        <h4>YOUR CATEGORIES</h4>
        <%
            List<CategoryModel> categories = (List<CategoryModel>) request.getAttribute("categories");
            if(categories != null && !categories.isEmpty()) {
                for(CategoryModel category : categories) {
        %>
        <div class="category-item">
            <div class="color-preview" style="background-color: <%= category.getColour() %>;"></div>
            <div class="category-name"><%= category.getCategoryName().toUpperCase() %></div>
            <a href="${pageContext.request.contextPath}/delete-category?id=<%= category.getId() %>" class="btn btn-danger btn-sm">DELETE</a>
        </div>
        <%
                }
            } else {
        %>
        <div class="empty-state">
            <p>NO CATEGORIES FOUND. CREATE YOUR FIRST CATEGORY ABOVE.</p>
        </div>
        <% } %>
    </div>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/list-todo" class="btn btn-secondary">BACK TO TASKS</a>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
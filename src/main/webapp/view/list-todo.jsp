<%@ page import="model.UserModel" %>
<%@ page import="model.TodoModel" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CategoryDAO"%>
<%@ page import="model.CategoryModel"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Todo List</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <style>
        /* Custom styles for todo items */
        .list-group-item {
            color: var(--text-color);
            border: 1px solid var(--border-color);
        }
        
        /* Style for category badge */
        .category-badge {
            display: inline-block;
            padding: 0.25em 0.5em;
            font-weight: bold;
            border: 1px solid var(--border-color);
        }
        
        /* Light mode - light background, dark text */
        html:not([data-bs-theme="dark"]) .todo-item-custom {
            color: #000000 !important;
        }
        
        /* Dark mode - dark background, light text */
        html[data-bs-theme="dark"] .todo-item-custom {
            color: #ffffff !important;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>WELCOME, <%= ((model.UserModel) session.getAttribute("user")).getUsername().toUpperCase() %></h2>
        <div class="d-flex gap-2">
            <button class="theme-toggle" onclick="toggleTheme()">
                <span class="theme-toggle-icon">🌙</span>
            </button>
            <form action="${pageContext.request.contextPath}/logout" method="get" class="d-inline">
                <button type="submit" class="btn btn-danger">
                    LOGOUT
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
        <%  
            List<CategoryModel> categories = CategoryDAO.getCategoryByUserId(todoObj.getUserId());
            CategoryModel category = null;
            for (CategoryModel cat : categories) {
                if (cat.getId() == todoObj.getCategoryId()) {
                    category = cat;
                    break;
                }
            }
            
            String categoryColor = category != null ? category.getColour() : "#000000";
            String bgColorLight = categoryColor + "20"; // Light shade for light mode
            String bgColorDark = categoryColor + "80";  // Darker shade for dark mode
        %>
        <div class="list-group-item todo-item-custom <%= todoObj.isCompleted() ? "completed-task" : "" %>" 
             style="background-color: var(--bg-color);">
            <div>
                <h5 class="mb-1">
                    <%= todoObj.isCompleted() ? "[DONE]" : "[TODO]" %> <%= todoObj.getTitle().toUpperCase() %>
                </h5>
                <p class="mb-1"><%= todoObj.getDescription() %></p>
                <small>
                    <span class="category-badge" style="background-color: <%= categoryColor %>; color: <%= categoryColor.equals("#000000") ? "#ffffff" : "#000000" %>">
                        <%= category != null ? category.getCategoryName().toUpperCase() : "" %>
                    </span><br>
                    STATUS: <%= todoObj.isCompleted() ? "COMPLETED" : "PENDING" %>
                </small>
            </div>
            <div class="task-actions">
                <a href="toggle-todo?id=<%=todoObj.getId()%>&userid=<%=((model.UserModel) session.getAttribute("user")).getId()%>&completed=<%=todoObj.isCompleted()%>"
                   class="btn btn-success btn-sm me-2">
                    <%= todoObj.isCompleted() ? "UNDO" : "DONE" %>
                </a>
                <a href="edit-todo?id=<%= todoObj.getId() %>" class="btn btn-warning btn-sm me-2">
                    EDIT
                </a>
                <a href="delete-todo?id=<%= todoObj.getId() %>" class="btn btn-danger btn-sm">
                    DELETE
                </a>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="empty-state">
            <h3>NO TASKS YET</h3>
            <p>ADD YOUR FIRST TASK BELOW</p>
        </div>
        <% } %>
    </div>

    <div class="d-flex justify-content-between mt-4">
        <div>
            <a href="${pageContext.request.contextPath}/view/add-todo.jsp" class="btn btn-outline-primary me-2">
                ADD NEW TASK
            </a>
            <a href="${pageContext.request.contextPath}/manage-categories" class="btn btn-outline-info">
                MANAGE CATEGORIES
            </a>
        </div>
        <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-outline-secondary">
            HOME
        </a>
    </div>
</div>

<script>
    // Apply dynamic styling based on theme
    function applyThemeStyles() {
        const isDarkMode = document.documentElement.getAttribute('data-bs-theme') === 'dark';
        const todoItems = document.querySelectorAll('.todo-item-custom');
        
        todoItems.forEach(item => {
            // Get the category color from the badge
            const badge = item.querySelector('.category-badge');
            if (badge) {
                const bgColor = badge.style.backgroundColor;
                
                if (isDarkMode) {
                    // Dark mode: darker background
                    item.style.borderLeft = `5px solid ${bgColor}`;
                } else {
                    // Light mode: lighter background
                    item.style.borderLeft = `5px solid ${bgColor}`;
                }
            }
        });
    }
    
    // Call initially and whenever theme changes
    applyThemeStyles();
    
    // Listen for theme changes
    const observer = new MutationObserver(mutations => {
        mutations.forEach(mutation => {
            if (mutation.attributeName === 'data-bs-theme') {
                applyThemeStyles();
            }
        });
    });
    
    observer.observe(document.documentElement, { attributes: true });
</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>

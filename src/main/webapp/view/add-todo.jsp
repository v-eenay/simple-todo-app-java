<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.CategoryDAO, java.util.Map" %>
<html>
<head>
    <title>Add Todo</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<h1>Add a New Todo</h1>
<hr/>

<% 
    // Get all categories with their colors
    Map<String, String> categories = CategoryDAO.getAllCategoriesWithColors();
%>

<div class="form-container">
    <% if(request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("errorMessage") %>
    </div>
    <% } %>

    <form action="../AddTodoServlet" method="post">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
        </div>
        
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        
        <div class="form-group">
            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <% for (Map.Entry<String, String> entry : categories.entrySet()) { %>
                    <option value="<%= entry.getKey() %>" 
                            style="background-color: <%= entry.getValue() %>20; color: <%= entry.getValue() %>">
                        <%= entry.getKey() %>
                    </option>
                <% } %>
            </select>
        </div>
        
        <div class="form-group">
            <label for="priority">Priority:</label>
            <select id="priority" name="priority" required>
                <option value="1">Low</option>
                <option value="2" selected>Medium</option>
                <option value="3">High</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="dueDate">Due Date:</label>
            <input type="date" id="dueDate" name="dueDate">
        </div>
        
        <div class="form-group">
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="Not Started" selected>Not Started</option>
                <option value="In Progress">In Progress</option>
                <option value="Completed">Completed</option>
            </select>
        </div>
        
        <div class="form-group checkbox-group">
            <label for="completed">
                <input type="checkbox" id="completed" name="completed">
                Mark as Completed
            </label>
        </div>
        
        <div class="form-actions">
            <input type="submit" value="Add Todo">
            <a href="${pageContext.request.contextPath}/ListTodoServlet">Cancel</a>
        </div>
    </form>
</div>

<hr/>
<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
</body>
</html>
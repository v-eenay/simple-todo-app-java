<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="./error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Todo</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
</head>
<body>
<div class="container">
    <button class="theme-toggle" onclick="toggleTheme()">
        <span class="theme-toggle-icon">🌙</span>
    </button>
    <div class="card">
        <div class="card-header">
            <h3>Add Todo</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/add-todo" method="post">
                <div>
                    <label for="title">Title</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label">Category</label>
                <select class="form-control" id="categoryId" name="categoryId" required>
                    <% 
                        List<CategoryModel> categories = CategoryDAO.getCategoriesByUserId(((UserModel)session.getAttribute("user")).getId());
                        for(CategoryModel category : categories) {
                    %>
                        <option value="<%= category.getId() %>"><%= category.getCategoryName() %></option>
                    <% } %>
                </select>
            </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="completed" name="completed">
                            <label class="form-check-label" for="completed">Completed</label>
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/list-todo" class="btn btn-secondary">Cancel</a>
                            <button type="submit" class="btn btn-primary">Add Todo</button>
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
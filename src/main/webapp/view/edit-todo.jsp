<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ page import="model.TodoModel" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Todo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
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
                            <a href="${pageContext.request.contextPath}/todos" class="btn btn-secondary">Cancel</a>
                            <button type="submit" class="btn btn-primary">Update Todo</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
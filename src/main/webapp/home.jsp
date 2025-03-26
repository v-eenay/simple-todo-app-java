<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="view/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Todo Application</h1>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column gap-3">
                        <a href="${pageContext.request.contextPath}/view/add-todo.jsp" class="btn btn-primary">Add a New Todo</a>
                        <a href="${pageContext.request.contextPath}/ListTodoServlet" class="btn btn-primary">View All Todos</a>
                    </div>
                </div>
            </div>
            <div class="text-center mt-4 text-muted">
                A simple retro-styled Todo application
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
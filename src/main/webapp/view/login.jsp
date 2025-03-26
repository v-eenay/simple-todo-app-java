<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Login</h3>
                </div>
                <div class="card-body">
                    <%
                        String error = (String) request.getAttribute("error");
                        if (error != null && !error.isEmpty()) {
                    %>
                    <div class="alert alert-danger"><%= error %></div>
                    <%
                        }
                    %>
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
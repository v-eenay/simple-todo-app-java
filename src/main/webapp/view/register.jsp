<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Todo App</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
</head>
<body>
<div class="container">
    <button class="theme-toggle" onclick="toggleTheme()">
        <span class="theme-toggle-icon">🌙</span>
    </button>
    <div class="card">
        <div class="card-header">
            <h3>Register</h3>
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
            <form action="${pageContext.request.contextPath}/register" method="post">
                <div>
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div>
                    <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
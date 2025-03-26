<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Todo App</title>
    <%@ include file="/WEB-INF/common/header.jsp" %>
</head>
<body>
<div class="container">
    <button class="theme-toggle" onclick="toggleTheme()">
        <span class="theme-toggle-icon">🌙</span>
    </button>
    <div class="card">
        <div class="card-header text-center">
            <h3>🌟 Welcome Back! 🌟</h3>
            <p class="text-muted mb-0">Ready to conquer your tasks? Let's go! 🚀</p>
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
                <div>
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100 login-btn">
    <i class="bi bi-box-arrow-in-right me-2"></i>Let's Get Productive! 🎯
</button>
            </form>
            <p class="text-center mt-3">✨ First time here? <a href="${pageContext.request.contextPath}/register" class="text-decoration-none">Join the productivity party! 🎉</a></p>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
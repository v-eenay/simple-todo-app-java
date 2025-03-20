<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Todo Application</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">
</head>
<body>
<h1>Login to Todo Application</h1>
<hr/>

<div class="form-container login-container">
    <% if(request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("errorMessage") %>
    </div>
    <% } %>

    <% if(session.getAttribute("registrationSuccess") != null) { %>
    <div class="success-message">
        <%= session.getAttribute("registrationSuccess") %>
        <% session.removeAttribute("registrationSuccess"); %>
    </div>
    <% } %>

    <form action="../UserAuthenticationServlet" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-actions">
            <input type="submit" value="Login">
            <a href="${pageContext.request.contextPath}/view/registration.jsp">Register</a>
        </div>
    </form>
</div>

<hr/>
<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
</body>
</html>
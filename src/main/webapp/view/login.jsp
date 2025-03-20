<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Todo Application</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">
    <style>
        .form-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #000;
            background-color: #fff;
        }

        .error-message {
            color: #ff0000;
            margin-bottom: 15px;
            text-align: center;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>Login to Todo Application</h1>
<hr/>

<div class="form-container">
    <% if(request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("errorMessage") %>
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
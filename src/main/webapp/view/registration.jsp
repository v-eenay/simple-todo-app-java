<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - Todo Application</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">
    <style>
        .form-container {
            max-width: 500px;
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

        .password-requirements {
            font-size: 0.8em;
            color: #666;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<h1>Register for Todo Application</h1>
<hr/>

<div class="form-container">
    <% if(request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("errorMessage") %>
    </div>
    <% } %>

    <form action="RegisterUserServlet" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <div class="password-requirements">
                Password must be at least 8 characters long
            </div>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>

        <div class="form-actions">
            <input type="submit" value="Register">
            <a href="${pageContext.request.contextPath}/view/login.jsp">Already have an account? Login</a>
        </div>
    </form>
</div>

<hr/>
<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

<script>
    function validateForm() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password.length < 8) {
            alert('Password must be at least 8 characters long');
            return false;
        }

        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return false;
        }

        return true;
    }
</script>
</body>
</html>
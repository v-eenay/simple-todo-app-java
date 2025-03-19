<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Todo</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/styles.css">
</head>
<body>
<h1>Add a New Todo</h1>
<hr/>
<form action="../AddTodoServlet" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br><br>
    <label for="completed">Completed:</label>
    <input type="checkbox" id="completed" name="completed"><br><br>
    <input type="submit" value="Add Todo">
</form>
<hr/>
<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
</body>
</html>
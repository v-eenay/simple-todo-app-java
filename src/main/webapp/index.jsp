<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Application</title>
    <link rel="stylesheet" type="text/css" href="./assets/css/styles.css">
</head>
<body>
<h1>Welcome to the Todo Application</h1>
<hr/>
<p><a href="${pageContext.request.contextPath}/view/add-todo.jsp">Add a new todo</a></p>
<p><a href="${pageContext.request.contextPath}/ListTodoServlet">View all todos</a></p>
<hr/>
</body>
</html>
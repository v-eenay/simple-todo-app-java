<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Application</title>
    <style>
        <%@ include file="./assets/css/styles.css"%>
        
        /* Additional retro styling */
        body {
            font-family: 'Courier New', monospace;
            background-color: #f5f5dc;
            color: #333;
            max-width: 800px;
            margin: 0 auto;
            padding: 40px 20px;
            text-align: center;
        }
        
        h1 {
            text-align: center;
            color: #333;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
            font-weight: bold;
            margin-bottom: 40px;
        }
        
        .menu-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            max-width: 400px;
            margin: 0 auto;
            padding: 30px;
            background-color: #eee8cd;
            border: 2px solid #333;
        }
        
        .menu-item {
            width: 100%;
        }
        
        .menu-item a {
            display: block;
            padding: 15px;
            background-color: #d3d3d3;
            color: #333;
            border: 1px solid #333;
            text-decoration: none;
            font-weight: bold;
            width: 100%;
            box-sizing: border-box;
            text-align: center;
        }
        
        .menu-item a:hover {
            background-color: #333;
            color: #f5f5dc;
        }
        
        .footer {
            margin-top: 40px;
            font-size: 0.9em;
            color: #666;
        }
    </style>
</head>
<body>
<h1>Todo Application</h1>
<div class="menu-container">
    <div class="menu-item">
        <a href="${pageContext.request.contextPath}/view/add-todo.jsp">Add a New Todo</a>
    </div>
    <div class="menu-item">
        <a href="${pageContext.request.contextPath}/ListTodoServlet">View All Todos</a>
    </div>
</div>
<div class="footer">
    A simple retro-styled Todo application
</div>
</body>
</html>
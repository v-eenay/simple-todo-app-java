<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error - Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Error <%= pageContext.getErrorData().getStatusCode() %></h3>
                </div>
                <div class="card-body text-center">
                    <p class="lead">
                        <%
                            int statusCode = pageContext.getErrorData().getStatusCode();
                            if (statusCode == 404) {
                        %>
                        The page you're looking for doesn't exist.
                        <%
                        } else if (statusCode == 500) {
                        %>
                        Something went wrong on our end. Please try again later.
                        <%
                        } else {
                        %>
                        An unexpected error occurred.
                        <%
                            }
                        %>
                    </p>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Go to Home</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
package controller;

import model.TodoModel;
import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddTodoServlet", urlPatterns = {"/AddTodoServlet", "/add"})
public class AddTodoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = request.getParameter("completed") != null;

        // Create a new TodoModel object
        TodoModel newTodo = new TodoModel(0, title, description, completed);

        try {
            // Add the todo to the database
            int newId = TodoDAO.addTodo(newTodo);
            if (newId != -1) {
                // Redirect to the list page after successful addition
                response.sendRedirect("ListTodoServlet");
            } else {
                // Handle failure (e.g., show an error message)
                request.setAttribute("errorMessage", "Failed to add todo.");
                request.getRequestDispatcher("view/add-todo.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("view/add-todo.jsp").forward(request, response);
        }
    }
}
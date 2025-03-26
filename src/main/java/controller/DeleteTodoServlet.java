package controller;

import model.TodoModel;
import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteTodoServlet", urlPatterns = {"/DeleteTodoServlet", "/delete"})
public class DeleteTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the todo ID from the request parameter
        int id = Integer.parseInt(request.getParameter("id"));

        // Create a TodoModel object with the ID
        TodoModel todoToDelete = new TodoModel(id, "", "", false);

        // Delete the todo from the database
        if (TodoDAO.removeTodo(todoToDelete)) {
            // Redirect to the list page after successful deletion
            response.sendRedirect("ListTodoServlet");
        } else {
            // Handle failure (e.g., show an error message)
            request.setAttribute("errorMessage", "Failed to delete todo.");
            request.getRequestDispatcher("view/list-todo.jsp").forward(request, response);
        }
    }
}
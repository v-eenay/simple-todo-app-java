package controller;

import model.TodoModel;
import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            // User not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            return;
        }
        
        try {
            // Retrieve the todo ID from the request parameter
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Create a TodoModel object with the ID and user ID
            TodoModel todoToDelete = new TodoModel(id, userId, "", "", false, "General", 2, null, "Not Started");
            
            // Delete the todo from the database
            if (TodoDAO.removeTodo(todoToDelete)) {
                // Redirect to the list page after successful deletion
                response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
            } else {
                // Handle failure (e.g., show an error message)
                request.setAttribute("errorMessage", "Failed to delete todo or you don't have permission to delete it.");
                request.getRequestDispatcher("/view/list-todo.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid todo ID.");
            request.getRequestDispatcher("/ListTodoServlet").forward(request, response);
        }
    }
}
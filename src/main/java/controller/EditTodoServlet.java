package controller;

import model.TodoModel;
import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditTodoServlet", urlPatterns = {"/EditTodoServlet", "/edit"})
public class EditTodoServlet extends HttpServlet {
    
    // Handle GET requests - display the edit form with current todo details
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID from the request parameter
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                // Fetch the todo from the database
                TodoModel todo = TodoDAO.getTodoById(id);
                
                if (todo != null) {
                    // Set the todo as a request attribute
                    request.setAttribute("todo", todo);
                    // Forward to the edit form
                    request.getRequestDispatcher("view/edit-todo.jsp").forward(request, response);
                } else {
                    // Todo not found, redirect to list
                    response.sendRedirect("ListTodoServlet");
                }
            } catch (NumberFormatException e) {
                // Invalid ID, redirect to list
                response.sendRedirect("ListTodoServlet");
            }
        } else {
            // No ID provided, redirect to list
            response.sendRedirect("ListTodoServlet");
        }
    }
    
    // Handle POST requests - process the form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form data
        String idParam = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = request.getParameter("completed") != null;
        
        try {
            int id = Integer.parseInt(idParam);
            // Create a TodoModel with the updated data
            TodoModel updatedTodo = new TodoModel(id, title, description, completed);
            
            // Update the todo in the database
            if (TodoDAO.updateTodo(updatedTodo)) {
                // Redirect to the list page after successful update
                response.sendRedirect("ListTodoServlet");
            } else {
                // Handle update failure
                request.setAttribute("errorMessage", "Failed to update todo.");
                request.setAttribute("todo", updatedTodo);
                request.getRequestDispatcher("view/edit-todo.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Invalid ID, redirect to list
            response.sendRedirect("ListTodoServlet");
        }
    }
} 
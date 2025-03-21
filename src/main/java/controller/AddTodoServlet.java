package controller;

import model.TodoModel;
import model.TodoDAO;
import model.CategoryDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/AddTodoServlet")
public class AddTodoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            // User not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            return;
        }
        
        // Get all categories with their colors for the dropdown
        Map<String, String> categories = CategoryDAO.getAllCategoriesWithColors();
        request.setAttribute("categories", categories);
        
        // Forward to the add-todo.jsp page
        request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            // User not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            return;
        }
        
        try {
            // Retrieve form data
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            boolean completed = request.getParameter("completed") != null;
            String category = request.getParameter("category");
            int priority = Integer.parseInt(request.getParameter("priority"));
            String status = request.getParameter("status");
            
            // Parse due date if provided
            Date dueDate = null;
            String dueDateStr = request.getParameter("dueDate");
            if (dueDateStr != null && !dueDateStr.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dueDate = dateFormat.parse(dueDateStr);
                } catch (ParseException e) {
                    request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD.");
                    request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
                    return;
                }
            }
            
            // Create a new TodoModel object
            TodoModel newTodo = new TodoModel(userId, title, description, completed, category, priority, dueDate, status);
            
            // Add the todo to the database
            int newId = TodoDAO.addTodo(newTodo);
            if (newId != -1) {
                // Redirect to the list page after successful addition
                response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
            } else {
                // Handle failure (e.g., show an error message)
                request.setAttribute("errorMessage", "Failed to add todo.");
                request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input format.");
            request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
        }
    }
}
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
import java.util.List;
import java.util.Map;

@WebServlet("/ListTodoServlet")
public class ListTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            // User not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            return;
        }
        
        // Get filter parameters
        String categoryFilter = request.getParameter("category");
        String priorityFilter = request.getParameter("priority");
        String statusFilter = request.getParameter("status");
        String sortBy = request.getParameter("sortBy");
        
        // Retrieve todos based on filters
        List<TodoModel> todos;
        
        if (categoryFilter != null && !categoryFilter.isEmpty() && !categoryFilter.equals("all")) {
            todos = TodoDAO.filterTodosByCategory(userId, categoryFilter);
        } else if (priorityFilter != null && !priorityFilter.isEmpty() && !priorityFilter.equals("all")) {
            todos = TodoDAO.filterTodosByPriority(userId, Integer.parseInt(priorityFilter));
        } else if (statusFilter != null && !statusFilter.isEmpty() && !statusFilter.equals("all")) {
            todos = TodoDAO.filterTodosByStatus(userId, statusFilter);
        } else {
            todos = TodoDAO.listTodosByUser(userId);
        }
        
        // Get all categories with their colors for the filter dropdown
        Map<String, String> categories = CategoryDAO.getAllCategoriesWithColors();
        
        // Set attributes for the JSP
        request.setAttribute("todos", todos);
        request.setAttribute("categories", categories);
        request.setAttribute("categoryFilter", categoryFilter);
        request.setAttribute("priorityFilter", priorityFilter);
        request.setAttribute("statusFilter", statusFilter);
        request.setAttribute("sortBy", sortBy);
        
        // Forward to the list-todo.jsp page
        request.getRequestDispatcher("view/list-todo.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests (e.g., for applying filters)
        doGet(request, response);
    }
}
package controller;

import model.CategoryDAO;
import model.CategoryModel;
import model.TodoModel;
import model.UserModel;
import service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet({"/todos", "/list-todo"})
public class ListTodoServlet extends HttpServlet {
    private final TodoService todoService;

    public ListTodoServlet() {
        this.todoService = new TodoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        
        // Get sorting and filtering parameters
        String sortBy = request.getParameter("sort");
        String filterStatus = request.getParameter("status");
        String categoryParam = request.getParameter("category");
        Integer filterCategory = null;
        
        if (categoryParam != null && !categoryParam.isEmpty()) {
            try {
                filterCategory = Integer.parseInt(categoryParam);
            } catch (NumberFormatException e) {
                // Ignore invalid category parameter
            }
        }
        
        // Get categories for filter dropdown
        List<CategoryModel> categories = null;
        try {
            categories = CategoryDAO.getCategoryByUserId(user.getId());
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            // Handle exception
        }
        
        // Store current filter/sort settings for form
        request.setAttribute("currentSort", sortBy != null ? sortBy : "date_desc");
        request.setAttribute("currentStatus", filterStatus != null ? filterStatus : "all");
        request.setAttribute("currentCategory", filterCategory != null ? filterCategory : 0);
        
        List<TodoModel> todos = null;
        try {
            todos = todoService.getAllTodos(user.getId(), sortBy, filterStatus, filterCategory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("/view/list-todo.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle form submission for filtering/sorting
        doGet(request, response);
    }
}
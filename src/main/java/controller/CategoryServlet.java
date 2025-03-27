package controller;

import model.CategoryDAO;
import model.CategoryModel;
import model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/manage-categories", "/add-category", "/delete-category"})
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        switch (action) {
            case "/manage-categories":
                List<CategoryModel> categories = CategoryDAO.getCategoryByUserId(user.getId());
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/view/manage-categories.jsp").forward(request, response);
                break;
            case "/delete-category":
                int categoryId = Integer.parseInt(request.getParameter("id"));
                boolean deleted = CategoryDAO.deleteCategory(categoryId);
                if (deleted) {
                    response.sendRedirect("manage-categories");
                }
                break;
            default:
                response.sendRedirect("list-todo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("/add-category".equals(action)) {
            String categoryName = request.getParameter("categoryName");
            String colour = request.getParameter("colour");
            CategoryModel category = new CategoryModel(categoryName, colour, user.getId());
            CategoryDAO.addCategory(category);
            response.sendRedirect("manage-categories");
        } else {
            response.sendRedirect("list-todo");
        }
    }
}
package controller;

import model.UserModel;
import model.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserAuthenticationServlet")
public class UserAuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // User is already logged in, redirect to home
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // If not logged in, forward to login page
        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Basic validation
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        UserModel user = UserDAO.validateUser(username, password);

        if (user != null) {
            // Authentication successful
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());

            // Redirect to home page
            response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
        } else {
            // Authentication failed
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }

    // Logout method that can be called from a servlet path
    @WebServlet("/LogoutServlet")
    public static class LogoutServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        }
    }
}
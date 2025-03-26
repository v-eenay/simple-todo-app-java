package controller;

import model.UserModel;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService;

    public RegisterServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
            return;
        }

        try {
            if (userService.usernameExists(username)) {
                request.setAttribute("error", "Username already exists");
                request.getRequestDispatcher("/view/register.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (userService.emailExists(email)) {
                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("/view/register.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UserModel user = new UserModel(username, email, password);
        try {
            userService.registerUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/todos");
    }
} 
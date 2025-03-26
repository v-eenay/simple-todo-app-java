package controller;

import model.UserModel;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/todos");
            return;
        }

        // Check for remember me cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    String[] credentials = cookie.getValue().split(":");
                    if (credentials.length == 2) {
                        String username = credentials[0];
                        String password = credentials[1];
                        UserModel user = null;
                        try {
                            user = userService.authenticateUser(username, password);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        if (user != null) {
                            session = request.getSession();
                            session.setAttribute("user", user);
                            response.sendRedirect(request.getContextPath() + "/todos");
                            return;
                        }
                    }
                }
            }
        }
        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/todos");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        UserModel user = null;
        try {
            user = userService.authenticateUser(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null) {
            // Create session
            session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            // Handle remember me
            if ("on".equals(rememberMe)) {
                Cookie rememberMeCookie = new Cookie("rememberMe", username + ":" + password);
                rememberMeCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                rememberMeCookie.setPath("/");
                response.addCookie(rememberMeCookie);
            }

            response.sendRedirect(request.getContextPath() + "/todos");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }
} 
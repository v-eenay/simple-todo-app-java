package controller;

import model.UserModel;
import model.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to registration page
        request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate form data
        if (username == null || username.trim().isEmpty() ||
                firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            request.setAttribute("errorMessage", "All fields are required");
            request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
            return;
        }

        // Validate password
        if (password.length() < 8) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long");
            request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
            return;
        }

        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errorMessage", "Invalid email format");
            request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
            return;
        }

        // Create new user
        UserModel newUser = new UserModel(username, firstName, lastName, email, password);

        // Add user to database
        int userId = UserDAO.addUser(newUser);

        if (userId > 0) {
            // Registration successful, redirect to login page with success message
            request.getSession().setAttribute("registrationSuccess", "Registration successful! Please login.");
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        } else {
            // Registration failed
            request.setAttribute("errorMessage", "Registration failed. Username may already be taken.");
            request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
        }
    }
}
package controller;

import model.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.JsonObject;

@WebServlet("/toggle-todo")
public class ToggleTodoServlet extends HttpServlet {
    private TodoDAO todoDAO;

    public void init() {
        todoDAO = new TodoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userid"));
        boolean completed = Boolean.parseBoolean(request.getParameter("completed"));
        try {
            todoDAO.toggleCompleted(id, userId, completed);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/todos");

    }
}
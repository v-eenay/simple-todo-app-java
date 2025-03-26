package controller;

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

@WebServlet("/add-todo")
public class AddTodoServlet extends HttpServlet {
    private final TodoService todoService;

    public AddTodoServlet() {
        this.todoService = new TodoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = "on".equals(request.getParameter("completed"));

        TodoModel todo = new TodoModel(0, title, description, completed, user.getId());
        try {
            todoService.addTodo(todo);
            response.sendRedirect(request.getContextPath() + "/todos");
        } catch (Exception e) {
            request.setAttribute("error", "Failed to add todo: " + e.getMessage());
            request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
        }
    }
}
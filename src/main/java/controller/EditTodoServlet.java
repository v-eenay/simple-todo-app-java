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
import java.sql.SQLException;

@WebServlet("/edit-todo")
public class EditTodoServlet extends HttpServlet {
    private final TodoService todoService;

    public EditTodoServlet() {
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
        int todoId = Integer.parseInt(request.getParameter("id"));
        TodoModel todo = null;
        try {
            todo = todoService.getTodoById(todoId, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (todo != null) {
            request.setAttribute("todo", todo);
            request.getRequestDispatcher("/view/edit-todo.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/todos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        int todoId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = "on".equals(request.getParameter("completed"));

        TodoModel todo = new TodoModel(todoId, title, description, completed, user.getId());
        try {
            todoService.updateTodo(todo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/todos");
    }
}
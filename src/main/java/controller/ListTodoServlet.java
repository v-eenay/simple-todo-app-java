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
import java.util.List;

@WebServlet("/todos")
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
        List<TodoModel> todos = null;
        try {
            todos = todoService.getAllTodos(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("/view/list-todo.jsp").forward(request, response);
    }
}
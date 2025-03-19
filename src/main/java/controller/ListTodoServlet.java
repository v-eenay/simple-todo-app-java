package controller;

import model.TodoModel;
import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListTodoServlet")
public class ListTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all todos from the database
        List<TodoModel> todos = TodoDAO.listTodo();

        // Set the todos as a request attribute
        request.setAttribute("todos", todos);

        // Forward to the list-todo.jsp page
        request.getRequestDispatcher("view/list-todo.jsp").forward(request, response);
    }
}
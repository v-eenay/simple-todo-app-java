package service;

import model.TodoDAO;
import model.TodoModel;

import java.sql.SQLException;
import java.util.List;

public class TodoService {
    private final TodoDAO todoDAO;

    public TodoService() {
        this.todoDAO = new TodoDAO();
    }

    public List<TodoModel> getAllTodos(int userId) throws SQLException {
        return todoDAO.getAllTodos(userId);
    }
    
    public List<TodoModel> getAllTodos(int userId, String sortBy, String filterStatus, Integer filterCategory) throws SQLException {
        return todoDAO.getAllTodos(userId, sortBy, filterStatus, filterCategory);
    }

    public TodoModel getTodoById(int id, int userId) throws SQLException {
        return todoDAO.getTodoById(id, userId);
    }

    public void addTodo(TodoModel todo) throws SQLException {
        todoDAO.addTodo(todo);
    }

    public void updateTodo(TodoModel todo) throws SQLException {
        todoDAO.updateTodo(todo);
    }

    public void deleteTodo(int id, int userId) throws SQLException {
        todoDAO.deleteTodo(id, userId);
    }
}
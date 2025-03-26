package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;

public class TodoDAO {
    // Instance variables for connecting to the database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo_db";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }

    // Method to establish a database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public List<TodoModel> getAllTodos(int userId) throws SQLException {
        List<TodoModel> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE user_id = ? ORDER BY id DESC";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    todos.add(new TodoModel(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getBoolean("completed"),
                        rs.getInt("user_id")
                    ));
                }
            }
        }
        return todos;
    }

    public TodoModel getTodoById(int id, int userId) throws SQLException {
        String sql = "SELECT * FROM todos WHERE id = ? AND user_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.setInt(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TodoModel(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getBoolean("completed"),
                        rs.getInt("user_id")
                    );
                }
            }
        }
        return null;
    }

    public void addTodo(TodoModel todo) throws SQLException {
        String sql = "INSERT INTO todos (title, description, completed, user_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.setInt(4, todo.getUserId());
            
            pstmt.executeUpdate();
        }
    }

    public void updateTodo(TodoModel todo) throws SQLException {
        String sql = "UPDATE todos SET title = ?, description = ?, completed = ? WHERE id = ? AND user_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.setInt(4, todo.getId());
            pstmt.setInt(5, todo.getUserId());
            
            pstmt.executeUpdate();
        }
    }

    public void deleteTodo(int id, int userId) throws SQLException {
        String sql = "DELETE FROM todos WHERE id = ? AND user_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.setInt(2, userId);
            
            pstmt.executeUpdate();
        }
    }
    public void toggleCompleted(int id, int userId, boolean completed) throws SQLException {
        String sql = "UPDATE todos SET completed = ? WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, !completed);
            pstmt.setInt(2, id);
            pstmt.setInt(3, userId);
            
            pstmt.executeUpdate();
        }
    }
}
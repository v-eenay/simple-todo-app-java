package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;

public class TodoDAO {

    public List<TodoModel> getAllTodos(int userId) throws SQLException {
        return getAllTodos(userId, null, null, null);
    }

    public List<TodoModel> getAllTodos(int userId, String sortBy, String filterStatus, Integer filterCategory) throws SQLException {
        List<TodoModel> todos = new ArrayList<>();
        
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM todos WHERE user_id = ?");
        
        // Add filtering conditions
        if (filterStatus != null && !filterStatus.isEmpty() && !filterStatus.equals("all")) {
            boolean isCompleted = filterStatus.equals("completed");
            sqlBuilder.append(" AND completed = ").append(isCompleted ? "1" : "0");
        }
        
        if (filterCategory != null && filterCategory > 0) {
            sqlBuilder.append(" AND category_id = ").append(filterCategory);
        }
        
        // Add sorting
        sqlBuilder.append(" ORDER BY ");
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "title_asc":
                    sqlBuilder.append("title ASC");
                    break;
                case "title_desc":
                    sqlBuilder.append("title DESC");
                    break;
                case "date_asc":
                    sqlBuilder.append("id ASC");
                    break;
                case "date_desc":
                    sqlBuilder.append("id DESC");
                    break;
                default:
                    sqlBuilder.append("id DESC");
                    break;
            }
        } else {
            sqlBuilder.append("id DESC");
        }
        
        String sql = sqlBuilder.toString();
        
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
                        rs.getInt("user_id"),
                        rs.getInt("category_id")
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
                        rs.getInt("user_id"),
                        rs.getInt("category_id")
                    );
                }
            }
        }
        return null;
    }

    public void addTodo(TodoModel todo) throws SQLException {
        String sql = "INSERT INTO todos (title, description, completed, user_id, category_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.setInt(4, todo.getUserId());
            pstmt.setInt(5, todo.getCategoryId());
            
            pstmt.executeUpdate();
        }
    }

    public void updateTodo(TodoModel todo) throws SQLException {
        String sql = "UPDATE todos SET title = ?, description = ?, completed = ?, category_id = ? WHERE id = ? AND user_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.setInt(4, todo.getCategoryId());
            pstmt.setInt(5, todo.getId());
            pstmt.setInt(6, todo.getUserId());
            
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
            
            pstmt.setBoolean(1, completed);
            pstmt.setInt(2, id);
            pstmt.setInt(3, userId);
            
            pstmt.executeUpdate();
        }
    }
}
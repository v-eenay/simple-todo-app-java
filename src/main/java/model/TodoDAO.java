package model;

import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDAO {

    // Method to add a todo to the database and return its generated ID
    public static int addTodo(TodoModel todoModel) throws SQLException {
        String query = "INSERT INTO todo_table (user_id, title, description, completed, category, priority, due_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, todoModel.getUserId());
            ps.setString(2, todoModel.getTitle());
            ps.setString(3, todoModel.getDescription());
            ps.setBoolean(4, todoModel.getCompleted());
            ps.setString(5, todoModel.getCategory());
            ps.setInt(6, todoModel.getPriority());
            
            // Handle null due date
            if (todoModel.getDueDate() != null) {
                ps.setDate(7, new java.sql.Date(todoModel.getDueDate().getTime()));
            } else {
                ps.setNull(7, Types.DATE);
            }
            
            ps.setString(8, todoModel.getStatus());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return -1; // Indicate failure if no ID is generated
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Method to remove a todo from the database
    public static boolean removeTodo(TodoModel todoModel) {
        String query = "DELETE FROM todo_table WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, todoModel.getId());
            ps.setInt(2, todoModel.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to list all todos from the database for a specific user
    public static List<TodoModel> listTodosByUser(int userId) {
        List<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    String category = rs.getString("category");
                    int priority = rs.getInt("priority");
                    Date dueDate = rs.getDate("due_date");
                    String status = rs.getString("status");
                    
                    todos.add(new TodoModel(id, userId, title, description, completed, category, priority, dueDate, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
    
    // Method to list all todos from the database (for backward compatibility)
    public static ArrayList<TodoModel> listTodo() {
        ArrayList<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");
                String category = rs.getString("category");
                int priority = rs.getInt("priority");
                Date dueDate = rs.getDate("due_date");
                String status = rs.getString("status");
                
                todos.add(new TodoModel(id, userId, title, description, completed, category, priority, dueDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    // Method to update a todo in the database
    public static boolean updateTodo(TodoModel todoModel) {
        String query = "UPDATE todo_table SET title = ?, description = ?, completed = ?, category = ?, priority = ?, due_date = ?, status = ? WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, todoModel.getTitle());
            ps.setString(2, todoModel.getDescription());
            ps.setBoolean(3, todoModel.getCompleted());
            ps.setString(4, todoModel.getCategory());
            ps.setInt(5, todoModel.getPriority());
            
            // Handle null due date
            if (todoModel.getDueDate() != null) {
                ps.setDate(6, new java.sql.Date(todoModel.getDueDate().getTime()));
            } else {
                ps.setNull(6, Types.DATE);
            }
            
            ps.setString(7, todoModel.getStatus());
            ps.setInt(8, todoModel.getId());
            ps.setInt(9, todoModel.getUserId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to get a todo by ID
    public static TodoModel getTodoById(int todoId, int userId) {
        String query = "SELECT * FROM todo_table WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, todoId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    String category = rs.getString("category");
                    int priority = rs.getInt("priority");
                    Date dueDate = rs.getDate("due_date");
                    String status = rs.getString("status");
                    
                    return new TodoModel(todoId, userId, title, description, completed, category, priority, dueDate, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Method to filter todos by category
    public static List<TodoModel> filterTodosByCategory(int userId, String category) {
        List<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table WHERE user_id = ? AND category = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    int priority = rs.getInt("priority");
                    Date dueDate = rs.getDate("due_date");
                    String status = rs.getString("status");
                    
                    todos.add(new TodoModel(id, userId, title, description, completed, category, priority, dueDate, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
    
    // Method to filter todos by priority
    public static List<TodoModel> filterTodosByPriority(int userId, int priority) {
        List<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table WHERE user_id = ? AND priority = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, priority);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    String category = rs.getString("category");
                    Date dueDate = rs.getDate("due_date");
                    String status = rs.getString("status");
                    
                    todos.add(new TodoModel(id, userId, title, description, completed, category, priority, dueDate, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
    
    // Method to filter todos by status
    public static List<TodoModel> filterTodosByStatus(int userId, String status) {
        List<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table WHERE user_id = ? AND status = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    String category = rs.getString("category");
                    int priority = rs.getInt("priority");
                    Date dueDate = rs.getDate("due_date");
                    
                    todos.add(new TodoModel(id, userId, title, description, completed, category, priority, dueDate, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
    
    // Method to get all categories
    public static List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String query = "SELECT name FROM categories ORDER BY name";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    // Method to get overdue todos
    public static List<TodoModel> getOverdueTodos(int userId) {
        List<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table WHERE user_id = ? AND due_date < CURRENT_DATE() AND status != 'Completed'";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    String category = rs.getString("category");
                    int priority = rs.getInt("priority");
                    Date dueDate = rs.getDate("due_date");
                    String status = rs.getString("status");
                    
                    todos.add(new TodoModel(id, userId, title, description, completed, category, priority, dueDate, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
}
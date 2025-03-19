package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoDAO {
    // Instance variables for connecting to the database
    private static final String URL = "jdbc:mysql://localhost:3306/todo_database";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }

    // Method to establish a database connection
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Method to add a todo to the database and return its generated ID
    public static int addTodo(TodoModel todoModel) throws SQLException {
        String query = "INSERT INTO todo_table (title, description, completed) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, todoModel.getTitle());
            ps.setString(2, todoModel.getDescription());
            ps.setBoolean(3, todoModel.getCompleted());
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
        String query = "DELETE FROM todo_table WHERE id = ?";
        try (Connection conn = getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, todoModel.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to list all todos from the database
    public static ArrayList<TodoModel> listTodo() {
        ArrayList<TodoModel> todos = new ArrayList<>();
        String query = "SELECT * FROM todo_table";
        try (Connection conn = getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");
                todos.add(new TodoModel(id, title, description, completed));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    // Main method with a menu-driven interface
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display the menu
            System.out.println("\n=== Todo Application Menu ===");
            System.out.println("1. Add Todo");
            System.out.println("2. Remove Todo");
            System.out.println("3. List Todos");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // Add a new todo
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Is completed (true/false): ");
                    boolean completed = scanner.nextBoolean();
                    TodoModel newTodo = new TodoModel(0, title, description, completed);
                    try {
                        int newId = addTodo(newTodo);
                        if (newId != -1) {
                            System.out.printf("Todo added successfully with ID: %d%n", newId);
                        } else {
                            System.out.println("Failed to add todo.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error adding todo:");
                        e.printStackTrace();
                    }
                    break;

                case 2: // Remove a todo by ID
                    System.out.print("Enter todo ID to remove: ");
                    int idToRemove = scanner.nextInt();
                    TodoModel todoToRemove = new TodoModel(idToRemove, "", "", false);
                    if (removeTodo(todoToRemove)) {
                        System.out.println("Todo removed successfully.");
                    } else {
                        System.out.println("Failed to remove todo or todo not found.");
                    }
                    break;

                case 3: // List all todos
                    ArrayList<TodoModel> todos = listTodo();
                    if (todos.isEmpty()) {
                        System.out.println("No todos found.");
                    } else {
                        System.out.println("\n=== Todo List ===");
                        for (TodoModel todo : todos) {
                            System.out.printf("ID: %-4d | Title: %-20s | Description: %-30s | Completed: %b%n",
                                    todo.getId(), todo.getTitle(), todo.getDescription(), todo.getCompleted());
                        }
                    }
                    break;

                case 4: // Exit the application
                    System.out.println("Exiting Todo Application...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option (1-4).");
            }
        }
    }
}
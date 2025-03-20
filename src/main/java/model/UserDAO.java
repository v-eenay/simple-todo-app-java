package model;

import java.sql.*;
import java.util.Scanner;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/todo_database";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated driver
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL Driver not found.");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static int addUser(UserModel user) {
        String query = "INSERT INTO user_table (username, fname, lname, email, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword()); // In real applications, store a hashed password

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error while adding user: " + e.getMessage());
        }
        return -1;
    }

    public static UserModel validateUser(String username, String password) {
        String query = "SELECT * FROM user_table WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password); // Use hashed password comparison in real applications

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserModel(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error while validating user: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n+--------------------------------+");
            System.out.println("|      TEST USER DAO SYSTEM      |");
            System.out.println("+--------------------------------+");
            System.out.println("| 1. Add User                    |");
            System.out.println("| 2. Validate User                |");
            System.out.println("| 3. Exit                         |");
            System.out.println("+--------------------------------+");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addUserTest(scanner);
                case 2 -> validateUserTest(scanner);
                case 3 -> {
                    System.out.println("Exiting test system...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addUserTest(Scanner scanner) {
        System.out.println("\n=== Add New User ===");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        UserModel user = new UserModel(username, firstName, lastName, email, password);
        int userId = addUser(user);

        if (userId > 0) {
            System.out.printf("User added successfully! User ID: %d%n", userId);
        } else {
            System.out.println("Failed to add user.");
        }
    }

    private static void validateUserTest(Scanner scanner) {
        System.out.println("\n=== User Login ===");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        UserModel user = validateUser(username, password);

        if (user != null) {
            System.out.printf("Login successful! Welcome, %s %s.%n", user.getFirstName(), user.getLastName());
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}

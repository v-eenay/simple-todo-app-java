# Todo Application - Java Web Development Learning Project

A comprehensive Java web application project designed for students learning web development. This project implements a Todo list manager using Java Servlets, JSP, and MySQL, providing hands-on experience with core web technologies.

## 🎯 Learning Objectives

- Understanding MVC architecture in Java web applications
- Working with Servlets and JSP
- Database operations using JDBC
- Web application deployment
- Basic CRUD operations

## 📋 Prerequisites

Before starting, ensure you have:

- JDK 8 or higher installed
- Apache Tomcat 8.5 or higher
- MySQL Server 5.7 or higher
- IDE (Eclipse/IntelliJ IDEA)
- Git

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/v-eenay/simple-todo-app-java.git
cd simple-todo-app-java
```

### 2. Database Setup

1. Open MySQL command line or workbench
2. Execute the following SQL commands:

```sql
CREATE DATABASE todo_database;
USE todo_database;

CREATE TABLE todo_table (
 id INT PRIMARY KEY AUTO_INCREMENT,
 title VARCHAR(100) NOT NULL,
 description TEXT,
 completed BOOLEAN DEFAULT FALSE
);
```

### 3. Configure Database Connection

1. Navigate to `src/main/java/com/todo/model/TodoDAO.java`
2. Update the following properties:

```java
private static final String URL = "jdbc:mysql://localhost:3306/todo_database";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 4. Project Setup

#### Maven Setup
1. Ensure Maven is installed on your system
2. Navigate to project directory
3. Run `mvn clean install`
4. Add the following dependencies to pom.xml:
- javax.servlet-api
- mysql-connector-java
- jstl
- junit

#### Manual Setup
1. Create a new Dynamic Web Project
2. Add the following JARs to WEB-INF/lib:
- javax.servlet-api-3.1.0.jar
- mysql-connector-java-8.0.27.jar
- jstl-1.2.jar
3. Configure build path to include these JARs

### 5. Configure Tomcat Server

1. Window → Show View → Servers
2. Right-click → New → Server
3. Choose Apache → Tomcat v8.5
4. Set server location to your Tomcat installation
5. Add the project to the server

## 📁 Project Structure

```
todo-application/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/todo/
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AddTodoServlet.java
│   │   │   │   │   ├── DeleteTodoServlet.java
│   │   │   │   │   └── ListTodoServlet.java
│   │   │   │   └── model/
│   │   │   │       ├── TodoModel.java
│   │   │   │       └── TodoDAO.java
│   │   ├── webapp/
│   │   │   ├── WEB-INF/
│   │   │   │   └── web.xml
│   │   │   ├── views/
│   │   │   │   ├── add-todo.jsp
│   │   │   │   └── list-todo.jsp
│   │   │   ├── assets/
│   │   │   │   └── css/
│   │   │   │       └── styles.css
│   │   │   └── index.jsp
│   └── test/
└── pom.xml
```

## 🎯 Implementation Steps

### 1. Create Model Class (TodoModel.java)
```java
public class TodoModel {
   private int id;
   private String title;
   private String description;
   private boolean completed;
   // Add getters, setters, constructors
}
```

### 2. Implement DAO Layer (TodoDAO.java)
```java
public class TodoDAO {
   // Database operations
   public static int addTodo(TodoModel todoModel)
   public static ArrayList<TodoModel> listTodos()
   public static boolean deleteTodo(TodoModel todoModel)
}
```

### 3. Create Servlets
- AddTodoServlet.java: Handle todo creation
- DeleteTodoServlet.java: Handle todo deletion
- ListTodoServlet.java: Display todos

### 4. Design JSP Pages
- index.jsp: Landing page
- add-todo.jsp: Add new todo
- list-todo.jsp: Display all todos

## 🔍 Testing

1. Start Tomcat server
2. Access application at `http://localhost:8080/todo-application`
3. Test following features:
- Adding new todo
- Listing todos
- Deleting todo

## 🚀 Suggested Improvements

Students can enhance the project by adding:

1. User Authentication
- Login/Register functionality
- User-specific todos
- Session management

2. Todo Features
- Todo categories/tags
- Priority levels
- Due date notifications
- Status tracking

3. UI Enhancements
- Responsive design
- Dark/Light theme
- Search and filter todos
- Sorting options

4. API Integration
- RESTful API endpoints
- JSON response format
- API documentation

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/NewFeature`)
3. Implement your feature
- Follow existing code structure
- Add appropriate comments
- Include unit tests
4. Commit changes (`git commit -m 'Add NewFeature'`)
5. Push to branch (`git push origin feature/NewFeature`)
6. Open a Pull Request with detailed description

## ✍️ Author

Vinay Koirala  
Lecturer, Itahari International College

## 🆘 Need Help?

- Check the [Issues](https://github.com/v-eenay/simple-todo-app-java/issues) section
- Contact: binaya.koirala@iic.edu.np
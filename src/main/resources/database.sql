-- Create the database
CREATE DATABASE IF NOT EXISTS todo_db;
USE todo_db;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create todos table with user_id foreign key
CREATE TABLE IF NOT EXISTS todos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_user_id ON todos(user_id);
CREATE INDEX idx_username ON users(username);
CREATE INDEX idx_email ON users(email);

-- Insert a test user (password: test123)
INSERT INTO users (username, email, password_hash, salt) VALUES 
('testuser', 'test@example.com', 
'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
'randomSalt123');

-- Insert sample users (password: test123)
INSERT INTO users (username, email, password_hash, salt) VALUES 
('john_doe', 'john@example.com',
 '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
 'test123salt'),
('jane_smith', 'jane@example.com',
 '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
 'test123salt');

-- Insert sample todos
INSERT INTO todos (title, description, completed, user_id) VALUES
('Complete Project', 'Finish the todo application project', false, 1),
('Buy Groceries', 'Get milk, bread, and eggs', false, 1),
('Call Mom', 'Weekly catch-up call', true, 1),
('Review Code', 'Review pull requests for the team', false, 2),
('Write Documentation', 'Document the new API endpoints', true, 2),
('Plan Vacation', 'Research and plan summer vacation', false, 3),
('Exercise', '30 minutes of cardio', true, 3),
('Read Book', 'Read chapter 5 of the new book', false, 3); 
-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS todo_database;

-- Use the database
USE todo_database;

-- Drop the table if it exists to avoid errors
DROP TABLE IF EXISTS todo_table;

-- Create the todo table
CREATE TABLE todo_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE
);

-- Insert sample data (optional)
INSERT INTO todo_table (title, description, completed) VALUES 
('Complete Java Assignment', 'Finish the todo application with CRUD operations', 0),
('Buy groceries', 'Milk, eggs, bread, and vegetables', 0),
('Read Book', 'Finish reading "Effective Java" by Joshua Bloch', 1),
('Exercise', 'Go for a 30-minute jog', 0);

-- Sample queries for CRUD operations

-- Select all todos
-- SELECT * FROM todo_table;

-- Select a specific todo by ID
-- SELECT * FROM todo_table WHERE id = 1;

-- Add a new todo
-- INSERT INTO todo_table (title, description, completed) VALUES ('New Todo', 'Description here', 0);

-- Update an existing todo
-- UPDATE todo_table SET title = 'Updated Title', description = 'Updated Description', completed = 1 WHERE id = 1;

-- Delete a todo
-- DELETE FROM todo_table WHERE id = 1; 
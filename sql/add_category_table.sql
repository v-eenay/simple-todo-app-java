-- Add category table
CREATE TABLE IF NOT EXISTS category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    colour VARCHAR(7) NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Add category_id column to todos table
ALTER TABLE todos
ADD COLUMN category_id INT,
ADD FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL;

-- Insert default category
INSERT INTO category (category_name, colour, user_id)
SELECT 'Default', '#E2E8F0', id FROM users;
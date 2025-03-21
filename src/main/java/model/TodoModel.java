package model;

import java.util.Date;

public class TodoModel {
    //Instance Variables
    private int id; //Primary key
    private int userId; // Foreign key to user table
    private String title;
    private String description;
    private boolean completed;
    private String category;
    private int priority; // 1-Low, 2-Medium, 3-High
    private Date dueDate;
    private String status; // "Not Started", "In Progress", "Completed"

    public TodoModel(int id, int userId, String title, String description, boolean completed, String category, int priority, Date dueDate, String status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
    }

    public TodoModel(int userId, String title, String description, boolean completed, String category, int priority, Date dueDate, String status) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    // Constructor for backward compatibility
    public TodoModel(int id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.category = "General";
        this.priority = 2; // Medium priority by default
        this.status = completed ? "Completed" : "Not Started";
    }

    // Constructor for backward compatibility
    public TodoModel(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.category = "General";
        this.priority = 2; // Medium priority by default
        this.status = completed ? "Completed" : "Not Started";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed) {
            this.status = "Completed";
        }
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        // Update completed flag based on status
        this.completed = "Completed".equals(status);
    }
    
    // Helper method to get priority as text
    public String getPriorityText() {
        return switch (priority) {
            case 1 -> "Low";
            case 3 -> "High";
            default -> "Medium";
        };
    }
    
    // Helper method to check if todo is overdue
    public boolean isOverdue() {
        if (dueDate == null || "Completed".equals(status)) {
            return false;
        }
        return new Date().after(dueDate);
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", category='" + category + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }
}

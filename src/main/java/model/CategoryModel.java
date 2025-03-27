package model;

public class CategoryModel {
    //Instance variables
    private int id;
    private String categoryName;
    private String colour;
    private int userId;

    public CategoryModel(String categoryName, String colour, int userId) {
        this.categoryName = categoryName;
        this.colour = colour;
        this.userId = userId;
    }

    public CategoryModel(int id, String categoryName, String colour, int userId) {
        this.id = id;
        this.categoryName = categoryName;
        this.colour = colour;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

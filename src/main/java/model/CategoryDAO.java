package model;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    public static int addCategory(CategoryModel category) {
        String sql = "insert into category (category_name, colour, user_id) values (?, ?, ?)";
        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            psmt.setString(1, category.getCategoryName());
            psmt.setString(2, category.getColour());
            psmt.setInt(3, category.getUserId());
            int generatedKeys = psmt.executeUpdate();
            if (generatedKeys > 0) {
                ResultSet rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public static ArrayList<CategoryModel> getAllCategories() {
        ArrayList<CategoryModel> list = new ArrayList<>();
        String sql = "select * from category";
        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String categoryName = rs.getString("category_name");
                String colour = rs.getString("colour");
                int userId = rs.getInt("user_id");
                list.add(new CategoryModel(id, categoryName, colour, userId));
            }
            return list;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<CategoryModel> getCategoryByUserId(int userId) {
        ArrayList<CategoryModel> list = new ArrayList<>();
        String sql = "select * from category where user_id = ?";
        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String categoryName = rs.getString("category_name");
                String colour = rs.getString("colour");
                list.add(new CategoryModel(id, categoryName, colour, userId));
            }
            return list;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean deleteCategory(int categoryId) {
        String sql = "delete from category where id = ?";
        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, categoryId);
            int deleted = psmt.executeUpdate();
            if (deleted > 0) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

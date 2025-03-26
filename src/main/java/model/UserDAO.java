package model;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class UserDAO {
    private static final String SALT_ALGORITHM = "SHA1PRNG";
    private static final String HASH_ALGORITHM = "SHA-256";

    public boolean registerUser(UserModel user) throws SQLException {
        String sql = "INSERT INTO users (username, email, password_hash, salt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Generate salt and hash password
            byte[] salt = generateSalt();
            String hashedPassword = hashPassword(user.getPassword(), salt);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, Base64.getEncoder().encodeToString(salt));
            
            pstmt.executeUpdate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public UserModel authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    String storedSalt = rs.getString("salt");
                    byte[] salt = Base64.getDecoder().decode(storedSalt);
                    
                    String inputHash = hashPassword(password, salt);
                    if (storedHash.equals(inputHash)) {
                        return new UserModel(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email")
                        );
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public UserModel getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    private byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance(SALT_ALGORITHM);
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
} 
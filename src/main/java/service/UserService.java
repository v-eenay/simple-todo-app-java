package service;

import model.UserDAO;
import model.UserModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(UserModel user) throws SQLException {
        if (userDAO.usernameExists(user.getUsername())) {
            return false;
        }
        if (userDAO.emailExists(user.getEmail())) {
            return false;
        }
        return userDAO.registerUser(user);
    }

    public UserModel authenticateUser(String username, String password) throws SQLException {
        return userDAO.authenticateUser(username, password);
    }

    public boolean usernameExists(String username) throws SQLException {
        return userDAO.usernameExists(username);
    }

    public boolean emailExists(String email) throws SQLException {
        return userDAO.emailExists(email);
    }

    public UserModel getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
    }
} 
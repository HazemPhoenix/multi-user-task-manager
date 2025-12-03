package com.hazem.service;

import com.hazem.config.DatabaseConnectionManager;
import com.hazem.dao.UserDAO;
import com.hazem.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private final DatabaseConnectionManager dbManager;
    private final UserDAO userDAO;

    public UserService(DatabaseConnectionManager dbManager, UserDAO userDAO) {
        this.dbManager = dbManager;
        this.userDAO = userDAO;
    }

    public boolean registerUser(String username, String email, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        User user = new User(email, hashedPassword, username);
        try(Connection con = dbManager.getConnection()){
            userDAO.save(con, user);
            return true;
        } catch(SQLException e) {
            System.out.println("Could not create user.");
            return false;
        }
    }

    public User loginUser(String email, String password) {
        try(Connection con = dbManager.getConnection()){
            User user = userDAO.findByEmail(con, email);
            if(user == null || !BCrypt.checkpw(password, user.getPassword())) {
                throw new RuntimeException("Email and/or password are incorrect.");
            }
            return user;
        } catch(SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}

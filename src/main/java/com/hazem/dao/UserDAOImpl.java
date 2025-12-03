package com.hazem.dao;

import com.hazem.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public void save(Connection con, User user) throws SQLException {
        String sql = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public User findByEmail(Connection con, String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("username"));
        }
        return null;
    }
}

package com.hazem.dao;

import com.hazem.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {
    void save(Connection con, User user) throws SQLException;
    User findByEmail(Connection con, String email) throws SQLException;
}
